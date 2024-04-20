/*

package PMV.HW3.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyMetric {


    private final Counter showBookCount;


    private final Counter showNumberOfFailures;


    public MyMetric(MeterRegistry meterRegistry) {
        showBookCount = meterRegistry.counter("custom_BookCount");
        showNumberOfFailures = meterRegistry.counter("custom_NumberOfFailures");
    }




    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void schedulingBookCount() {
        showBookCount.increment();

    }

    @Scheduled(fixedDelay = 1000, initialDelay = 0)
    public void schedulingNumberOfFailures() {
        showNumberOfFailures.increment();

    }

}


*/
