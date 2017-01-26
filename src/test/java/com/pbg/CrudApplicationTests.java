package com.pbg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pbg.model.Person;
import com.pbg.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudApplicationTests {

	@Autowired(required = true)
	PersonService pServ;
	
	private final int random(int max){
		return (int) (Math.random() * max);
	}
		
	@Test
	public void addPerson() {
		final Integer numeroPersonas = new Integer(1000000);
		final String[] nombres = new String[]
				{"Pablo", "Pedro", "Ricardo", "Maria", "Carmen", "Manuel", "Jose",
				 "Francisco", "Laura", "Marta", "David", "Isabel", "Ana", "Lucia",
				 "Raquel", "Antonio", "Luis", "Javier", "Angel", "Diego", "Lorena"};
		final String[] apellidos = new String[]
				{"Garcia", "Lopez", "Perez", "Gonzalez", "Sanchez", "Martinez", "Rodriguez",
				 "Fernandez", "Gomez", "Martin", "Hernandez", "Ruiz", "Diaz", "Alvarez",
				 "Jimenez", "Moreno", "Alonso", "Gutierrez", "Romero", "Torres", "Suarez",
				 "Ramirez", "Vazquez", "Navarro", "Dominguez", "Ramos", "Castro", "Gil",
				 "Flores", "Morales", "Blanco", "Serrano", "Molina", "Ortiz", "Santos",
				 "Ortega", "Delgado", "Mendez", "Castillo", "Marquez", "Cruz", "Rojas"};
		for(int i = 0; i < numeroPersonas; ++i){
			Person p = new Person();
			p.setName(nombres[random(nombres.length)]);
			p.setSurname(apellidos[random(apellidos.length)]+" "+apellidos[random(apellidos.length)]);
			p.setPhoneNumber(random(999999));
			pServ.create(p);
		}
		
	}

}
