package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;

public class HomeAdapter extends CursorAdapter{

    int layout;
    public HomeAdapter(Context context, int layout) {
        super(context, null, layout);
        this.layout=layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);

        View rootView=inflater.inflate(layout,parent,false);

        UserHolder holder= new UserHolder();

        holder.txvUserName=(TextView)rootView.findViewById(R.id.ITEM_HOME_edt_userName);
        rootView.setTag(holder);

        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        UserHolder holder=(UserHolder)view.getTag();
        holder.txvUserName.setText(cursor.getString(1));
    }

    class UserHolder{
        TextView txvUserName;
    }

}
