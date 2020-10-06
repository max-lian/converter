package yakovlev.m.converter.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class StandartPasswordGenerator implements PasswordGenerator
{

    private int passwordLenght = 8;

    public int getPasswordLenght()
    {
        return passwordLenght;
    }

    public void setPasswordLenght(int passwordLenght)
    {
        this.passwordLenght = passwordLenght;
    }

    @Override
    public String generagePassword()
    {
        return RandomStringUtils.randomAlphanumeric(8);
    }

}
