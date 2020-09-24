package yakovlev.m.converter.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yakovlev.m.converter.model.Student;

import java.util.HashSet;
import java.util.Set;

@RestController
//@RequestMapping("/")
public class TestController
{
    @Autowired
    private DatabaseController databaseController;
    @GetMapping("/findstudent")
    public String getStudentsByPrimaryKey(@RequestParam String firstName, @RequestParam String lastName)
    {
        return "Hello " + firstName + " " + lastName;
    }

    @GetMapping("/allstudents")
    public String getAllStudents()
    {
        Set<Student> students = databaseController.getAllStudents();
        Gson gson = new Gson();
        String jsonString= gson.toJson(students);
        return jsonString;
    }

}
