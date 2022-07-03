package com.example.mobileactionlastproject.inf.dto.category;

import com.example.mobileactionlastproject.inf.enums.EnumPollutionCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InfInformationO3Dto{
    @JsonProperty("O3")
    private EnumPollutionCategory O3;
}
