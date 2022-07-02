package com.example.mobileactionlastproject.rst.dto;

import lombok.Data;

import java.util.List;

@Data
public class HourlyPollutionDto {
    private PollutionInformationDto components;
    private Long dt;
}
