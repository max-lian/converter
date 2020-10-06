package yakovlev.m.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yakovlev.m.converter.model.Student;
import yakovlev.m.converter.model.StudentFabric;
import yakovlev.m.converter.secvice.DatabaseService;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CSVController
{
    @Autowired
    private DatabaseService databaseController;
    @Autowired
    private StudentFabric studentFabric;

    @Value("${csvcontroller.orgUnitPath}")
    private String orgUnitPath;
    @Value("${csvcontroller.adminEmail}")
    private String adminEmail;
    @Value("${csvcontroller.adminNumber}")
    private String adminNuber;
    @Value("${csvcontroller.changePasswordAtNextSignIn}")
    private String changePasswordAtNextSignIn;
    @Value("${csvcontroller.inputFilePath}")
    private String inputFilePath;
    @Value("${csvcontroller.outputFilePath}")
    private String outputFilePath;

    private String[] headerString = new String[]{"First Name [Required]", "Last Name [Required]", "Email Address [Required]", "Password [Required]", "Password Hash Function [UPLOAD ONLY]", "Org Unit Path [Required]", "New Primary Email [UPLOAD ONLY]", "Recovery Email", "Home Secondary Email", "Work Secondary Email", "Recovery Phone [MUST BE IN THE E.164 FORMAT]", "Work Phone", "Home Phone", "Mobile Phone", "Work Address", "Home Address", "Employee ID", "Employee Type", "Employee Title", "Manager Email", "Department", "Cost Center", "Building ID", "Floor Name", "Floor Section", "Change Password at Next Sign-In", "New Status [UPLOAD ONLY]"
};

    public void generateCSVStudentsFile() throws FileNotFoundException, SQLException {
        List<Student> students = databaseController.getAllStudents();
        List<String[]> studentsStrings = new ArrayList<String[]>();
        studentsStrings.add(headerString);
        for (Student student:
             students) {
            studentsStrings.add(parseStudentToStringArray(student));
        }
        PrintWriter pw = new PrintWriter(new File(outputFilePath));
        studentsStrings.stream()
                .map(this::convertToCSV)
                .forEach(pw::println);
        pw.close();
    }

    public void readCSVStudentsFile() throws IOException, SQLException {
        System.out.println(inputFilePath);
        BufferedReader csvReader = new BufferedReader(new FileReader(inputFilePath));
        String row = "";
        while ((row = csvReader.readLine()) != null)
        {
            String[] splitedRow = row.split(" ");
            String firstName = splitedRow[1];
            for(int i = 2; i < splitedRow.length; i++) firstName = firstName.concat(" " + splitedRow[i]);
            databaseController.addNewStudent(studentFabric.generateNewStudent(firstName, splitedRow[0]));
        }
        csvReader.close();
    }


    private String[] parseStudentToStringArray(Student student)  {
        return new String[]{student.getUaFirstName(),student.getUaLastName(), student.getEmail(), student.getPassword(), "",orgUnitPath, "", adminEmail,"","","","",adminNuber,"","","","","","","","","","","","",changePasswordAtNextSignIn,""};
    }

    private String convertToCSV(String[] data)
    {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data)
    {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(";") || data.contains("\"") || data.contains("'"))
        {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
