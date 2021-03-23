package com.company;

import java.util.InvalidPropertiesFormatException;

public class PasswordFuerte extends Password{

    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$")) {
            this.pass = pass;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    //duda sobre override
    public static Boolean checkPassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$");

    }


}
