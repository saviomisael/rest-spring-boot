package io.github.saviomisael;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.github.saviomisael.dto.CreatePersonDto;
import io.github.saviomisael.models.Person;
import io.github.saviomisael.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

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
	@Operation(summary = "Gets all people.", description = "Gets all people.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Returns all people.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Person.class))) }) })
	public List<Person> getAll() {
		var persons = service.findAll();

		persons.forEach(x -> x.add(linkTo(methodOn(PersonController.class).getById(x.getId())).withSelfRel()));

		return persons;
	}

	@PostMapping("/person")
	@Operation(summary = "Create a person.", description = "Create a person.")
	@ApiResponse(responseCode = "201", description = "Returns the person created.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Person.class))) })
	@ApiResponse(responseCode = "400", description = "Returns the errors in the request.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) })
	public ResponseEntity<Person> create(@Valid @RequestBody CreatePersonDto dto) {
		var person = new Person();
		person.setAddress(dto.getAddress());
		person.setFirstName(dto.getFirstName());
		person.setLastName(dto.getLastName());
		person.setGender(dto.getGender());
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

	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
