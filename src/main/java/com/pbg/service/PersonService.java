package com.pbg.service;

import org.springframework.data.domain.Page;

import com.pbg.model.Person;

public interface PersonService {
	Page<Person> searchPagination(String name, String surname, Integer p, Integer m);
	Person read(Integer id);
	Person create(Person p);
	void update(Person p);
	void delete(Integer id);
}
