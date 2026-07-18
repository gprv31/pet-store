package com.example.exam.controller;

import com.example.api.PetInformationControllerApi;
import com.example.exam.service.PetService;
import com.example.model.Pet;
import lombok.AllArgsConstructor;
import com.example.exam.mapper.PetResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PetControllerImpl implements PetInformationControllerApi {

    private final PetService petService;
    private final PetResponseMapper petResponseMapper;

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return ResponseEntity.ok(petResponseMapper.toDomain(petService.getPetById(petId)));
    }
}