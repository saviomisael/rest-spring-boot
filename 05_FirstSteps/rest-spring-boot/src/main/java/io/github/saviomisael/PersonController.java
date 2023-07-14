package io.github.saviomisael;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import io.github.saviomisael.models.Person;
import io.github.saviomisael.services.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService service;
	
	@GetMapping("/person/{id}")
	public Person getById(@PathVariable("id") long id) {
		var person = service.findById(id);
		person.add(linkTo(methodOn(PersonController.class).getById(id)).withSelfRel());
		
		return person;
	}
	
	@GetMapping("/person")
	public List<Person> getAll() {
		var persons = service.findAll();
		
		persons.forEach(x -> x.add(linkTo(methodOn(PersonController.class).getById(x.getId())).withSelfRel()));
		
		return persons;
	}
	
	@PostMapping("/person")
	public ResponseEntity<Person> create(@RequestBody Person person) {
		var personFromDb = service.create(person);
		personFromDb.add(linkTo(methodOn(PersonController.class).getById(personFromDb.getId())).withSelfRel());
		return ResponseEntity.status(HttpStatus.CREATED).body(personFromDb);
	}
	
	@PutMapping("/person/{id}")
	public Person updateById(@PathVariable("id") long id, @RequestBody Person person) {
		person.setId(id);
		var personFromDb = service.update(person);
		
		personFromDb.add(linkTo(methodOn(PersonController.class).getById(personFromDb.getId())).withSelfRel());
		
		return personFromDb;
	}
	
	@DeleteMapping("/person/{id}")	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
