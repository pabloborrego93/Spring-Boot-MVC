package com.pbg.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbg.model.Person;

@Repository
public interface PersonDao extends PagingAndSortingRepository<Person, Integer> {
		@Query(value = "select new com.pbg.model.Person (u.id, u.name, u.surname, u.phoneNumber) "
			+ "from Person as u where "
			+ "(?1 is null OR u.name like ?1) "
			+ "AND "
			+ "(?2 is null OR u.surname like ?2)")
		Page<Person> findByNameAndSurname(
			@Param(value = "name") String name,
			@Param(value = "surname") String surname, 
			Pageable pageable);
}
