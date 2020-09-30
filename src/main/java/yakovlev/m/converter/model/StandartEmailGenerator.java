package yakovlev.m.converter.model;

import org.springframework.stereotype.Component;

@Component
public class StandartEmailGenerator implements EmailGenerator
{

    @Override
    public String generateEmail(String firstName, String lastName)
    {
        return firstName + "." + lastName + "@sumdu.edu.ua";
    }

    @Override
    public String generateEmail(Student student)
    {
        return student.getLatinFirstName().split(" ")[0].toLowerCase() + "." + student.getLatinLastName().toLowerCase() + "@sumdu.edu.ua";
    }
}
