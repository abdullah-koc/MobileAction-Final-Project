package com.example.mobileactionlastproject.inf.dto.category;

import com.example.mobileactionlastproject.inf.enums.EnumPollutionCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

@Data
public class InfInformationSO2Dto {
    @JsonProperty("SO2")
    private EnumPollutionCategory SO2;
}
