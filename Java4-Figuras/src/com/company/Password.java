package com.company;

public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public Password(){
        this.password = "123";
    }

    public Password(Password c){
        this.password = c.password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Boolean checkPassword(String password){
        return password.matches("^[0-9]*$");
    }

    public void setValue(String pwd){

        try {
            if (Password.checkPassword(pwd)) {
                this.password = pwd;
            }
            else{
                //duda de que exception tirar
                throw new IllegalArgumentException();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
