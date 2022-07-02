package com.example.mobileactionlastproject.rst.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PollutionInformationDto {
    private BigDecimal co;
    private BigDecimal o3;
    private BigDecimal so2;
}
