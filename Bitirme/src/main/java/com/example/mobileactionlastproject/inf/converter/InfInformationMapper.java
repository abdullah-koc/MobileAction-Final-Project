package com.example.mobileactionlastproject.inf.converter;

import com.example.mobileactionlastproject.inf.dto.InfInformationSaveDto;
import com.example.mobileactionlastproject.inf.entity.InfInformation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InfInformationMapper {

    InfInformationMapper INSTANCE = Mappers.getMapper(InfInformationMapper.class);

    InfInformation convertToInfInformation(InfInformationSaveDto infInformationSaveDto);

}
