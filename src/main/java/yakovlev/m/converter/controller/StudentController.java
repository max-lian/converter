package yakovlev.m.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.model.EmailGenerator;
import yakovlev.m.converter.model.PasswordGenerator;
import yakovlev.m.converter.model.Student;

@Component
public class StudentController
{
    @Autowired
    private EmailGenerator emailGenerator;
    @Autowired
    private PasswordGenerator passwordGenerator;

    public void fillStudentsFields(Student student)
    {
        if (student.getEmail() == null)
        {
            student.setEmail(emailGenerator.generateEmail(student));
        }
        if (student.getPassword() == null)
        {
            student.setPassword(passwordGenerator.generagePassword());
        }
    }

    public Student generateNewStudent(String firstName, String lastName)
    {
        Student student = new Student(firstName, lastName);
        student.setEmail(emailGenerator.generateEmail(student));
        student.setPassword(passwordGenerator.generagePassword());
        return null;
    }
}
