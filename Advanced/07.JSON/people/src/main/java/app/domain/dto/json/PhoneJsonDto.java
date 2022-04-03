package app.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PhoneJsonDto implements Serializable {

    @Expose
    private String number;

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
