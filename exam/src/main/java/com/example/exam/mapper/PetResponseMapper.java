package com.example.exam.mapper;

import com.example.exam.model.PetRecord;
import com.example.model.Pet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetResponseMapper {

    Pet toDomain(PetRecord petRecord);
}
