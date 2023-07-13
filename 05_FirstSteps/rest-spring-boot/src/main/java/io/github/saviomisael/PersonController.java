package io.github.saviomisael;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.saviomisael.models.Person;
import io.github.saviomisael.services.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService service;
	
	@GetMapping("/person/{id}")
	public Person getById(@PathVariable("id") long id) {
		return service.findById(id);
	}
	
	@GetMapping("/person")
	public List<Person> getAll() {
		return service.findAll();
	}
	
	@PostMapping("/person")
	public ResponseEntity<Person> create(@RequestBody Person person) {
		var personFromDb = service.create(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(personFromDb);
	}
	
	@PutMapping("/person/{id}")
	public Person updateById(@PathVariable("id") long id, @RequestBody Person person) {
		person.setId(id);
		return service.update(person);
	}
	
	@DeleteMapping("/person/{id}")	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
