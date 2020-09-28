package yakovlev.m.converter.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yakovlev.m.converter.model.Student;

import java.util.Set;

@RestController
//@RequestMapping("/")
public class RestWebController
{
    @Autowired
    private DatabaseController databaseController;
    @Autowired
    private CSVController cvsController;
    @Autowired
    private StudentController studentController;

    @GetMapping("/findstudent")
    public String getStudentsByPrimaryKey(@RequestParam String firstName, @RequestParam String lastName)
    {
        return "Hello " + firstName + " " + lastName;
    }

    @GetMapping("/addstudent")
    public String addNewStudent(@RequestParam String uaFirstName, @RequestParam String uaLastName)
    {
        Student student = studentController.generateNewStudent(uaFirstName, uaLastName);
        databaseController.addNewStudent(student);
        return "Hello " + uaFirstName + " " + uaLastName;
    }

    @GetMapping("/allstudents")
    public String getAllStudents()
    {
        Set<Student> students = databaseController.getAllStudents();
        Gson gson = new Gson();
        String jsonString= gson.toJson(students);
        return jsonString;
    }

    @GetMapping("/generateCSV")
    public String generateCSVFile()
    {
        cvsController.generateCSVStidentsFile();
        return "OK";
    }
}
