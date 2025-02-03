package com.example.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.erudio.api.PersonController;
import com.example.erudio.domain.Person;
import com.example.erudio.domain.dtos.PersonDTO;
import com.example.erudio.domain.repositories.PersonRepository;
import com.example.erudio.services.exceptions.ObjectNotFoundException;

@Service
public class PersonServiceImpl implements PersonService {

    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());

    private final PersonRepository repository;

    private final ModelMapper mapper;

    public PersonServiceImpl(PersonRepository repository, ModelMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public PersonDTO findById(Long id) {
        logger.info("finding one person.");
        Person person = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Person not Found!"));
        PersonDTO dto = mapper.map(person, PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    @Override
    public List<PersonDTO> findAll() {
        logger.info("finding All.");
        var persons = repository.findAll().stream().map(person -> mapper.map(person, PersonDTO.class))
                .toList();

        persons.stream().forEach(
                person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel()));

        return persons;
    }

    @Override
    public PersonDTO create(PersonDTO person) {
        logger.info("create  person.");
        Person personSaved = repository.save(mapper.map(person, Person.class));
        PersonDTO dto = mapper.map(personSaved, PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    @Override
    public PersonDTO update(PersonDTO person) {
        logger.info("update  person.");
        findById(person.getId());
        Person personSaved = repository.save(mapper.map(person, Person.class));
        PersonDTO dto = mapper.map(personSaved, PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting...");
        findById(id);
        repository.deleteById(id);
    }

}
