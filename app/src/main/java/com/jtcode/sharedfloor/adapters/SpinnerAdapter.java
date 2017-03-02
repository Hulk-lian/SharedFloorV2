package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.database.DatabaseManager;


public class SpinnerAdapter extends ArrayAdapter<CharSequence> {

    public SpinnerAdapter(Context context) {
        super(context,R.layout.layout_spinner_item);
        this.addAll(DatabaseManager.getInstance().getAllUsernames());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.layout_spinner_item, parent, false);
        TextView make = (TextView) row.findViewById(R.id.L_SPINNER_edt);
        make.setText(getItem(position));
        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.layout_spinner_item, parent, false);
        TextView make = (TextView) row.findViewById(R.id.L_SPINNER_edt);

        make.setText(getItem(position));
        return row;
    }
}
