package com.jtcode.sharedfloor.interfaces;

public interface ILogin {

    interface  msgView{
        public void setMessageError(String error,int errorCode);
    }
    interface Presenter
    {
        public void validateCredentials(String user,String pass);
    }
}
