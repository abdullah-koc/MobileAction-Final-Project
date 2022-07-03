package com.example.mobileactionlastproject.inf.converter;

import com.example.mobileactionlastproject.inf.entity.InfInformation;
import com.example.mobileactionlastproject.rst.dto.DatePollutionDto;
import org.springframework.stereotype.Service;

@Service
public class InfInformationConverter {

    public InfInformation convertToInfInformation(DatePollutionDto datePollutionDto) {
        InfInformation infInformation = new InfInformation();
        infInformation.setCity(datePollutionDto.getCity());
        infInformation.setLocalDate(datePollutionDto.getLocalDate());
        infInformation.setCOPollution(datePollutionDto.getPollutionInformationDto().getCo());
        infInformation.setO3Pollution(datePollutionDto.getPollutionInformationDto().getO3());
        infInformation.setSO2Pollution(datePollutionDto.getPollutionInformationDto().getSo2());
        return infInformation;
    }
}
