package com.github.immrgabriel.JAXB.simple.test;

import com.github.immrgabriel.JAXB.simple.entity.Person;
import com.github.immrgabriel.JAXB.simple.parser.Parser;
import com.github.immrgabriel.JAXB.simple.parser.impl.JaxbParser;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class JaxbParserTest {
    private Parser parser;
    private File file;

    @Before
    public void setUp() throws Exception {
        parser = new JaxbParser();
        file = new File("src/" + JaxbParserTest.class.getPackage().getName().replaceAll("[.]", "/") + "/person.xml");
    }

    @Test
    public void testGetObject() throws Exception {
        Person person = (Person) parser.getObject(file, Person.class);
        System.out.println(person);
    }

    @Test
    public void testSaveObject() throws Exception {
        Person person = new Person();
        Person friend1 = new Person();
        Person friend2 = new Person();

        person.setName("Sergiy");
        person.setAge(26);
        friend1.setName("Anya");
        friend1.setAge(23);
        friend2.setName("Olya");
        friend2.setAge(22);

        person.setFriends(new ArrayList<Person>() {{
            add(friend1);
            add(friend2);
        }});

        parser.saveObject(file, person);
    }
}
