package com.numberconversion;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {

    private final MeterRegistry meterRegistry;
    private final Timer processingTimer;
    private final ConversionService conversionService;

    public ConversionController(MeterRegistry meterRegistry, ConversionService conversionService) {
        this.meterRegistry = meterRegistry;
        this.processingTimer = meterRegistry.timer("my_app.processing_time");
        this.conversionService = conversionService;
    }

    @GetMapping("/romannumeral")
    @Counted
    public ConversionResponse convertNumberToRoman(@RequestParam("query") int query) throws Exception {

        return conversionService.convertToRoman(query);
    }


}
