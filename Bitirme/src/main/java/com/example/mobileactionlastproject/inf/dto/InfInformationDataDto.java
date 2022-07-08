package com.example.mobileactionlastproject.inf.dto;

import com.example.mobileactionlastproject.inf.enums.EnumCity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

@Data
public class InfInformationDataDto {

    @JsonProperty("City")
    private EnumCity city;

    @JsonProperty("Results")
    private List<InfInformationResultDto> results;
}
