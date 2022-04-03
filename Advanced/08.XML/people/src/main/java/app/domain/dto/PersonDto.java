package app.domain.dto;

import app.domain.validation.Phone;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto {

    @XmlElement(name = "first_name")
    private String firstName;

    @XmlElement(name = "last_name")
    private String lastName;

    @XmlElement
    @Phone
    private AddressDto address;

    @XmlElementWrapper(name = "phone_numbers")
    @XmlElement(name = "phone_number")
    @Phone
    private Set<PhoneDto> phoneNumbers;

    public PersonDto() {
        this.setPhoneNumbers(new HashSet<>());
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDto getAddress() {
        return this.address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Set<PhoneDto> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
