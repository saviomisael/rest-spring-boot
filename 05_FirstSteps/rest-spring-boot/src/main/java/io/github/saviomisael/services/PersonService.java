package io.github.saviomisael.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import io.github.saviomisael.models.Person;

@Service
public class PersonService {
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public Person findById(String id) {
		logger.info("Finding one person!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Savio");
		person.setLastName("Moreira");
		person.setAddress("Campinas, São Paulo");
		person.setGender("Male");
		return person;
	}
	
	public List<Person> findAll() {
		logger.info("Finding all persons!");
		
		List<Person> allPersons = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			Person person = new Person();
			person.setId(counter.incrementAndGet());
			person.setFirstName("Person first name: " + i);
			person.setLastName("Person last name: " + i);
			person.setAddress("Some address " + i);
			person.setGender(i % 2 == 0 ? "Male" : "Female");
			allPersons.add(person);
		}
		
		return allPersons;
	}
	
	public Person create(Person person) {
		logger.info("Creating a person!");
		
		return person;
	}
	
	public Person update(Person person) {
		return person;
	}

	public void deleteById(long id) {
	}
}
