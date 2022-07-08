package com.example.mobileactionlastproject.rst.dto;

import lombok.Data;

import java.math.BigDecimal;


/**
 * This Dto is used for the pollution information.
 * @author : Muhammet Abdullah Koç
 * @since : 1.0
 */
@Data
public class PollutionInformationDto {
    private BigDecimal co;
    private BigDecimal o3;
    private BigDecimal so2;
}
