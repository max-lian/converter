package yakovlev.m.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.model.Student;

import java.sql.PreparedStatement;
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
    @Autowired
    private StudentController studentController;

    //Список всех студентов из базы данных в виде сета
    public Set<Student> getAllStudents() {
        try (Statement statement = connectionToDatabase.getConnection().createStatement())
        {
            ResultSet resultSet = statement.executeQuery("select * from students");
            return writeResultSet(resultSet);
        } catch (SQLException throwables)
        {
            System.err.println("Got an exception!");
            System.err.println(throwables.getMessage());
        }
        return null;
    }

    public void addNewStudent(Student student)
    {
        String query = " insert into students (latinFirstName, latinLastName, uaFirstName, uaLastName, email, sPassword)"
                + " values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connectionToDatabase.getConnection().prepareStatement(query))
        {
            statement.setString (1, student.getLatinFirstName());
            statement.setString (2, student.getLatinLastName());
            statement.setString (3, student.getUaFirstName());
            statement.setString (4, student.getUaLastName());
            statement.setString (5, student.getEmail());
            statement.setString (6, student.getPassword());
            statement.execute();
        } catch (SQLException throwables) {
            System.err.println("Got an exception!");
            System.err.println(throwables.getMessage());
        }
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
        String latinFirstName = resultSet.getString("latinFirstName");
        String latinLastName = resultSet.getString("latinLastName");
        String uaFirstName = resultSet.getString("uaFirstName");
        String uaLastName = resultSet.getString("uaLastName");
        String email = resultSet.getString("email");
        String sPassword = resultSet.getString("sPassword");
        Student student = studentController.generateNewStudent(latinFirstName, latinLastName, uaFirstName, uaLastName, email, sPassword);
        System.out.println(student.toString());
        return student;
    }
}
