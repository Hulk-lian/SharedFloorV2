package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.model.ClearTurn;


public class CleanTurnAdapter extends ArrayAdapter<ClearTurn> {

    public CleanTurnAdapter(Context context) {
        super(context, R.layout.item_clean_turn);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView=convertView;
        CleanHolder cleanHolder=null;

        if(rootView==null){

        }
        else{

        }
        cleanHolder.txvNameTurn.setText(getItem(position).getName());

        return rootView;
    }

    class CleanHolder{
        TextView txvNameUser,txvNameTurn;
    }
}
