package com.example.erudio.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.example.erudio.domain.Person;
import com.example.erudio.domain.dtos.PersonDTO;

public class DozerMapper {

    private DozerMapper() {
        // Private constructor to hide the implicit public one
    }

    private static ModelMapper mapper = new ModelMapper();

    // Mapear campos de nomes diferentes, mas com o mesmo proposito
    static {
        mapper.createTypeMap(
                Person.class,
                PersonDTO.class)
                .addMapping(Person::getId, PersonDTO::setId);// PersonDTO::setKEY
        mapper.createTypeMap(
                PersonDTO.class,
                Person.class)
                .addMapping(PersonDTO::getId, Person::setId);// PersonDTO::setKEY
    }

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

}
