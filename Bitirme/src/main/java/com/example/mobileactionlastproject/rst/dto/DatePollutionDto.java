package com.example.mobileactionlastproject.rst.dto;

import com.example.mobileactionlastproject.inf.enums.EnumCity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DatePollutionDto {
    private EnumCity city;
    private PollutionInformationDto pollutionInformationDto;
    private LocalDate localDate;
}
