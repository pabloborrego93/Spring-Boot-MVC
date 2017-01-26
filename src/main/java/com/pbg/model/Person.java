package com.pbg.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8904932828219254929L;
	
	public Person(Integer id, String name, String surname, Integer phoneNumber) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
	}
	
	public Person() {
	}

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String surname;
	private Integer phoneNumber;
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String toString(){
		return new String("id: "+id+" | name: "+name+" | surname: "+surname+" | phone: "+phoneNumber);
	}
	
}
