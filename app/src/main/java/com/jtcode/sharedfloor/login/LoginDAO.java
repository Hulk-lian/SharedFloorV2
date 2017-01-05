package com.jtcode.sharedfloor.login;


public class LoginDAO {
    private String mens;

    //comprobacion si el usuario y la contraseña son correctos en la base de datos
    public boolean validateCredentials(){
        boolean res=true;
        //comprobacion con la base de datos
        return res;
    }

    ///return the result of the autentication
    public String getResult(){
        //debug
        mens="Autentificacion correcta";
        return mens;
    }

}
