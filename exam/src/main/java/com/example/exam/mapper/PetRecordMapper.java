package com.example.exam.mapper;

import com.example.exam.model.PetRecord;
import org.mapstruct.Mapper;
import com.example.external.model.Pet;

@Mapper(componentModel = "spring")
public interface PetRecordMapper {

    PetRecord toDomain(Pet pet);
}
