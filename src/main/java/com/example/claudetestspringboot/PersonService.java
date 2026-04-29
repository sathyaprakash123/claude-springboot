package com.example.claudetestspringboot;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(PersonRequest request) {
        Person person = new Person();
        person.setName(request.getName());
        person.setAge(request.getAge());
        person.setGender(request.getGender());
        return personRepository.save(person);
    }
}
