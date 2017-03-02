package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.database.DatabaseManager;
import com.jtcode.sharedfloor.model.User;

import java.util.ArrayList;

public class HomeAdapter extends ArrayAdapter<User>{

    private Context context;
    private DatabaseManager db;

    public HomeAdapter(Context context, int layout) {
        super(context, layout);
        this.context=context;
        db= DatabaseManager.getInstance();
        this.addAll(db.getAllUsers());
    }

    public boolean addUser(User u){
        db.addUser(u);
        this.refreshUsers();
        return true;
    }
    private void refreshUsers(){
        this.clear();
        this.addAll(db.getAllUsers());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView=convertView;
        UserHolder holder ;
        if(rootView==null){
            rootView=LayoutInflater.from(context).inflate(R.layout.item_home_user,null);
            holder= new UserHolder();
            holder.txvUserName=(TextView)rootView.findViewById(R.id.ITEM_HOME_edt_userName);
            rootView.setTag(holder);
        }else
        {
            holder=(UserHolder)rootView.getTag();
        }

        holder.txvUserName.setText(getItem(position).getName());

        return rootView;
    }

    class UserHolder{
        TextView txvUserName;
    }

}
