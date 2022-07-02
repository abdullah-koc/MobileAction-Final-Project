package com.example.mobileactionlastproject.util;

import com.example.mobileactionlastproject.inf.enums.EnumPollutionCategory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EnumPollutionCategoryUtilTest {

    @Test
    void shouldHazardousWhenConvertCOToEnumValueIs400() {
        assertEquals(EnumPollutionCategory.Hazardous, EnumPollutionCategoryUtil.convertCOToEnum(new BigDecimal(400)));
    }

    @Test
    void shouldHazardousWhenConvertO3ToEnumValueIs749() {
        assertEquals(EnumPollutionCategory.Hazardous, EnumPollutionCategoryUtil.convertO3ToEnum(new BigDecimal(749)));
    }

    @Test
    void shouldHazardousWhenConvertSO2ToEnumValueIs2000() {
        assertEquals(EnumPollutionCategory.Hazardous, EnumPollutionCategoryUtil.convertSO2ToEnum(new BigDecimal(2000)));
    }

    @Test
    void shouldGoodWhenConvertCOToEnumValueIs25() {
        assertEquals(EnumPollutionCategory.Good, EnumPollutionCategoryUtil.convertCOToEnum(new BigDecimal(25)));
    }

    @Test
    void shouldGoodWhenConvertO3ToEnumValueIs50() {
        assertEquals(EnumPollutionCategory.Good, EnumPollutionCategoryUtil.convertO3ToEnum(new BigDecimal(50)));
    }

    @Test
    void shouldGoodWhenConvertSO2ToEnumValueIs40() {
        assertEquals(EnumPollutionCategory.Good, EnumPollutionCategoryUtil.convertSO2ToEnum(new BigDecimal(40)));
    }

    @Test
    void shouldSatisfactoryWhenConvertCOToEnumValueIs60() {
        assertEquals(EnumPollutionCategory.Satisfactory, EnumPollutionCategoryUtil.convertCOToEnum(new BigDecimal(60)));
    }

    @Test
    void shouldSatisfactoryWhenConvertO3ToEnumValueIs100() {
        assertEquals(EnumPollutionCategory.Satisfactory, EnumPollutionCategoryUtil.convertO3ToEnum(new BigDecimal(100)));
    }

    @Test
    void shouldSatisfactoryWhenConvertSO2ToEnumValueIs80() {
        assertEquals(EnumPollutionCategory.Satisfactory, EnumPollutionCategoryUtil.convertSO2ToEnum(new BigDecimal(80)));
    }

    @Test
    void shouldModerateWhenConvertCOToEnumValueIs120() {
        assertEquals(EnumPollutionCategory.Moderate, EnumPollutionCategoryUtil.convertCOToEnum(new BigDecimal(120)));
    }

    @Test
    void shouldModerateWhenConvertO3ToEnumValueIs168() {
        assertEquals(EnumPollutionCategory.Moderate, EnumPollutionCategoryUtil.convertO3ToEnum(new BigDecimal(168)));
    }

    @Test
    void shouldModerateWhenConvertSO2ToEnumValueIs380() {
        assertEquals(EnumPollutionCategory.Moderate, EnumPollutionCategoryUtil.convertSO2ToEnum(new BigDecimal(380)));
    }

    @Test
    void shouldPoorWhenConvertCOToEnumValueIs180() {
        assertEquals(EnumPollutionCategory.Poor, EnumPollutionCategoryUtil.convertCOToEnum(new BigDecimal(180)));
    }

    @Test
    void shouldPoorWhenConvertO3ToEnumValueIs200() {
        assertEquals(EnumPollutionCategory.Poor, EnumPollutionCategoryUtil.convertO3ToEnum(new BigDecimal(200)));
    }

    @Test
    void shouldPoorWhenConvertSO2ToEnumValueIs800() {
        assertEquals(EnumPollutionCategory.Poor, EnumPollutionCategoryUtil.convertSO2ToEnum(new BigDecimal(800)));
    }

    @Test
    void shouldSevereWhenConvertCOToEnumValueIs220() {
        assertEquals(EnumPollutionCategory.Severe, EnumPollutionCategoryUtil.convertCOToEnum(new BigDecimal(220)));
    }

    @Test
    void shouldSevereWhenConvertO3ToEnumValueIs300() {
        assertEquals(EnumPollutionCategory.Severe, EnumPollutionCategoryUtil.convertO3ToEnum(new BigDecimal(300)));
    }

    @Test
    void shouldSevereWhenConvertSO2ToEnumValueIs900() {
        assertEquals(EnumPollutionCategory.Severe, EnumPollutionCategoryUtil.convertSO2ToEnum(new BigDecimal(900)));
    }
}