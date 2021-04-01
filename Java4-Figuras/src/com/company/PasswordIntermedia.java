package com.company;

import java.util.InvalidPropertiesFormatException;

public class PasswordIntermedia extends Password{

    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (pass.matches("^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{8,}$")) {
            this.pass = pass;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public static Boolean checkPassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{8,}$");

    }

}
