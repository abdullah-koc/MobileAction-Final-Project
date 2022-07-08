package com.example.mobileactionlastproject.rst.dto;

import com.example.mobileactionlastproject.inf.enums.EnumCity;
import lombok.Data;

import java.time.LocalDate;

/**
 * This Dto is used for daily pollution information
 * @author : Muhammet Abdullah Koç
 * @since : 1.0
 */
@Data
public class DatePollutionDto {
    private EnumCity city;
    private PollutionInformationDto pollutionInformationDto;
    private LocalDate localDate;
}
