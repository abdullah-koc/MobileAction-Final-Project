package com.example.mobileactionlastproject.rst.dto;

import lombok.Data;

import java.math.BigDecimal;


/**
 * This Dto is used for getting the lat and lng from the api.
 * @author : Muhammet Abdullah Koç
 * @since : 1.0
 */
@Data
public class LatLngDto {

    private BigDecimal lat;
    private BigDecimal lon;
}
