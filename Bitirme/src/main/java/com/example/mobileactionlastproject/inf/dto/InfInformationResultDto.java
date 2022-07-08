package com.example.mobileactionlastproject.inf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

@Data
public class InfInformationResultDto {
    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("Categories")
    private List<Object> categories;
}
