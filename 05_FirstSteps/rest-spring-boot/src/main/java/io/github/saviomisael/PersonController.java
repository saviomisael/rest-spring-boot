package io.github.saviomisael;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.saviomisael.models.Person;
import io.github.saviomisael.services.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService service;
	
	@GetMapping("/person/{id}")
	public Person getById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@GetMapping("/person")
	public List<Person> getAll() {
		return service.findAll();
	}
	
	@PostMapping("/person")
	public Person create(@RequestBody Person person) {
		return service.create(person);
	}
	
	@PutMapping("/person/{id}")
	public Person updateById(@PathVariable("id") long id, @RequestBody Person person) {
		person.setId(id);
		return service.update(person);
	}
	
	@DeleteMapping("/person/{id}")
	public void delete(@PathVariable("id") long id) {
		service.deleteById(id);
	}
}
