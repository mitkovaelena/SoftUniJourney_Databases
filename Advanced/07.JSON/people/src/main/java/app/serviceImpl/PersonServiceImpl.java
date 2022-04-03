package app.serviceImpl;

import app.domain.dto.DTOConvertUtil;
import app.domain.dto.json.AddressJsonDto;
import app.domain.dto.json.PersonJsonDto;
import app.domain.dto.json.PhoneJsonDto;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.repository.PersonRepository;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRepository phoneRepository;


    @Override
    public void create(Person person) {
        this.personRepository.saveAndFlush(person);
    }

    @Override
    public Person findById(long id) {
        Person person = this.personRepository.findOne(id);
        return person;
    }

    @Override
    public List<PersonJsonDto> findByCountry(String country) {
        List<Person> persons = this.personRepository.findByCountry(country);
        List<PersonJsonDto> personJsonDtos = new ArrayList<>();
        for (Person person : persons) {
            PersonJsonDto personJsonDto = DTOConvertUtil.convert(person, PersonJsonDto.class);
            personJsonDtos.add(personJsonDto);
        }

        return personJsonDtos;
    }
}
