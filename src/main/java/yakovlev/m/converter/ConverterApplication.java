package yakovlev.m.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yakovlev.m.converter.secvice.DatabaseService;
@SpringBootApplication
public class ConverterApplication {

    @Autowired
    private DatabaseService databaseController;

    public static void main(String[] args) {

        SpringApplication.run(ConverterApplication.class, args);
    }

}
