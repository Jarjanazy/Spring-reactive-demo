package demo.abdo.reactiveDemo.reactorTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {
    @Test
    void mapTest(){
        Mono<String> mono = Mono.just("Hey").map(String::toLowerCase);
        StepVerifier.
                create(mono).
                expectNext("hey").
                verifyComplete();
    }

    @Test
    void errorTest(){
        StepVerifier.
                create(Mono.error(new RuntimeException("Error!!!"))).
                expectError(RuntimeException.class).
                verify();
    }

    @Test
    void whenMonoIsEmpty_getSwitchMono(){
        Mono<String> emptyMono = Mono.empty();
        Mono<String> switchMono = emptyMono.switchIfEmpty(Mono.just("test"));

        StepVerifier.create(switchMono).expectNext("test").verifyComplete();
    }

    @Test
    void whenMonoIsNotEmpty_getMono(){
        Mono<String> emptyMono = Mono.just("original");
        Mono<String> switchMono = emptyMono.switchIfEmpty(Mono.just("test"));

        StepVerifier.create(switchMono).expectNext("original").verifyComplete();
    }
}
