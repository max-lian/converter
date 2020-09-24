package yakovlev.m.converter.model;

public interface EmailGenerator
{
    public String generateEmail(String firstName, String lastName);
    public String generateEmail(Student student);
}
