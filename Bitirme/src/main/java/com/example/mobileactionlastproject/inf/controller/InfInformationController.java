package com.example.mobileactionlastproject.inf.controller;

import com.example.mobileactionlastproject.inf.dto.InfInformationDataDto;
import com.example.mobileactionlastproject.inf.enums.EnumCity;
import com.example.mobileactionlastproject.inf.service.InfInformationService;
import com.example.mobileactionlastproject.rst.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

@RestController
@RequestMapping("/api/v1/information")
@RequiredArgsConstructor
public class InfInformationController {

    private final InfInformationService infInformationService;
    private final RestTemplateService restTemplateService;

    @GetMapping
    public ResponseEntity<InfInformationDataDto> findByCityAndStartDateAndEndDate(
            @RequestParam(value = "city") EnumCity city,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @RequestParam (value = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate){
        return ResponseEntity.ok(infInformationService.queryByCityAndStartDateAndEndDate(city, startDate, endDate));
    }

    @DeleteMapping
    public void deleteByCityAndDates(
            @RequestParam(value = "city") EnumCity city,
            @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @RequestParam ("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate
    ){
        infInformationService.deleteByCityAndBetweenDates(city, startDate, endDate);
    }

}
