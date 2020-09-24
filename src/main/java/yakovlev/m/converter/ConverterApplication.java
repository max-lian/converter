package yakovlev.m.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import yakovlev.m.converter.controller.DatabaseController;
//@PropertySource("classpath:databaseConnection.properties")
@SpringBootApplication
public class ConverterApplication {

    @Autowired
    private DatabaseController databaseController;

    public static void main(String[] args) {
        SpringApplication.run(ConverterApplication.class, args);
    }

}
