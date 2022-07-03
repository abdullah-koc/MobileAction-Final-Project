package com.example.mobileactionlastproject.rst.service;


import com.example.mobileactionlastproject.inf.enums.EnumCity;
import com.example.mobileactionlastproject.rst.converter.PollutionDtoConverter;
import com.example.mobileactionlastproject.rst.dto.DatePollutionDto;
import com.example.mobileactionlastproject.rst.dto.HourlyPollutionDto;
import com.example.mobileactionlastproject.rst.dto.HourlyPollutionListDto;
import com.example.mobileactionlastproject.rst.dto.LatLngDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestTemplateService {

    private final PollutionDtoConverter pollutionDtoConverter;

    @Value("${openweather.url}")
    private String url;

    @Value("${openweather.apikey}")
    private String apiKey;

    public LatLngDto getCoordinatesFromAPI(EnumCity city){
        String requestUrl = url + "geo/1.0/direct?q=" + city.getCity() + "&limit=1&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate(getSimpleClientHttpRequestFactory());
        ResponseEntity<LatLngDto[]> response = restTemplate.getForEntity(requestUrl, LatLngDto[].class);
        return Objects.requireNonNull(response.getBody()).length > 0 ? response.getBody()[0] : null;
    }

    public DatePollutionDto getPollutionInformationFromAPI
            (EnumCity city, LocalDate localDate){


        ZoneId zoneId = ZoneId.of("UTC");
        long epochStart = localDate.atStartOfDay(zoneId).toEpochSecond();

        LocalDate endDateIncluded = localDate.plusDays(1);
        long epochEnd = endDateIncluded.atStartOfDay(zoneId).toEpochSecond();
        epochEnd -= 60;

        LatLngDto coordinates = getCoordinatesFromAPI(city);
        if(coordinates == null){
            throw new RuntimeException("Error occurred when getting coordinates for " + city.getCity());
        }

        BigDecimal lat = coordinates.getLat();
        BigDecimal lon = coordinates.getLon();

        String requestUrl = url + "data/2.5/air_pollution/history?lat=" + lat + "&lon=" + lon + "&start=" + epochStart + "&end=" + epochEnd + "&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate(getSimpleClientHttpRequestFactory());
        ResponseEntity<HourlyPollutionListDto> response = restTemplate.getForEntity(requestUrl, HourlyPollutionListDto.class);

        List<HourlyPollutionDto> hourlyPollutionDtoList = Objects.requireNonNull(response.getBody()).getList();

        DatePollutionDto datePollutionDto = pollutionDtoConverter.convertToDatePollutionDtoList(hourlyPollutionDtoList, city);

        return datePollutionDto;
    }

    private SimpleClientHttpRequestFactory getSimpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(7_000);
        factory.setReadTimeout(7_000);
        return factory;
    }




}
