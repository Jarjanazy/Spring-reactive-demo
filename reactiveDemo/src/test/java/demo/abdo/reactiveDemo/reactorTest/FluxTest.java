package demo.abdo.reactiveDemo.reactorTest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    void filterAndMapTest(){
        Flux<String> fluxOfStrings =
                Flux.just("Nope", "No", "Nada").
                        filter(s -> s.length() > 2).
                        map(String::toLowerCase);

        // subscribes to the FLux and handles the values emitted by it
        StepVerifier.
                create(fluxOfStrings).
                expectNext("nope").
                expectNext("nada").
                verifyComplete(); // the actual subscription to the FLux
    }


}
