package com.example.exam.service.impl;

import com.example.exam.client.ExternalPetClient;
import com.example.exam.model.PetRecord;
import com.example.exam.service.PetService;
import com.example.model.Pet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final ExternalPetClient externalPetClient;

    @Override
    public PetRecord getPetById(Long id) {
        PetRecord petFound = externalPetClient.findPetById(id);
        log.info("PetServiceImpl.getPetById - Pet found: {} ", petFound);
        return petFound;
    }
}
