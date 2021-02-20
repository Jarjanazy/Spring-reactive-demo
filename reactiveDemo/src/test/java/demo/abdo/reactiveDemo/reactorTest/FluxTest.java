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

    @Test
    void errorEventTest(){
        Flux<String> fluxOfStringsAndError =
                Flux.just("I", "am", "abdo").concatWith(Flux.error(RuntimeException::new));
        StepVerifier.
                create(fluxOfStringsAndError).
                expectNext("I").
                expectNext("am").
                expectNext("abdo").
                expectError(RuntimeException.class).
                verify();
    }

    @Test
    void errorEventWithMessageTest(){
        Flux<String> fluxOfStringsAndError =
                Flux.just("I", "am", "not","abdo").
                        concatWith(Flux.error(() -> new RuntimeException("error!!!")));
        StepVerifier.
                create(fluxOfStringsAndError).
                expectNext("I").
                expectNext("am").
                expectNext("not").
                expectNext("abdo").
                expectErrorMessage("error!!!").
                verify();
    }


}
