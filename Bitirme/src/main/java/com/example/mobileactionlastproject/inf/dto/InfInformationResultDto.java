package com.example.mobileactionlastproject.inf.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InfInformationResultDto {
    private LocalDate date;
    private List<Object> categories;
}
