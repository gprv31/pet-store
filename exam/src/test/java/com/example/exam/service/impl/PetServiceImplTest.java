package com.example.exam.service.impl;

import com.example.exam.client.ExternalPetClient;
import com.example.exam.model.PetRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {
    @Mock
    private ExternalPetClient client;

    @InjectMocks
    private PetServiceImpl service;

    @Test
    void shouldReturnPetFromClient() {
        PetRecord pet = new PetRecord(1L, "Firulais", "AVAILABLE");
        when(client.findPetById(1L)).thenReturn(pet);
        PetRecord result = service.getPetById(1L);
        assertEquals(pet, result);
        verify(client).findPetById(1L);
    }
}