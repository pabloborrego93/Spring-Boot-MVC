package com.pbg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pbg.dao.PersonDao;
import com.pbg.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired(required = true)
	PersonDao pDao;
	
	@Override
	public Page<Person> searchPagination(String name, String surname, Integer p, Integer m) {
		final PageRequest pageRequest = new PageRequest(p, m);
		if(name!=null){
			StringBuilder aux = new StringBuilder(name);
			aux = aux.insert(0, "%");
			aux = aux.append("%");
			name = aux.toString();
		}
		if(surname!=null){
			StringBuilder aux = new StringBuilder(surname);
			aux = aux.insert(0, "%");
			aux = aux.append("%");
			surname = aux.toString();
		}
		return pDao.findByNameAndSurname(name, surname, pageRequest);
	}

	@Override
	public Person read(Integer id) {
		return pDao.findOne(id);
	}

	@Override
	public Person create(Person p) {
		return pDao.save(p);
	}

	@Override
	public void update(Person p) {
		pDao.save(p);
	}

	@Override
	public void delete(Integer id) {
		pDao.delete(id);
	}

}
