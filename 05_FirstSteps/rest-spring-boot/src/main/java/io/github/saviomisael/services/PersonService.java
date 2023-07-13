package io.github.saviomisael.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.saviomisael.exceptions.ResourceNotFoundException;
import io.github.saviomisael.models.Person;
import io.github.saviomisael.repositories.PersonRepository;

@Service
public class PersonService {
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	@Autowired
	PersonRepository repository;

	public Person findById(long id) {
		logger.info("Finding one person!");

		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
	}

	public List<Person> findAll() {
		logger.info("Finding all persons!");

		return repository.findAll();
	}

	public Person create(Person person) {
		logger.info("Creating a person!");

		return repository.save(person);
	}

	public Person update(Person person) {
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		return repository.save(entity);
	}

	public void deleteById(long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		
		repository.delete(entity);
	}
}
