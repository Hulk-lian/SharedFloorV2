package com.jtcode.sharedfloor.RepTestsDatos;

import com.jtcode.sharedfloor.model.ClearTurn;
import com.jtcode.sharedfloor.model.User;

import java.util.ArrayList;
import java.util.List;

public class CleanTurnList {

    private static List<ClearTurn> listTurn;
    private static CleanTurnList ourInstance = new CleanTurnList();

    public static CleanTurnList getInstance() {
        return ourInstance;
    }

    private CleanTurnList() {
        listTurn= new ArrayList<>();

        List<User> userList=UsersHomeLIST.getAll();
        String[] names= new String[]{"cocina","baño","salon"};
        String[] descrip=new String[]{"lipiar la cocina y tirar la basura de la cocina","limpiar el baño y tirar la basura del baño","limpiar el salon y los pasillos"};

        for ( int i=0;i<userList.size();i++) {
            listTurn.add(new ClearTurn(userList.get(i).getName(),descrip[i],names[i]));
        }

    }
    public static List<ClearTurn> getAll(){
        return listTurn;
    }
}
