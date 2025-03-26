package com.numberconversion;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.numberconversion.RomanConstants.NUMBERS;
import static com.numberconversion.RomanConstants.SYMBOLS;

@Service
@Log4j2
public class ConversionServiceImpl implements ConversionService{

     public ConversionResponse convertToRoman(int input) throws Exception {
         if (!isValidInput(input)) {
             log.error("Invalid input: {}, only values allowed are 1 to 3999", input);
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
         ConversionResponse response = new ConversionResponse(input, result.toString());
         log.info("Roman numeral response: {input: {}, output: {}}", response.getInput(), response.getOutput());
        return response ;
    }

    private boolean isValidInput(int value) {
        return value >= 1 && value <= 3999;
    }
}
