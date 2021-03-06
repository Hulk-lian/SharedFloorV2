package com.jtcode.sharedfloor.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jtcode.sharedfloor.Activity_Selection;
import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.interfaces.ILogin;


public class Activity_Login extends AppCompatActivity implements ILogin.msgView{

    Intent intentCont;
    Button btnLogin;
    TextInputLayout tilUsername,tilPassword;
    TextView txvForget,txvRegister;
    private ILogin.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        init();
    }
    private void init(){
        tilUsername=(TextInputLayout)findViewById(R.id.A_LOGIN_tilUsername);
        tilPassword=(TextInputLayout)findViewById(R.id.A_LOGIN_tilPassword);
        btnLogin=(Button)findViewById(R.id.A_LOGIN_btnLogin);
        txvForget=(TextView)findViewById(R.id.A_LOGIN_txvForgetPass);
        txvRegister=(TextView)findViewById(R.id.A_LOGIN_txvCreateAccount);

        loginPresenter= new LoginPresenter(Activity_Login.this);

        //links
        txvForget.setMovementMethod(LinkMovementMethod.getInstance());
        txvRegister.setMovementMethod(LinkMovementMethod.getInstance());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.validateCredentials(tilUsername.getEditText().getText().toString(),tilPassword.getEditText().getText().toString());
            }
        });

        tilUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilUsername.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        tilPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void login(){
        intentCont= new Intent(Activity_Login.this,Activity_Selection.class);
        startActivity(intentCont);
        finish();
    }


    ///////////////////////debug/////////////////////
    //falta quitar el error de campo vacio una vez que sale y se introduce algo se quite el error
    @Override
    public void setMessageError(String error, int errorCode) {


        switch (errorCode) {

            case R.id.A_LOGIN_edtUsername: // User Incorrecto
                tilUsername.setError(error);
                break;

            case R.id.A_LOGIN_edtPassword: // Pass Incorreta
                tilPassword.setError(error);
                break;

            case 0: //Login ok
                login();
                break;
        }
    }
}
