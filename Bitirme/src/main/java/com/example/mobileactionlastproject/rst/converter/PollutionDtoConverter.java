package com.example.mobileactionlastproject.rst.converter;

import com.example.mobileactionlastproject.inf.enums.EnumCity;
import com.example.mobileactionlastproject.rst.dto.DatePollutionDto;
import com.example.mobileactionlastproject.rst.dto.HourlyPollutionDto;
import com.example.mobileactionlastproject.rst.dto.PollutionInformationDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PollutionDtoConverter {
    public List<DatePollutionDto> convertToDatePollutionDtoList(List<HourlyPollutionDto> hourlyPollutionDtoList, EnumCity city){
        int noOfDatePollutionDtos = hourlyPollutionDtoList.size() / 24;
        List<DatePollutionDto> datePollutionDtoList = new ArrayList<>();

        for(int i = 0; i < noOfDatePollutionDtos; i++){
            BigDecimal avgCO = BigDecimal.ZERO;
            BigDecimal avgSO2 = BigDecimal.ZERO;
            BigDecimal avgO3 = BigDecimal.ZERO;

            LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochSecond(hourlyPollutionDtoList.get(i * 24).getDt(), 0), ZoneOffset.UTC);
            DatePollutionDto datePollutionDto = new DatePollutionDto();

            for(int j = 0; j < 24; j++){
                HourlyPollutionDto hourlyPollutionDto = hourlyPollutionDtoList.get(i * 24 + j);
                avgCO = avgCO.add(hourlyPollutionDto.getComponents().getCo());
                avgSO2 = avgSO2.add(hourlyPollutionDto.getComponents().getSo2());
                avgO3 = avgO3.add(hourlyPollutionDto.getComponents().getO3());
            }
            avgCO = avgCO.divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
            avgSO2 = avgSO2.divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
            avgO3 = avgO3.divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);

            datePollutionDto.setLocalDate(localDate);

            PollutionInformationDto pollutionInformationDto = new PollutionInformationDto();
            pollutionInformationDto.setCo(avgCO);
            pollutionInformationDto.setSo2(avgSO2);
            pollutionInformationDto.setO3(avgO3);
            datePollutionDto.setPollutionInformationDto(pollutionInformationDto);
            datePollutionDto.setCity(city);

            datePollutionDtoList.add(datePollutionDto);
        }
        return datePollutionDtoList;
    }




}
