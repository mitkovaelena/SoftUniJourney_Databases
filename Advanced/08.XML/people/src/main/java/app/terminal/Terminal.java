package app.terminal;

import app.domain.dto.*;
import app.domain.model.Address;
import app.domain.model.Person;
import app.serialize.Serializer;
import app.service.AddressesService;
import app.service.PersonService;
import app.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private AddressesService addressesService;

    @Autowired
    @Qualifier(value = "XMLSerializer")
    private Serializer xmlSerializer;


    @Override
    public void run(String... strings) throws Exception {
        importPersonXML();
        importPersonsXML();
        this.exportFirstPersonXML();
        exportAddressesXML();
    }

    private void importPersonXML() {
        PersonDto personDto = xmlSerializer.deserialize(PersonDto.class, "/files/input/xml/person.xml");
        Person person = DTOConvertUtil.convert(personDto, Person.class);
        personService.save(person);
    }

    private void importPersonsXML() {
        PersonsDto personsDto = xmlSerializer.deserialize(PersonsDto.class, "/files/input/xml/persons.xml");
        List<Person> persons = DTOConvertUtil.convert(personsDto.getPersonDtos(), Person.class);
        for (Person person : persons) {
            personService.save(person);
        }
    }

    private void exportAddressesXML() {
        List<Address> allAdresses = addressesService.findAllAdresses();
        List<AddressDto> addressDtos = DTOConvertUtil.convert(allAdresses, AddressDto.class);
        AddressesDto addressesDto = new AddressesDto();
        addressesDto.setAddressDtos(addressDtos);
        xmlSerializer.serialize(addressesDto, "src/main/resources/files/output/xml/addresses.xml");
    }

    private void exportFirstPersonXML() {
        Person person = this.personService.findById(1);
        PersonDto personDto = DTOConvertUtil.convert(person, PersonDto.class);
        this.xmlSerializer.serialize(personDto, "src/main/resources/files/output/xml/person.xml");
    }
}
