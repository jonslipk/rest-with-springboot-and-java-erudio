package com.example.erudio.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.erudio.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
