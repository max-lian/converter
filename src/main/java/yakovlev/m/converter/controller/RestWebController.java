package yakovlev.m.converter.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yakovlev.m.converter.model.Student;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
    private static Logger LOG = LoggerFactory.getLogger(RestWebController.class);

    @GetMapping("/findstudent")
    public String getStudentsByUaFirstAndLastName(@RequestParam String uaFirstName, @RequestParam String uaLastName)
    {
        try
        {
            Set<Student> students = databaseController.findStudents(uaFirstName, uaLastName);
            Gson gson = new Gson();
            String jsonString = gson.toJson(students);
            return jsonString;
        }
        catch (SQLException ex){
            LOG.error(ex.getMessage());
            return "SQLException";
        }
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    @ResponseBody
    public String addNewStudent(@RequestParam String uaFirstName, @RequestParam String uaLastName)
    {
        try
        {
            Student student = studentController.generateNewStudent(uaFirstName, uaLastName);
            databaseController.addNewStudent(student);
            return "Student added";
        }
        catch (SQLException ex){
            LOG.error(ex.getMessage());
            return "SQLException";
        }
    }

    @GetMapping("/allstudents")
    public String getAllStudents()
    {
        try
        {
            Set<Student> students = databaseController.getAllStudents();
            Gson gson = new Gson();
            String jsonString = gson.toJson(students);
            return jsonString;
        }
        catch (SQLException ex){
            LOG.error(ex.getMessage());
            return "SQLException";
        }
    }

    @GetMapping("/generateCSV")
    public String generateCSVFile()
    {
        try {
            cvsController.generateCSVStidentsFile();
        }
        catch (SQLException ex) {
            LOG.error(ex.getMessage());
            return "SQLException";
        }
        catch (FileNotFoundException ex){
            LOG.error(ex.getMessage());
            return "FileNotFoundException";
        }
        return "OK";
    }
}
