package com.example.exam.mapper;

import com.example.exam.model.PetRecord;
import com.example.external.model.Pet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PetRecordMapperTest {
    private final PetRecordMapper mapper = new PetRecordMapperImpl();

    @Test
    void shouldMapExternalPetToDomain() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Rocky");

        PetRecord result = mapper.toDomain(pet);
        assertEquals(1L, result.id());
        assertEquals("Rocky", result.name());
    }
}