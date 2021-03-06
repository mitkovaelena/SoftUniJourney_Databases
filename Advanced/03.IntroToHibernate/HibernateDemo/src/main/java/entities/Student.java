package entities;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private Date registrationDate;

    public Student() {
        super();
    }

    public Student(String name, Date registrationDate) {
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
