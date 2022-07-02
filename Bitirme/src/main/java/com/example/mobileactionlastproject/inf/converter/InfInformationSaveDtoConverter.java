package com.example.mobileactionlastproject.inf.converter;

import com.example.mobileactionlastproject.inf.dto.InfInformationSaveDto;
import com.example.mobileactionlastproject.rst.dto.DatePollutionDto;
import org.springframework.stereotype.Service;

@Service
public class InfInformationSaveDtoConverter {

    public InfInformationSaveDto convertToInfInformationSaveDto(DatePollutionDto datePollutionDto) {
        InfInformationSaveDto infInformationSaveDto = new InfInformationSaveDto();
        infInformationSaveDto.setCity(datePollutionDto.getCity());
        infInformationSaveDto.setLocalDate(datePollutionDto.getLocalDate());
        infInformationSaveDto.setCOPollution(datePollutionDto.getPollutionInformationDto().getCo());
        infInformationSaveDto.setO3Pollution(datePollutionDto.getPollutionInformationDto().getO3());
        infInformationSaveDto.setSO2Pollution(datePollutionDto.getPollutionInformationDto().getSo2());
        return infInformationSaveDto;
    }
}
