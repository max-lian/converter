package yakovlev.m.converter.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.secvice.DatabaseService;

import java.sql.SQLException;

@Component
public class StandartEmailGenerator implements EmailGenerator
{
    @Autowired
    private DatabaseService databaseController;

    @Value("${email.domen}")
    private String domen;

    @Override
    public String generateEmail(String firstName, String lastName) throws SQLException {
        int count = 1;
        String email = firstName.split(" ")[0].toLowerCase() + "." + lastName.toLowerCase();
        if (databaseController.findStudents(email + domen).isEmpty()) return email + domen;
        else
        {
            while (!databaseController.findStudents(email + count + domen).isEmpty()) count++;
        }
        return email + count + domen;
    }
}
