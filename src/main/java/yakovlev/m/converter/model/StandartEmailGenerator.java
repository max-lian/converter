package yakovlev.m.converter.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.controller.DatabaseController;

import java.sql.SQLException;

@Component
public class StandartEmailGenerator implements EmailGenerator
{
    @Autowired
    private DatabaseController databaseController;
    private String domen = "@sumdu.edu.ua";
    @Override
    public String generateEmail(String firstName, String lastName)
    {
        return firstName + "." + lastName + "@sumdu.edu.ua";
    }

    @Override
    public String generateEmail(Student student) throws SQLException
    {
        int count = 1;
        String email = student.getLatinFirstName().split(" ")[0].toLowerCase() + "." + student.getLatinLastName().toLowerCase();
        if (databaseController.findStudents(email + domen).isEmpty()) return email + domen;
        else
            {
            while (!databaseController.findStudents(email + count + domen).isEmpty()) count++;
            }
            return email + count + domen;
    }
}
