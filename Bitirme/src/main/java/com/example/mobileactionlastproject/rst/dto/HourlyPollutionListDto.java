package com.example.mobileactionlastproject.rst.dto;

import lombok.Data;

import java.util.List;

/**
 * This Dto is used for holding list of HourlyPollutionDto's.
 * @author : Muhammet Abdullah Koç
 * @since : 1.0
 */
@Data
public class HourlyPollutionListDto {

    private List<HourlyPollutionDto> list;
}
