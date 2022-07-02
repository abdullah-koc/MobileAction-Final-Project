package com.example.mobileactionlastproject.rst.service;

import com.example.mobileactionlastproject.inf.enums.EnumCity;
import com.example.mobileactionlastproject.rst.converter.PollutionDtoConverter;
import com.example.mobileactionlastproject.rst.dto.LatLngDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestTemplateServiceTest {

    @Mock
    private PollutionDtoConverter pollutionDtoConverter;

    @Spy
    @InjectMocks
    private RestTemplateService restTemplateService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(restTemplateService, "url", "http://api.openweathermap.org/");
        ReflectionTestUtils.setField(restTemplateService, "apiKey", "bb9c4c962e095b0789f6604f037585dc");
    }

    @Test
    void shouldGetCoordinatesFromAPI() {
        LatLngDto latLngDto = restTemplateService.getCoordinatesFromAPI(EnumCity.London);
        assertEquals(51.51, latLngDto.getLat().setScale(2, RoundingMode.HALF_UP).doubleValue());
        assertEquals(-0.13, latLngDto.getLon().setScale(2, RoundingMode.HALF_UP).doubleValue());

        latLngDto = restTemplateService.getCoordinatesFromAPI(EnumCity.Ankara);
        assertEquals(39.92, latLngDto.getLat().setScale(2, RoundingMode.HALF_UP).doubleValue());
        assertEquals(32.85, latLngDto.getLon().setScale(2, RoundingMode.HALF_UP).doubleValue());

        latLngDto = restTemplateService.getCoordinatesFromAPI(EnumCity.Mumbai);
        assertEquals(19.08, latLngDto.getLat().setScale(2, RoundingMode.HALF_UP).doubleValue());
        assertEquals(72.88, latLngDto.getLon().setScale(2, RoundingMode.HALF_UP).doubleValue());

        latLngDto = restTemplateService.getCoordinatesFromAPI(EnumCity.Tokyo);
        assertEquals(35.68, latLngDto.getLat().setScale(2, RoundingMode.HALF_UP).doubleValue());
        assertEquals(139.76, latLngDto.getLon().setScale(2, RoundingMode.HALF_UP).doubleValue());

        latLngDto = restTemplateService.getCoordinatesFromAPI(EnumCity.Barcelona);
        assertEquals(41.38, latLngDto.getLat().setScale(2, RoundingMode.HALF_UP).doubleValue());
        assertEquals(2.18, latLngDto.getLon().setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    @Test
    void shouldGetPollutionInformationFromAPI() {
        assertNotNull(restTemplateService.getPollutionInformationFromAPI(EnumCity.London, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 5, 13)));
        assertNotNull(restTemplateService.getPollutionInformationFromAPI(EnumCity.Ankara, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 5, 13)));
        assertNotNull(restTemplateService.getPollutionInformationFromAPI(EnumCity.Mumbai, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 5, 13)));
        assertNotNull(restTemplateService.getPollutionInformationFromAPI(EnumCity.Tokyo, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 5, 13)));
        assertNotNull(restTemplateService.getPollutionInformationFromAPI(EnumCity.Barcelona, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 5, 13)));
        //start date cannot be after end date, throws error
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> restTemplateService.getPollutionInformationFromAPI(EnumCity.London, LocalDate.of(2022, 5, 13), LocalDate.of(2022, 5, 12)));
        assertEquals("Start date cannot be after end date", illegalArgumentException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNoCoordinateExist() {
        when(restTemplateService.getCoordinatesFromAPI(EnumCity.London)).thenReturn(null);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> restTemplateService.getPollutionInformationFromAPI(EnumCity.London, LocalDate.of(2022, 5, 12), LocalDate.of(2022, 5, 13)));
        assertEquals("Error occurred when getting coordinates for London", runtimeException.getMessage());
    }
}