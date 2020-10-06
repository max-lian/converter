package yakovlev.m.converter.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yakovlev.m.converter.model.Student;
import yakovlev.m.converter.model.StudentFabric;
import yakovlev.m.converter.secvice.DatabaseService;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

@RestController
//@RequestMapping("/")
public class RestWebController
{
    @Autowired
    private DatabaseService databaseController;
    @Autowired
    private CSVController cvsController;
    @Autowired
    private StudentFabric studentFabric;
    private static final Logger INFOLOG = LoggerFactory.getLogger(RestWebController.class);
    private static final Logger ERRORLOG = LoggerFactory.getLogger("errorlog");


    @RequestMapping(value = "/findstudent", method = RequestMethod.GET)
    public String getStudentsByUaFirstAndLastName(@RequestParam String uaFirstName, @RequestParam String uaLastName)
    {
        try
        {
            INFOLOG.info("Try to find student: " + uaFirstName + "\t" + uaLastName);
            List<Student> students = databaseController.findStudents(uaFirstName, uaLastName);
            Gson gson = new Gson();
            String jsonString = gson.toJson(students);
            INFOLOG.info("Student " + uaFirstName + " " + uaLastName + " was finded");
            return jsonString;
        }
        catch (SQLException ex){
            ERRORLOG.error(ex.getMessage());
            return "500 SQLException";
        }
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    @ResponseBody
    public String addNewStudent(@RequestParam String uaFirstName, @RequestParam String uaLastName)
    {
        try
        {
            INFOLOG.info("Try to add student: " + uaFirstName + "\t" + uaLastName);
            Student student = studentFabric.generateNewStudent(uaFirstName, uaLastName);
            databaseController.addNewStudent(student);
            INFOLOG.info("Student " + uaFirstName + " " + uaLastName + " was added");
            return "200 Student added";
        }
        catch (SQLException ex){
            ERRORLOG.error(ex.getMessage());
            return "500 SQLException";
        }
    }

    @GetMapping("/allstudents")
    public String getAllStudents()
    {
        try
        {
            List<Student> students = databaseController.getAllStudents();
            Gson gson = new Gson();
            String jsonString = gson.toJson(students);
            return jsonString;
        }
        catch (SQLException ex){
            ERRORLOG.error(ex.getMessage());
            return "500 SQLException";
        }
    }

    @GetMapping("/generateCSV")
    public String generateCSVFile()
    {
        INFOLOG.info("Generating CSVFile");
        try {
            cvsController.generateCSVStudentsFile();
        }
        catch (SQLException ex) {
            ERRORLOG.error(ex.getMessage());
            return "500 SQLException";
        }
        catch (FileNotFoundException ex){
            ERRORLOG.error(ex.getMessage());
            return "500 FileNotFoundException";
        }
        return "200 OK";
    }

    @GetMapping("/readCSV")
    public String readCSVFile(){
        try {
            cvsController.readCSVStudentsFile();
        } catch (Exception ex) {
            ERRORLOG.error(ex.getMessage());
            return "500";
        }
        return "200 OK";
    }
}
