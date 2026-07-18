package com.example.exam.controller;

import com.example.exam.mapper.PetResponseMapper;
import com.example.exam.model.PetRecord;
import com.example.exam.service.PetService;
import com.example.model.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PetControllerImplTest {
    @Mock
    private PetService petService;

    @Mock
    private PetResponseMapper petResponseMapper;

    @InjectMocks
    private PetControllerImpl controller;

    @Test
    void shouldReturnPetWhenPetExists() {
        Long petId = 1L;
        PetRecord petRecord = new PetRecord(1L, "Rocky", "DOG");
        Pet responsePet = new Pet();

        when(petService.getPetById(petId)).thenReturn(petRecord);
        when(petResponseMapper.toDomain(petRecord)).thenReturn(responsePet);

        ResponseEntity<Pet> response = controller.getPetById(petId);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(responsePet, response.getBody());

        verify(petService).getPetById(petId);
        verify(petResponseMapper).toDomain(petRecord);
    }
}