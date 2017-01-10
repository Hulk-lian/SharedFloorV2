package com.jtcode.sharedfloor.RepTestsDatos;

import com.jtcode.sharedfloor.adapters.HomeAdapter;
import com.jtcode.sharedfloor.model.User;

import java.util.ArrayList;
import java.util.List;

public class WhoPaidList  {

    private static List<CharSequence> listUsers;

    private static WhoPaidList ourInstance = new WhoPaidList();

    public static WhoPaidList getInstance() {
        return ourInstance;
    }

    private WhoPaidList() {
        listUsers= new ArrayList<>();
        for (User u :UsersHomeLIST.getAll()) {
            listUsers.add(u.getName());
        }
        listUsers.add("Casero");
    }
    public static List<CharSequence> getAll(){
        return listUsers;
    }

}

