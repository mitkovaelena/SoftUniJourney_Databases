package app.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "phone")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneDto implements Serializable {

    @XmlElement
    @NotNull
    private String number;

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
