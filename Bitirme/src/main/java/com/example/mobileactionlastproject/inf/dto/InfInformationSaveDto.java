package com.example.mobileactionlastproject.inf.dto;

import com.example.mobileactionlastproject.inf.enums.EnumCity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InfInformationSaveDto {
    private EnumCity city;
    private LocalDate localDate;
    private BigDecimal COPollution;
    private BigDecimal O3Pollution;
    private BigDecimal SO2Pollution;
}
