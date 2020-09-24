package yakovlev.m.converter;

import yakovlev.m.converter.model.PasswordGenerator;
import yakovlev.m.converter.model.StandartPasswordGenerator;

public class MainTestForPasswordGenerator {
    public static void main(String[] args)
    {
        StandartPasswordGenerator standartPasswordGenerator = new StandartPasswordGenerator();
        System.out.println(standartPasswordGenerator.generagePassword());
    }
}
