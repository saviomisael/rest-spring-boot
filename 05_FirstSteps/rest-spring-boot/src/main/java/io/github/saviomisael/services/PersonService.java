package io.github.saviomisael.services;

import java.util.List;
import java.util.logging.Logger;

import io.github.saviomisael.dto.PageResponseDto;
import io.github.saviomisael.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import io.github.saviomisael.exceptions.ResourceNotFoundException;
import io.github.saviomisael.models.Person;
import io.github.saviomisael.repositories.PersonRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
	private final Logger logger = Logger.getLogger(PersonService.class.getName());
	private PersonRepository repository;

	@Autowired
	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}

	public Person findById(long id) {
		logger.info("Finding one person!");

		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
	}

	public PageResponseDto<Person> findAll(Pageable pageable, String filterByGender) {
		logger.info("Finding all persons!");

		if(!filterByGender.isBlank()) return PageMapper.fromPageToPageResponse(repository.findByGender(filterByGender, pageable));

		return PageMapper.fromPageToPageResponse(repository.findAll(pageable));
	}

	public Person create(Person person) {
		logger.info("Creating a person!");

		return repository.save(person);
	}

	@Transactional
	public Person update(Person person) {
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		return repository.save(entity);
	}

	@Transactional
	public void deleteById(long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
		
		repository.delete(entity);
	}
}
