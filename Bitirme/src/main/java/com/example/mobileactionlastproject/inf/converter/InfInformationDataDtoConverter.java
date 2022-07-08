package com.example.mobileactionlastproject.inf.converter;

import com.example.mobileactionlastproject.inf.dto.*;
import com.example.mobileactionlastproject.inf.dto.category.InfInformationCODto;
import com.example.mobileactionlastproject.inf.dto.category.InfInformationO3Dto;
import com.example.mobileactionlastproject.inf.dto.category.InfInformationSO2Dto;
import com.example.mobileactionlastproject.inf.entity.InfInformation;
import com.example.mobileactionlastproject.util.EnumPollutionCategoryUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

@Service
public class InfInformationDataDtoConverter {

    public InfInformationDataDto convertToInfInformationDataDto(List<InfInformation> infInformationList) {

        if(infInformationList == null){
            return null;
        }
        InfInformationDataDto infInformationDataDto = new InfInformationDataDto();
        List<InfInformationResultDto> infInformationResultDtoList = new ArrayList<>();


        for(InfInformation infInformation : infInformationList){
            InfInformationResultDto infInformationResultDto = new InfInformationResultDto();

            infInformationResultDto.setDate(infInformation.getLocalDate());
            infInformationResultDto.setCategories(getCategories(infInformation));

            infInformationResultDtoList.add(infInformationResultDto);
        }

        infInformationDataDto.setCity(infInformationList.get(0).getCity());
        infInformationDataDto.setResults(infInformationResultDtoList);

        return infInformationDataDto;
    }


    /**
     * This method is used to get categories from infInformation object.
     * For each pollution type, district Dtos are used in order to make output look like in the assignment.
     * @param infInformation {@link InfInformation}
     * @return {@link List<Object>}
     */
    private List<Object> getCategories(InfInformation infInformation) {
        List<Object> categories = new ArrayList<>();

        InfInformationCODto infInformationCODto = new InfInformationCODto();
        infInformationCODto.setCO(EnumPollutionCategoryUtil.convertCOToEnum(infInformation.getCOPollution()));
        categories.add(infInformationCODto);

        InfInformationO3Dto infInformationO3Dto = new InfInformationO3Dto();
        infInformationO3Dto.setO3(EnumPollutionCategoryUtil.convertO3ToEnum(infInformation.getO3Pollution()));
        categories.add(infInformationO3Dto);


        InfInformationSO2Dto infInformationSO2Dto = new InfInformationSO2Dto();
        infInformationSO2Dto.setSO2(EnumPollutionCategoryUtil.convertSO2ToEnum(infInformation.getSO2Pollution()));
        categories.add(infInformationSO2Dto);
        return categories;
    }

}













