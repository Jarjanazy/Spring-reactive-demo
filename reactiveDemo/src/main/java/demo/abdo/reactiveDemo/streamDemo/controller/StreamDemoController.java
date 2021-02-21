package demo.abdo.reactiveDemo.streamDemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.Random;


import java.time.Duration;

@RestController
public class StreamDemoController {
    /* since the default return type is JSON, it only returns when the four elements are all done, i.e. after 4 seconds */
    @GetMapping("/integers")
    public Flux<Integer> getIntegers(){
        return Flux.
                just(1, 2, 3, 4).
                delayElements(Duration.ofSeconds(1));
    }

    /* this will return each element when ready, i.e. it will return one element on every second */
    @GetMapping(value = "integers/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getIntegersStream(){
        return Flux.
                just(1, 2, 3, 4).
                delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(value = "integers/stream/infinite", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> getIntegersInfiniteStream(){
        return Flux.
                interval(Duration.ofSeconds(1));
    }
}
