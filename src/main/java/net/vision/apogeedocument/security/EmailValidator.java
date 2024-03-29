package net.vision.apogeedocument.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator {
    public boolean validateEmail(String email) {
        // Expression régulière pour vérifier le format de l'adresse e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
