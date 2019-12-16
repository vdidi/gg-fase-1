package com.zup.httprequest.httprequest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class HttpBinRunner implements CommandLineRunner {
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private final HttpService service;

    public HttpBinRunner(HttpService service) { this.service = service; }

    @Override
    public void run(String[] args) {
        LOGGER.info(() -> service.sendPostRequest());
        LOGGER.info(() -> service.sendDeleteRequest());
        LOGGER.info(() -> service.sendBearerAuthorization());
    }

}
