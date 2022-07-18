package com.joostdebos.udemy.hello;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/hello")
public class HelloWorldController {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);
    private final MyService service;

    private final String helloFromConfig;

    private final HelloWorldTranslationConfig translationConfig;

    public HelloWorldController(MyService service, @Property(name = "hello.world.message") String helloFromConfig, HelloWorldTranslationConfig translationConfig) {
        log.debug("hello world controller started");
        this.service = service;
        this.helloFromConfig = helloFromConfig;
        this.translationConfig = translationConfig;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public String helloWorld() {
        log.debug("called the hello world api");
        return service.helloFromService();
    }

    @Get(uri = "/config", produces = MediaType.TEXT_PLAIN)
    public String helloConfig() {
        log.debug("return from config");
        return helloFromConfig;
    }

    @Get(uri = "/translation", produces = MediaType.APPLICATION_JSON)
    public HelloWorldTranslationConfig helloFromTranslation() {
        return translationConfig;
    }
}
