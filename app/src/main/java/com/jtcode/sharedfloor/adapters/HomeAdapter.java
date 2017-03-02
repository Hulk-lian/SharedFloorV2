package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.model.User;

import java.util.ArrayList;

public class HomeAdapter extends ArrayAdapter<User>{

    private Context context;
    private ArrayList<User> userList;

    public HomeAdapter(Context context, int layout) {
        super(context, layout);
        this.context=context;
        userList= new ArrayList<>();
    }

    class UserHolder{
        TextView txvUserName;
    }

}
