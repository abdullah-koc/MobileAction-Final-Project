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
import java.util.List;

/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

@Service
public class PollutionDtoConverter {

    /**
     * This method converts the list of hourly pollution dto to pollution information dto of a date.
     * The list includes hourly data of a day, and the method calculates the average of the data.
     * @param hourlyPollutionDtoList {@link List} of {@link HourlyPollutionDto}
     * @param city {@link EnumCity}
     * @return {@link DatePollutionDto}
     */
    public DatePollutionDto convertToDatePollutionDtoList(List<HourlyPollutionDto> hourlyPollutionDtoList, EnumCity city) {
        if(hourlyPollutionDtoList == null) {
            return null;
        }

        //number of data got from the api for a day
        int size = hourlyPollutionDtoList.size();

        BigDecimal avgCO = BigDecimal.ZERO;
        BigDecimal avgSO2 = BigDecimal.ZERO;
        BigDecimal avgO3 = BigDecimal.ZERO;

        //converting epoch date to localdate
        LocalDate localDate = LocalDate.ofInstant(Instant.ofEpochSecond(hourlyPollutionDtoList.get(0).getDt(), 0), ZoneOffset.UTC);

        DatePollutionDto datePollutionDto = new DatePollutionDto();

        for (int j = 0; j < size; j++) {
            HourlyPollutionDto hourlyPollutionDto = hourlyPollutionDtoList.get(j);
            avgCO = avgCO.add(hourlyPollutionDto.getComponents().getCo());
            avgSO2 = avgSO2.add(hourlyPollutionDto.getComponents().getSo2());
            avgO3 = avgO3.add(hourlyPollutionDto.getComponents().getO3());
        }

        //taking average
        avgCO = avgCO.divide(BigDecimal.valueOf(size), 2, RoundingMode.HALF_UP);
        avgSO2 = avgSO2.divide(BigDecimal.valueOf(size), 2, RoundingMode.HALF_UP);
        avgO3 = avgO3.divide(BigDecimal.valueOf(size), 2, RoundingMode.HALF_UP);

        datePollutionDto.setLocalDate(localDate);

        PollutionInformationDto pollutionInformationDto = new PollutionInformationDto();
        pollutionInformationDto.setCo(avgCO);
        pollutionInformationDto.setSo2(avgSO2);
        pollutionInformationDto.setO3(avgO3);

        datePollutionDto.setPollutionInformationDto(pollutionInformationDto);
        datePollutionDto.setCity(city);

        return datePollutionDto;
    }


}
