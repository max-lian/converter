package yakovlev.m.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.model.EmailGenerator;
import yakovlev.m.converter.model.PasswordGenerator;
import yakovlev.m.converter.model.Student;
import yakovlev.m.converter.model.Transliterator;

import java.sql.SQLException;

@Component
public class StudentController
{
    @Autowired
    private EmailGenerator emailGenerator;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private Transliterator transliterator;

    public Student generateNewStudent(String latinFirstName, String latinLastName, String uaFirstName, String uaLastName,  String email, String sPassword)
    {
        return new Student(latinFirstName, latinLastName, uaFirstName, uaLastName, email, sPassword);
    }

    public Student generateNewStudent(String uaFirstName, String uaLastName) throws SQLException
    {
        Student student = new Student(uaFirstName, uaLastName);
        student.setLatinFirstName(transliterator.toTranslate(uaFirstName));
        student.setLatinLastName(transliterator.toTranslate(uaLastName));
        student.setEmail(emailGenerator.generateEmail(student));
        student.setPassword(passwordGenerator.generagePassword());
        return student;
    }
}
