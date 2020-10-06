package yakovlev.m.converter.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class StudentFabric
{
    @Autowired
    private EmailGenerator emailGenerator;
    @Autowired
    private PasswordGenerator passwordGenerator;
    @Autowired
    private Transliterator transliterator;

    public Student generateNewStudent(String uaFirstName, String uaLastName) throws SQLException
    {
        String latinFirstName = transliterator.toTranslate(uaFirstName);
        String latinLastName = transliterator.toTranslate(uaLastName);

        return  Student.builder()
                .setUaFirstName(uaFirstName)
                .setUaLastName(uaLastName)
                .setLatinFirstName(latinFirstName)
                .setLatinLastName(latinLastName)
                .setEmail(emailGenerator.generateEmail(latinFirstName, latinLastName))
                .setPassword(passwordGenerator.generagePassword())
                .build();
    }
}
