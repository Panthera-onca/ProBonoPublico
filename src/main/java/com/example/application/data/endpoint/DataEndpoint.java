package com.example.application.data.endpoint;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Random;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
public class DataEndpoint {
	
	@GetMapping(path = "/approval", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getIndicators() {
        Random random = new Random();
        return Flux
            .<String>generate(
                sink -> {
                    sink.next(BigDecimal.valueOf(random.nextInt(10000), 2).toString());
                }
            )
            .delayElements(Duration.ofMillis(500))
            .take(30);
    }

}
