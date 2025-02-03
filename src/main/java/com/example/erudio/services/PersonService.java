package com.example.erudio.services;

import java.util.List;

import com.example.erudio.domain.dtos.PersonDTO;

public interface PersonService {

    PersonDTO findById(Long id);

    List<PersonDTO> findAll();

    PersonDTO create(PersonDTO person);

    PersonDTO update(PersonDTO person);

    void deleteById(Long id);

}
