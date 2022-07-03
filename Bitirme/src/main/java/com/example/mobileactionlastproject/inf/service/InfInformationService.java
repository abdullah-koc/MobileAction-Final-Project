package com.example.mobileactionlastproject.inf.service;


import com.example.mobileactionlastproject.inf.converter.InfInformationDataDtoConverter;
import com.example.mobileactionlastproject.inf.converter.InfInformationConverter;
import com.example.mobileactionlastproject.inf.dto.InfInformationDataDto;
import com.example.mobileactionlastproject.inf.entity.InfInformation;
import com.example.mobileactionlastproject.inf.enums.EnumCity;
import com.example.mobileactionlastproject.inf.service.entityservice.InfInformationEntityService;
import com.example.mobileactionlastproject.rst.dto.DatePollutionDto;
import com.example.mobileactionlastproject.rst.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
@Slf4j
public class InfInformationService {
    private final InfInformationEntityService infInformationEntityService;
    private final InfInformationConverter infInformationConverter;
    private final InfInformationDataDtoConverter infInformationDataDtoConverter;
    private final RestTemplateService restTemplateService;

    public InfInformationDataDto queryByCityAndStartDateAndEndDate(EnumCity city, LocalDate startDate, LocalDate endDate) {

        if (startDate == null && endDate == null) {
            startDate = LocalDate.now().minusDays(6);
            endDate = LocalDate.now();
        } else if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("startDate and endDate must be both null or both not null");
        }

        if (startDate.isBefore(LocalDate.of(2020, 11, 27))) {
            throw new IllegalArgumentException("Start date cannot be before 2020-11-27");
        }
        if (endDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("End date cannot be after today");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        List<InfInformation> existingData = getInfInformationsFromDB(city, startDate, endDate);
        List<InfInformation> newData = new ArrayList<>(existingData);
        long days = DAYS.between(startDate, endDate) + 1;
        for (int i = 0; i < days; i++) {
            LocalDate localDate = startDate.plusDays(i);
            if (existingData.stream().noneMatch(inf -> inf.getLocalDate().equals(localDate))) {
                DatePollutionDto datePollutionDto = restTemplateService.getPollutionInformationFromAPI(city, localDate);
                InfInformation infInformation = infInformationConverter.convertToInfInformation(datePollutionDto);
                newData.add(infInformation);
                save(infInformation);
                log.info("City: {}, Date {}, was saved to DB (retrieved from API)", city, localDate);
            }
            else{
                log.info("City: {}, Date {} already exists in DB (retrieved from DB)", city, localDate);
            }
        }

        newData.sort(Comparator.comparing(InfInformation::getLocalDate));

        return infInformationDataDtoConverter.convertToInfInformationDataDto(newData);
    }

    public List<InfInformation> getInfInformationsFromDB(EnumCity city, LocalDate startDate, LocalDate endDate) {
        List<InfInformation> existingData = new ArrayList<>();
        LocalDate tempDate = startDate;
        while (tempDate.isBefore(endDate.plusDays(1))) {
            InfInformation infInformation = infInformationEntityService.findByCityAndLocalDate(city, tempDate);
            if (infInformation != null) {
                existingData.add(infInformation);
            }
            tempDate = tempDate.plusDays(1);
        }
        return existingData;
    }

    public void deleteByCityAndBetweenDates(EnumCity city, LocalDate startDate, LocalDate endDate) {
        LocalDate tempDate = startDate;
        while (tempDate.isBefore(endDate.plusDays(1))) {
            deleteByCityAndLocalDate(city, tempDate);
            tempDate = tempDate.plusDays(1);
        }
    }

    private InfInformation save(InfInformation infInformation) {
        return infInformationEntityService.save(infInformation);
    }

    private void deleteByCityAndLocalDate(EnumCity city, LocalDate localDate) {
        infInformationEntityService.deleteByCityAndLocalDate(city, localDate);
    }

}
