package demo.abdo.reactiveDemo.reactorTest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                expectNext("nope", "nada").
                verifyComplete(); // the actual subscription to the FLux
    }

    @Test
    void errorEventTest(){
        Flux<String> fluxOfStringsAndError =
                Flux.just("I", "am", "abdo").concatWith(Flux.error(RuntimeException::new));
        StepVerifier.
                create(fluxOfStringsAndError).
                expectNext("I", "am", "abdo").
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
                expectNext("I", "am" , "not", "abdo").
                expectErrorMessage("error!!!").
                verify();
    }

    @Test
    void verifyEmittedCountForFluxOfListsTest(){
        Flux<List<String>> fluxOfLists =
                Flux.just(Arrays.asList("1", "2"), Arrays.asList("3", "4"));

        StepVerifier.create(fluxOfLists).expectNextCount(2).verifyComplete();
    }

    @Test
    void flatMapEffect(){
        Flux<Mono<Integer>> fluxOfMonos = Flux.range(1, 3).map(Mono::just);
        // flatMap subscribes to the Monos and get their Integer values into the Flux
        Flux<Integer> fluxOfIntegers = Flux.range(1, 3).flatMap(Mono::just);
    }

}
