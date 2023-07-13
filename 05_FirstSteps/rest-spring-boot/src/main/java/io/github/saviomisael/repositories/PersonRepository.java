package io.github.saviomisael.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saviomisael.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> { }
