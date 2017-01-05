package com.jtcode.sharedfloor.login;

import android.content.Context;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.interfaces.CustomConstants;
import com.jtcode.sharedfloor.interfaces.ILogin;
import com.jtcode.sharedfloor.model.User;

/**
 * Created by Hulk-lián
 */

public class LoginPresenter implements ILogin.Presenter{
    private User user;
    private ILogin.msgView msgView;

    public LoginPresenter(ILogin.msgView view){
        this.msgView=view;
    }

    @Override
    public void validateCredentials(String user, String pass) {
        String mensRes=null;
        int codeRes=0;

        //empty user
        if(!user.isEmpty()) {
            //pass empty
            if (!pass.isEmpty()) {
                mensRes = ((Context) msgView).getResources().getString(R.string.loginOK); //all ok
                codeRes = CustomConstants.ALLOK;
            }
            else{
                mensRes = ((Context) msgView).getResources().getString(R.string.empty_pass); //pass empty
                codeRes = R.id.A_LOGIN_edtPassword;
            }
        }
        else{
            mensRes = ((Context) msgView).getResources().getString(R.string.empty_user); //user empty
            codeRes= R.id.A_LOGIN_edtUsername;
        }
        msgView.setMessageError(mensRes,codeRes);
    }

    private void conect(){

    }
    //comprobacion si el usuario esta registrado
    private boolean access(String user,String pass){
        return true;
    }
}
