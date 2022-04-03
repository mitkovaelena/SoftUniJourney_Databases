package app.terminal;

import app.domain.dto.DTOConvertUtil;
import app.domain.dto.json.PersonJsonDto;
import app.domain.model.Person;
import app.io.Serializer;
import app.serviceImpl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private static final String  PERSON_INPUT_JSON = "/files/input/json/person.json";
    private static final String  PEOPLE_INPUT_JSON = "/files/input/json/persons.json";
    private static final String  PEOPLE_OUTPUT_JSON = "src/main/resources/files/output/json/people.json";

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    @Qualifier("JsonParser")
    private Serializer serializer;

    @Override
    public void run(String... strings) throws Exception {
        this.importPerson();
        this.importPeople();
        exportPersonByCountry();
    }

    private void exportPersonByCountry() {
        List<PersonJsonDto> bulgarians = this.personService.findByCountry("Bulgaria");
        serializer.serialize(bulgarians, PEOPLE_OUTPUT_JSON);
    }

    private void importPeople() {
        PersonJsonDto[] personJsonDtos = serializer.deserialize(PersonJsonDto[].class, PEOPLE_INPUT_JSON);
        for (PersonJsonDto personJsonDto : personJsonDtos) {
            this.savePerson(personJsonDto);
        }
    }

    private void importPerson() {
        PersonJsonDto personJsonDto = serializer.deserialize(PersonJsonDto.class, PERSON_INPUT_JSON);
        this.savePerson(personJsonDto);
    }

    private void savePerson(PersonJsonDto personJsonDto) {
        Person person = DTOConvertUtil.convert(personJsonDto, Person.class);
        this.personService.create(person);
    }
}
