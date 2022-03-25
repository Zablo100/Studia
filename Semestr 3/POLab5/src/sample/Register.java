package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {

    public static void checkName(String name) throws NumberInNameException{
        Pattern pattern = Pattern.compile("[A-Za-z]+");
        Matcher matcher = pattern.matcher(name);
        if(!matcher.matches()){
            throw new NumberInNameException("Pole na imię nie może zawierać liczb");
        }
    }

    public static void checkAge(String age) throws AgeException{
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(age);
        if(!matcher.matches()){
            throw new AgeException("W pole na wiek znajdują się litery");
        }
    }
}
