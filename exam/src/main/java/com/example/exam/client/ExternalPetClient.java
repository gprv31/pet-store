package com.example.exam.client;

import com.example.exam.exception.ExternalApiException;
import com.example.exam.exception.PetNotFoundException;
import com.example.exam.model.PetRecord;
import com.example.external.api.PetApi;
import lombok.AllArgsConstructor;
import com.example.exam.mapper.PetRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalPetClient {

    private final PetApi petApi;
    private final PetRecordMapper petRecordMapper;

    @Retryable(retryFor = ExternalApiException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public PetRecord findPetById(Long id) {
        try {
            return petRecordMapper.toDomain(petApi.getPetById(id));
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.warn("ExternalPetClient.findPetById - Pet not found in external API. petId={}", id);
                throw new PetNotFoundException(id);
            }
            log.error("ExternalPetClient.findPetById - External client error occurred", ex);
            throw new ExternalApiException("Client error", ex);
        } catch (HttpServerErrorException ex) {
            log.error("ExternalPetClient.findPetById - External server error occurred", ex);
            throw new ExternalApiException("Server error", ex);
        } catch (Exception e) {
            log.error("ExternalPetClient.findPetById - Unexpected error occurred", e);
            throw new ExternalApiException("Unexpected error calling external API", e);
        }
    }
}
