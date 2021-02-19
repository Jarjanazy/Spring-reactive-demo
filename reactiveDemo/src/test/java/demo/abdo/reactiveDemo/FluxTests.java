package demo.abdo.reactiveDemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxTests {
    @Test
    void bufferTest(){
        Flux.just(1, 2, 3, 4, 5, 6).
                buffer(3).
                subscribe(System.out::println);// prints [1, 2, 3] and [4, 5, 6]
    }
    @Test
    void mapTest(){
        Flux.just("hey", "hi", "hola").
                map(String::toUpperCase).
                subscribe(System.out::println); // prints HEY HI HOLA
    }
    @Test
    void errorTest(){
        Flux.error(RuntimeException::new).
                doOnError(System.out::println). // prints java.lang.RuntimeException
                subscribe((e) -> System.out.println("Done without error"));
    }
}
