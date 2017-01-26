package com.pbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.pbg.exceptions.PersonNotFoundException;
import com.pbg.model.ErrorResponse;
import com.pbg.model.Person;
import com.pbg.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired(required = true)
	PersonService pServ;

	/*@RequestMapping(value = "/prueba/{id}", method = RequestMethod.GET)
	public Person readPerson(@PathVariable Integer id){
		return pServ.read(id);
	}*/
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<Person> readAllPaging(
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String surname,
			@RequestParam(required=false, defaultValue = "0") Integer p,
			@RequestParam(required=false, defaultValue = "10") Integer m) {
		return pServ.searchPagination(name, surname, p, m);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> read(@PathVariable Integer id) throws PersonNotFoundException {
		Person person = pServ.read(id);
		if(person==null) throw new PersonNotFoundException("Persona con id "+id+" no encontrada.");
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		pServ.delete(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Person create(@RequestBody Person p) {
		return pServ.create(p);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Person p) {
		pServ.update(p);
	}
	
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Parametro erroneo");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
