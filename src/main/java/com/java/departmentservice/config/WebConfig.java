package com.java.departmentservice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.departmentservice.client.CustomerClient;
import com.netflix.discovery.converters.Auto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfig {

    @Autowired
    LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Bean
    WebClient webClient(ObjectMapper objectMapper) {
        return WebClient.builder()
                .baseUrl("https://employee-service").filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @SneakyThrows
    @Bean
    CustomerClient postClient(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(CustomerClient.class);
    }
}
