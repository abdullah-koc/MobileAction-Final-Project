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

/**
 * This class is used to get data from API.
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

@Service
@RequiredArgsConstructor
public class RestTemplateService {

    private final PollutionDtoConverter pollutionDtoConverter;

    @Value("${openweather.url}")
    private String url;

    @Value("${openweather.apikey}")
    private String apiKey;

    /**
     * This method is used to get coordinates of city from API.
     * @param city {@link EnumCity}
     * @return {@link LatLngDto}
     */
    public LatLngDto getCoordinatesFromAPI(EnumCity city){
        String requestUrl = url + "geo/1.0/direct?q=" + city.getCity() + "&limit=1&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate(getSimpleClientHttpRequestFactory());
        ResponseEntity<LatLngDto[]> response = restTemplate.getForEntity(requestUrl, LatLngDto[].class);
        return Objects.requireNonNull(response.getBody()).length > 0 ? response.getBody()[0] : null;
    }

    /**
     * This method is used to get daily information from api
     * The API returns hourly data and this method converts it to daily data.
     * Data is retrieved day by day because some dates may have less than 24 data
     * Therefore, the conversion from hourly to daily data should not assume that there are 24 elements in a day.
     * If there are less than 24 elements, this might cause an error.
     * Now, because data is retrieved daily, there won't be any errors.
     * @param city {@link EnumCity}
     * @param localDate {@link LocalDate}
     * @return {@link DatePollutionDto}
     */
    public DatePollutionDto getPollutionInformationFromAPI (EnumCity city, LocalDate localDate){

        //getting start and end times as epoch time
        ZoneId zoneId = ZoneId.of("UTC");
        long epochStart = localDate.atStartOfDay(zoneId).toEpochSecond();

        LocalDate endDateIncluded = localDate.plusDays(1);
        long epochEnd = endDateIncluded.atStartOfDay(zoneId).toEpochSecond();
        epochEnd -= 60;  //subtracting 1 minute to avoid getting data from the next day.

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

        //conversion to daily data
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
