package com.company;

public class PasswordSimple extends Password{

    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (pass.matches("^(?=.*[a-z])[a-z]{8,}$")) {
            this.pass = pass;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public static Boolean checkPassword(String password){
        return password.matches("^(?=.*[a-z])[a-z]{8,}$");

    }

}
