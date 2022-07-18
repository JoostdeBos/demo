package com.joostdebos.udemy.hello;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Singleton
public class SecondHelloWorldService implements MyService {

    @Override
    public String helloFromService() {
        return "hello from micronaut second service";
    }
}
