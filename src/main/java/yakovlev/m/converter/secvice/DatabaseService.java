package yakovlev.m.converter.secvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.controller.ConnectionToDatabase;
import yakovlev.m.converter.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseService
{
    @Autowired
    private ConnectionToDatabase connectionToDatabase;

    //Список всех студентов из базы данных в виде сета
    public List<Student> getAllStudents() throws SQLException{
        Statement statement = connectionToDatabase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from students");
        return writeResultSet(resultSet);
    }

    //Поиск студентов по email
    public List<Student> findStudents(String email) throws SQLException{
       Statement statement = connectionToDatabase.getConnection().createStatement();
       ResultSet resultSet = statement.executeQuery(String.format("select * from students where email = '%s'", email));
       return writeResultSet(resultSet);
    }

    public List<Student> findStudents(String uaFirstName, String uaLastName) throws SQLException{
        Statement statement = connectionToDatabase.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(
                String.format("select * from students where uaFirstName = '%s' and uaLastName = '%s'", uaFirstName, uaLastName));
        return writeResultSet(resultSet);
    }

    public void addNewStudent(Student student) throws SQLException
    {
        String query = " insert into students (latinFirstName, latinLastName, uaFirstName, uaLastName, email, sPassword)"
                + " values (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connectionToDatabase.getConnection().prepareStatement(query);
        statement.setString (1, student.getLatinFirstName());
        statement.setString (2, student.getLatinLastName());
        statement.setString (3, student.getUaFirstName());
        statement.setString (4, student.getUaLastName());
        statement.setString (5, student.getEmail());
        statement.setString (6, student.getPassword());
        statement.execute();
    }

    private List<Student> writeResultSet(ResultSet resultSet) throws SQLException
    {
        List<Student> students = new ArrayList<>();
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
        Student student = Student.builder()
                .setUaFirstName(uaFirstName)
                .setUaLastName(uaLastName)
                .setLatinFirstName(latinFirstName)
                .setLatinLastName(latinLastName)
                .setEmail(email)
                .setPassword(sPassword)
                .build();;
        //System.out.println(student.toString());
        return student;
    }
}
