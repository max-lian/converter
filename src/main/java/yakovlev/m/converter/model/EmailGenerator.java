package yakovlev.m.converter.model;

import java.sql.SQLException;

public interface EmailGenerator
{
    public String generateEmail(String firstName, String lastName) throws SQLException;
}
