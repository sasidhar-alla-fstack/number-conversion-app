package com.numberconversion;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ConversionController {

    private final MeterRegistry meterRegistry;
    private final Timer processingTimer;
    private final Counter requestCounter;
    private final ConversionService conversionService;

    public ConversionController(MeterRegistry meterRegistry, ConversionService conversionService) {
        this.meterRegistry = meterRegistry;
        this.processingTimer = meterRegistry.timer("romannumeral_processing_time");
        this.requestCounter = meterRegistry.counter("romannumeral_request_count");
        this.conversionService = conversionService;
    }

    @GetMapping("/romannumeral")
    @Counted
    public ConversionResponse convertNumberToRoman(@RequestParam("query") int query) throws Exception {
        requestCounter.increment();
        log.info("Roman numeral input value:{}", query);
        return conversionService.convertToRoman(query);
    }


}
