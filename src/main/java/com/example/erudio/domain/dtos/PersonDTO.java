package com.example.erudio.domain.dtos;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO extends RepresentationModel<PersonDTO> {

    private Long id;
    @NotEmpty(message = "First name must not be empty")
    private String firstName;
    @NotEmpty(message = "Last name must not be empty")
    private String lastName;
    @NotEmpty(message = "address must not be empty")
    private String address;
    @NotEmpty(message = "gender must not be empty")
    private String gender;

}
