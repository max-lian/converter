package yakovlev.m.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CSVController
{
    @Autowired
    private DatabaseController databaseController;
    private String[] headerString = new String[]{"First name", "Last name", "Password", "Email"};

    public void generateCSVStidentsFile()
    {
        Set<Student> students = databaseController.getAllStudents();
        List<String[]> studentsStrings = new ArrayList<String[]>();
        studentsStrings.add(headerString);
        for (Student student:
             students) {
            studentsStrings.add(parseStudentToStringArray(student));
        }
        File csvOutputFile = new File("studentsList.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile))
        {
            studentsStrings.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        //assertTrue(csvOutputFile.exists());
    }

    private String[] parseStudentToStringArray(Student student)
    {
        return new String[]{student.getFirstName(), student.getLastName(), student.getPassword(), student.getEmail()};
    }

    private String convertToCSV(String[] data)
    {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(";"));
    }

    private String escapeSpecialCharacters(String data)
    {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'"))
        {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}