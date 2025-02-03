package com.example.erudio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.example.erudio.domain.Person;
import com.example.erudio.domain.dtos.PersonDTO;
import com.example.erudio.domain.repositories.PersonRepository;
import com.example.erudio.services.exceptions.ObjectNotFoundException;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository repository;

    @Mock
    private ModelMapper mapper;

    private Person person;
    private PersonDTO personSave;

    private PersonDTO personDTO;

    private Optional<Person> optionalPerson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personService = new PersonServiceImpl(repository, mapper);
        startedPersons();
    }

    @Test
    void findById_Sucess_With_ReturnPersonDto() {
        when(repository.findById(anyLong())).thenReturn(optionalPerson);
        when(mapper.map(any(), any())).thenReturn(person);
        when(mapper.map(any(), any())).thenReturn(personDTO);

        PersonDTO personFind = personService.findById(personDTO.getId());

        assertNotNull(personFind);
        assertEquals(PersonDTO.class, personFind.getClass());
        assertNotNull(personFind.getLinks());
        assertTrue(personFind.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
        assertEquals(personDTO.getFirstName(), personFind.getFirstName());
        assertEquals(personDTO.getLastName(), personFind.getLastName());
        assertEquals(personDTO.getAddress(), personFind.getAddress());
        assertEquals(personDTO.getGender(), personFind.getGender());

    }

    @Test
    void findByAll_Sucess_With_ReturnPersonDto() {
        when(repository.findAll()).thenReturn(List.of(person));
        when(mapper.map(any(), any())).thenReturn(person);
        when(mapper.map(any(), any())).thenReturn(personDTO);

        List<PersonDTO> listResult = personService.findAll();

        assertNotNull(listResult);
        assertEquals(PersonDTO.class, listResult.get(0).getClass());
        assertNotNull(listResult.get(0).getLinks());
        assertEquals(1, listResult.size());
        assertTrue(listResult.get(0).toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
        assertEquals(personDTO.getFirstName(), listResult.get(0).getFirstName());
        assertEquals(personDTO.getLastName(), listResult.get(0).getLastName());
        assertEquals(personDTO.getAddress(), listResult.get(0).getAddress());
        assertEquals(personDTO.getGender(), listResult.get(0).getGender());

    }

    @Test
    void findAll_With_Return_Empty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<PersonDTO> listResult = personService.findAll();

        assertNotNull(listResult);
        assertEquals(0, listResult.size());
    }

    @Test
    void findById_With_Return_Exception() {
        when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Person not Found!"));

        try {
            personService.findById(personDTO.getId());
            fail();
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Person not Found!", ex.getMessage());
        }
    }

    @Test
    void create_Sucess_With_Return_PersonDTO() {
        when(repository.save(any())).thenReturn(person);
        when(mapper.map(any(PersonDTO.class), any())).thenReturn(person);
        when(mapper.map(any(Person.class), any())).thenReturn(personDTO);

        PersonDTO result = personService.create(personSave);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(PersonDTO.class, result.getClass());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
        assertEquals(personDTO.getAddress(), result.getAddress());
        assertEquals(personDTO.getGender(), result.getGender());

    }

    @Test
    void create_With_Return_Exception() {
        when(repository.save(any())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> personService.create(personSave));
    }

    @Test
    void update_Sucess_With_Return_PersonDTO() {
        when(repository.save(any())).thenReturn(person);
        when(mapper.map(any(PersonDTO.class), any())).thenReturn(person);
        when(mapper.map(any(Person.class), any())).thenReturn(personDTO);

        PersonDTO result = personService.create(personDTO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(PersonDTO.class, result.getClass());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
        assertEquals(personDTO.getId(), result.getId());
        assertEquals(personDTO.getFirstName(), result.getFirstName());
        assertEquals(personDTO.getLastName(), result.getLastName());
        assertEquals(personDTO.getAddress(), result.getAddress());
        assertEquals(personDTO.getGender(), result.getGender());

    }

    @Test
    void update_With_Return_Exception() {
        when(repository.save(any())).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> personService.create(personDTO));
    }

    @Test
    void delete_Sucess() {
        when(repository.findById(anyLong())).thenReturn(optionalPerson);
        when(mapper.map(any(Person.class), any())).thenReturn(personDTO);
        doNothing().when(repository).deleteById(anyLong());
        assertThatCode(() -> personService.deleteById(1L)).doesNotThrowAnyException();
    }

    @Test
    void delete_Exception() {
        when(repository.findById(anyLong())).thenThrow(ObjectNotFoundException.class);

        try {
            personService.deleteById(personDTO.getId());
            fail();
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    private void startedPersons() {
        person = new Person(1L, "Jonas", "Silva", "endereco completo", "male");
        personSave = new PersonDTO(null, "Jonas", "Silva", "endereco completo", "male");
        personDTO = new PersonDTO(1L, "Jonas", "Silva", "endereco completo", "male");
        optionalPerson = Optional.of(new Person(1L, "Jonas", "Silva", "endereco completo", "male"));

    }

}
