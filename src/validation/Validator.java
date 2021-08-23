package validation;

import domain.User;

import java.util.regex.Pattern;

public class Validator {

    public Validator(){ }

    //Minimum eight characters, at least one uppercase letter, one lowercase letter and one number
    public boolean validatePassword(String password){
        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        return validate(PASSWORD_PATTERN, password);
    }

    public boolean validateName(String name){
        final String NAME_PATTERN = "[A-Z][a-z]+";
        return validate(NAME_PATTERN, name) && name.length() > 2;
    }

    public boolean validate(String patternText, String text){
        Pattern pattern = Pattern.compile(patternText);
        return pattern.matcher(text).matches();
    }

}
