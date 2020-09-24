package yakovlev.m.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseController
{
    @Autowired
    private ConnectionToDatabase connectionToDatabase;

    public Set<Student> getAllStudents() {
        try (Statement statement = connectionToDatabase.getConnection().createStatement())
        {
            ResultSet resultSet = statement.executeQuery("select * from students");
            return writeResultSet(resultSet);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    private Set<Student> writeResultSet(ResultSet resultSet) throws SQLException
    {
        Set<Student> students = new HashSet<>();
        while (resultSet.next())
        {
            students.add(studentObjectFormer(resultSet));
        }
        return students;
    }

    private Student studentObjectFormer(ResultSet resultSet) throws SQLException
    {
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        String sPassword = resultSet.getString("sPassword");
        System.out.println(firstName + "\t" + lastName + "\t" + email + "\t" + sPassword);
        return new Student(firstName, lastName, email, sPassword);

    }
}
