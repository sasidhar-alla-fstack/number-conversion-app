package com.numberconversion;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConversionResponse {

    private String input;
    private String output;

    ConversionResponse(int input, String output) {
        this.input = String.valueOf(input);
        this.output = output;
    }
}
