package yakovlev.m.converter.model;

import org.springframework.stereotype.Component;

public class Student {
    private String latinFirstName;
    private String latinLastName;
    private String uaFirstName;
    private String uaLastName;
    private String email;
    private String password;

    public Student(String uaFirstName, String uaLastName) {
        this.uaFirstName = uaFirstName;
        this.uaLastName = uaLastName;
    }

    public Student(String latinFirstName, String latinLastName, String uaFirstName, String uaLastName, String email, String password) {
        this.latinFirstName = latinFirstName;
        this.latinLastName = latinLastName;
        this.uaFirstName = uaFirstName;
        this.uaLastName = uaLastName;
        this.email = email;
        this.password = password;
    }

    public String getUaFirstName() {
        return uaFirstName;
    }

    public void setUaFirstName(String uaFirstName) {
        this.uaFirstName = uaFirstName;
    }

    public String getUaLastName() {
        return uaLastName;
    }

    public void setUaLastName(String uaLastName) {
        this.uaLastName = uaLastName;
    }

    public String getLatinFirstName() {
        return latinFirstName;
    }

    public void setLatinFirstName(String latinFirstName) {
        this.latinFirstName = latinFirstName;
    }

    public String getLatinLastName() {
        return latinLastName;
    }

    public void setLatinLastName(String latinLastName) {
        this.latinLastName = latinLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "latinFirstName='" + latinFirstName + '\'' +
                ", latinLastName='" + latinLastName + '\'' +
                ", uaFirstName='" + uaFirstName + '\'' +
                ", uaLastName='" + uaLastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
