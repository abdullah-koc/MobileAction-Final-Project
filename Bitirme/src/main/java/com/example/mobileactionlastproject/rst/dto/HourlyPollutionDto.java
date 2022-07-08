package com.example.mobileactionlastproject.rst.dto;

import lombok.Data;


/**
 * This Dto is used for hourly pollution information
 * @author : Muhammet Abdullah Koç
 * @since : 1.0
 */
@Data
public class HourlyPollutionDto {
    private PollutionInformationDto components;
    private Long dt;
}
