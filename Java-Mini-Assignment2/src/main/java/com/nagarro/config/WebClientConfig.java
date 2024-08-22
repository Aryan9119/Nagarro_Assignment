package com.nagarro.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Configuration class for creating instances of {WebClient} customized for different APIs.
 */
@Configuration
public class WebClientConfig {

    private static final int API1_CONNECTION_TIMEOUT = 2000;
    private static final int API1_READ_TIMEOUT = 2000;
    private static final int API1_WRITE_TIMEOUT = 2000;

    private static final int API2_CONNECTION_TIMEOUT = 1000;
    private static final int API2_READ_TIMEOUT = 1000;
    private static final int API2_WRITE_TIMEOUT = 1000;

    private static final int API3_CONNECTION_TIMEOUT = 1000;
    private static final int API3_READ_TIMEOUT = 1000;
    private static final int API3_WRITE_TIMEOUT = 1000;

    /**
     * Creates and configures a {WebClient} bean for API1.
     *
     * @return A configured {WebClient} instance for API1.
     */
    @Bean(name = "webClientAPI1")
    public WebClient webClientAPI1() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, API1_CONNECTION_TIMEOUT)
                .responseTimeout(Duration.ofMillis(API1_READ_TIMEOUT))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(API1_READ_TIMEOUT, TimeUnit.MILLISECONDS)))
                .doOnConnected(conn -> conn.addHandlerLast(new WriteTimeoutHandler(API1_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    /**
     * Creates and configures a {WebClient} bean for API2.
     *
     * @return A configured {WebClient} instance for API2.
     */
    @Bean(name = "webClientAPI2")
    public WebClient webClientAPI2() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, API2_CONNECTION_TIMEOUT)
                .responseTimeout(Duration.ofMillis(API2_READ_TIMEOUT))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(API2_READ_TIMEOUT, TimeUnit.MILLISECONDS)))
                .doOnConnected(conn -> conn.addHandlerLast(new WriteTimeoutHandler(API2_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    /**
     * Creates and configures a {WebClient} bean for API3.
     *
     * @return A configured {WebClient} instance for API3.
     */
    @Bean(name = "webClientAPI3")
    public WebClient webClientAPI3() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, API3_CONNECTION_TIMEOUT)
                .responseTimeout(Duration.ofMillis(API3_READ_TIMEOUT))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(API3_READ_TIMEOUT, TimeUnit.MILLISECONDS)))
                .doOnConnected(conn -> conn.addHandlerLast(new WriteTimeoutHandler(API3_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
