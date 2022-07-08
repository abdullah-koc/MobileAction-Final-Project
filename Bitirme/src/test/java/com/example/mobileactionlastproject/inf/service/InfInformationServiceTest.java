package com.example.mobileactionlastproject.inf.service;

import com.example.mobileactionlastproject.inf.converter.InfInformationDataDtoConverter;
import com.example.mobileactionlastproject.inf.converter.InfInformationConverter;
import com.example.mobileactionlastproject.inf.dto.InfInformationDataDto;
import com.example.mobileactionlastproject.inf.dto.InfInformationResultDto;
import com.example.mobileactionlastproject.inf.entity.InfInformation;
import com.example.mobileactionlastproject.inf.enums.EnumCity;
import com.example.mobileactionlastproject.inf.service.entityservice.InfInformationEntityService;
import com.example.mobileactionlastproject.rst.dto.DatePollutionDto;
import com.example.mobileactionlastproject.rst.service.RestTemplateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InfInformationServiceTest {

    @Mock
    private InfInformationEntityService infInformationEntityService;

    @Mock
    private InfInformationConverter infInformationSaveDtoConverter;

    @Mock
    private InfInformationDataDtoConverter infInformationDataDtoConverter;

    @Mock
    private RestTemplateService restTemplateService;

    @Spy
    @InjectMocks
    private InfInformationService infInformationService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(restTemplateService, "url", "http://api.openweathermap.org/");
        ReflectionTestUtils.setField(restTemplateService, "apiKey", "bb9c4c962e095b0789f6604f037585dc");
    }

    @Test
    void shouldThrowExceptionWhenOneOfDatesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> infInformationService.queryByCityAndStartDateAndEndDate(EnumCity.London, null, LocalDate.of(2022, 5,13)));
        assertThrows(IllegalArgumentException.class, () -> infInformationService.queryByCityAndStartDateAndEndDate(EnumCity.London, LocalDate.of(2022, 5,12), null));
    }

    @Test
    void shouldThrowExceptionWhenStartDateOrEndDateIsNotValid(){

        //invalid start date
        assertThrows(IllegalArgumentException.class, () -> infInformationService.queryByCityAndStartDateAndEndDate(EnumCity.London, LocalDate.of(2019, 11, 27), LocalDate.of(2022, 5,12)));

        //invalid end date
        assertThrows(IllegalArgumentException.class, () -> infInformationService.queryByCityAndStartDateAndEndDate(EnumCity.London, LocalDate.of(2022, 5,12), LocalDate.of(2023, 5,13)));

        //start date is after end date
        assertThrows(IllegalArgumentException.class, () -> infInformationService.queryByCityAndStartDateAndEndDate(EnumCity.London, LocalDate.of(2022, 5,12), LocalDate.of(2022, 5,11)));
    }

    @Test
    void shouldReturnFromDBWhenAllDataIsInDB() {

        LocalDate localDate1 = LocalDate.of(2022, 5, 12);
        LocalDate localDate2 = LocalDate.of(2022, 5, 13);

        InfInformation infInformation1 = mock(InfInformation.class);
        InfInformation infInformation2 = mock(InfInformation.class);

        when(infInformation1.getLocalDate()).thenReturn(localDate1);
        when(infInformation2.getLocalDate()).thenReturn(localDate2);

        List<InfInformation> existingData = new ArrayList<>();
        existingData.add(infInformation1);
        existingData.add(infInformation2);
        List<InfInformation> newData = new ArrayList<>(existingData);

        when(infInformationEntityService.findByCityAndLocalDate(EnumCity.London, localDate1)).thenReturn(infInformation1);
        when(infInformationEntityService.findByCityAndLocalDate(EnumCity.London, localDate2)).thenReturn(infInformation2);

        InfInformationDataDto infInformationDataDto = mock(InfInformationDataDto.class);

        List<InfInformationResultDto> expectedResult = new ArrayList<>();

        InfInformationResultDto infInformationResultDto1 = mock(InfInformationResultDto.class);
        InfInformationResultDto infInformationResultDto2 = mock(InfInformationResultDto.class);
        expectedResult.add(infInformationResultDto1);
        expectedResult.add(infInformationResultDto2);

        when(infInformationDataDto.getResults()).thenReturn(expectedResult);


        when(infInformationDataDtoConverter.convertToInfInformationDataDto(newData)).thenReturn(infInformationDataDto);

        InfInformationDataDto result = infInformationService.queryByCityAndStartDateAndEndDate(EnumCity.London, localDate1, localDate2);

        assertEquals(2, result.getResults().size());
    }
    
    @Test
    void shouldReturnAllDataFromAPICorrectly(){
        LocalDate localDate1 = LocalDate.of(2022, 5, 12);
        LocalDate localDate2 = LocalDate.of(2022, 5, 13);

        DatePollutionDto datePollutionDto1 = mock(DatePollutionDto.class);
        DatePollutionDto datePollutionDto2 = mock(DatePollutionDto.class);

        when(restTemplateService.getPollutionInformationFromAPI(EnumCity.London, localDate1)).thenReturn(datePollutionDto1);
        when(restTemplateService.getPollutionInformationFromAPI(EnumCity.London, localDate2)).thenReturn(datePollutionDto2);

        InfInformation infInformation1 = mock(InfInformation.class);
        InfInformation infInformation2 = mock(InfInformation.class);

        when(infInformation1.getLocalDate()).thenReturn(localDate1);
        when(infInformation2.getLocalDate()).thenReturn(localDate2);

        when(infInformationSaveDtoConverter.convertToInfInformation(datePollutionDto1)).thenReturn(infInformation1);
        when(infInformationSaveDtoConverter.convertToInfInformation(datePollutionDto2)).thenReturn(infInformation2);

        List<InfInformation> results = new ArrayList<>();
        results.add(infInformation1);
        results.add(infInformation2);

        List<InfInformationResultDto> resultsDto = new ArrayList<>();
        InfInformationResultDto infInformationResultDto1 = mock(InfInformationResultDto.class);
        InfInformationResultDto infInformationResultDto2 = mock(InfInformationResultDto.class);


        resultsDto.add(infInformationResultDto1);
        resultsDto.add(infInformationResultDto2);

        InfInformationDataDto infInformationDataDto = mock(InfInformationDataDto.class);
        when(infInformationDataDto.getResults()).thenReturn(resultsDto);

        when(infInformationDataDtoConverter.convertToInfInformationDataDto(results)).thenReturn(infInformationDataDto);

        InfInformationDataDto result = infInformationService.queryByCityAndStartDateAndEndDate(EnumCity.London, localDate1, localDate2);

        assertEquals(2, result.getResults().size());
    }

    @Test
    void shouldDeleteByCityAndStartDateAndEndDate(){
        LocalDate localDate1 = LocalDate.of(2022, 5, 12);
        LocalDate localDate2 = LocalDate.of(2022, 5, 13);

        assertDoesNotThrow(() -> infInformationService.deleteByCityAndBetweenDates(EnumCity.London, localDate1, localDate2));
    }
    
    
}