package com.example.exam.config;

import com.example.external.ApiClient;
import com.example.external.api.PetApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class PetClientConfig {
    @Value("${api.pet-store.base-url}")
    private String baseUrl;

    @Value("${api.pet-store.connect-timeout-ms}")
    private int connectTimeout;

    @Value("${api.pet-store.read-timeout-ms}")
    private int readTimeout;

    @Bean
    public ApiClient petApiClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);

        RestClient restClient = RestClient.builder()
                .requestFactory(factory)
                .build();

        ApiClient apiClient = new ApiClient(restClient);
        apiClient.setBasePath(baseUrl);

        return apiClient;
    }

    @Bean
    public PetApi petApi(ApiClient petApiClient) {
        return new PetApi(petApiClient);
    }
}


