package app.dtos.bindingDtos;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.LocalDate;

public class LoggedInUserDto {
    private Long id;

    private String fullName;

    private Boolean isAdmin;

    public LoggedInUserDto() {
    }

    public LoggedInUserDto(Long id, String fullName, Boolean isAdmin) {
        this.id = id;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
