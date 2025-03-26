package com.numberconversion;

import org.springframework.stereotype.Service;

import static com.numberconversion.RomanConstants.NUMBERS;
import static com.numberconversion.RomanConstants.SYMBOLS;

@Service
public class ConversionServiceImpl implements ConversionService{

     public ConversionResponse convertToRoman(int input) throws Exception {
         if (!isValidInput(input)) {
             throw new Exception("Invalid input");
         }
        StringBuilder result = new StringBuilder();
         int currentValue = input;
         for (int i = 0; i < NUMBERS.length; i++) {
            while (currentValue >= NUMBERS[i]) {
                currentValue -= NUMBERS[i];
                result.append(SYMBOLS[i]);
            }
        }
        return new ConversionResponse(input, result.toString());
    }

    private boolean isValidInput(int value) {
        return value >= 1 && value <= 3999;
    }
}
