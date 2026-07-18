package com.example.exam.client;

import com.example.exam.exception.ExternalApiException;
import com.example.exam.exception.PetNotFoundException;
import com.example.exam.mapper.PetRecordMapper;
import com.example.exam.model.PetRecord;
import com.example.external.api.PetApi;
import com.example.external.model.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ExternalPetClientTest {
    @Mock
    private PetApi petApi;

    @Mock
    private PetRecordMapper mapper;

    @InjectMocks
    private ExternalPetClient client;


    @Test
    void shouldReturnPetWhenExternalApiReturnsSuccess() {
        Long id = 1L;
        Pet pet = new Pet();
        PetRecord expected = new PetRecord(1L, "Dog", "AVAILABLE");

        when(petApi.getPetById(id)).thenReturn(pet);
        when(mapper.toDomain(pet)).thenReturn(expected);


        PetRecord result = client.findPetById(id);
        assertEquals(expected, result);
        verify(petApi).getPetById(id);
    }


    @Test
    void shouldThrowNotFoundWhenExternalReturns404() {
        Long id = 10L;
        when(petApi.getPetById(id)).thenThrow(HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Not found", null, null, null));
        assertThrows(PetNotFoundException.class, () -> client.findPetById(id));
    }


    @Test
    void shouldThrowExternalApiExceptionWhenClientError() {
        when(petApi.getPetById(1L))
                .thenThrow(
                        HttpClientErrorException.create(
                                HttpStatus.BAD_REQUEST, "Bad Request", null, null, null));
        assertThrows(ExternalApiException.class, () -> client.findPetById(1L));
    }


    @Test
    void shouldThrowExternalApiExceptionWhenServerError() {
        when(petApi.getPetById(1L))
                .thenThrow(
                        HttpClientErrorException.create(
                                HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null, null, null));
        assertThrows(ExternalApiException.class, () -> client.findPetById(1L));
    }
}
