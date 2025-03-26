package com.numberconversion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ConversionServiceImplTest {

    @InjectMocks
    private ConversionServiceImpl conversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void convertToRoman_validInput_returnsCorrectRomanNumeral() throws Exception {
        assertEquals("I", conversionService.convertToRoman(1).getOutput());
        assertEquals("IV", conversionService.convertToRoman(4).getOutput());
        assertEquals("V", conversionService.convertToRoman(5).getOutput());
        assertEquals("IX", conversionService.convertToRoman(9).getOutput());
        assertEquals("X", conversionService.convertToRoman(10).getOutput());
        assertEquals("XL", conversionService.convertToRoman(40).getOutput());
        assertEquals("L", conversionService.convertToRoman(50).getOutput());
        assertEquals("XC", conversionService.convertToRoman(90).getOutput());
        assertEquals("C", conversionService.convertToRoman(100).getOutput());
        assertEquals("CD", conversionService.convertToRoman(400).getOutput());
        assertEquals("D", conversionService.convertToRoman(500).getOutput());
        assertEquals("CM", conversionService.convertToRoman(900).getOutput());
        assertEquals("M", conversionService.convertToRoman(1000).getOutput());
        assertEquals("MMMCMXCIX", conversionService.convertToRoman(3999).getOutput());
    }

    @Test
    void convertToRoman_invalidInputLessThan1_throwsException() {
        Exception exception = assertThrows(Exception.class, () -> {
            conversionService.convertToRoman(0);
        });
        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    void convertToRoman_invalidInputGreaterThan3999_throwsException() {
        Exception exception = assertThrows(Exception.class, () -> {
            conversionService.convertToRoman(4000);
        });
        assertEquals("Invalid input", exception.getMessage());
    }
}