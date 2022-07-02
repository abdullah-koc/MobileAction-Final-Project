package com.example.mobileactionlastproject.inf.dto;

import com.example.mobileactionlastproject.inf.enums.EnumCity;
import lombok.Data;

import java.util.List;

@Data
public class InfInformationDataDto {

    private EnumCity city;
    private List<InfInformationResultDto> results;
}
