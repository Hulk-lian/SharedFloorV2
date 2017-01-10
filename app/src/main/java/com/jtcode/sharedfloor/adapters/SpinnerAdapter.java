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
import com.jtcode.sharedfloor.RepTestsDatos.WhoPaidList;


public class SpinnerAdapter extends ArrayAdapter<CharSequence> {

    public SpinnerAdapter(Context context) {
        super(context,R.layout.layout_spinner_item,WhoPaidList.getAll());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.layout_spinner_item, parent, false);
        TextView make = (TextView) row.findViewById(R.id.L_SPINNER_edt);
        Typeface myTypeFace = Typeface.createFromAsset(getContext().getAssets(),"font.ttf");
        make.setTypeface(myTypeFace);
        make.setText(getItem(position));
        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.layout_spinner_item, parent, false);

        TextView make = (TextView) row.findViewById(R.id.L_SPINNER_edt);

        Typeface myTypeFace = Typeface.createFromAsset(getContext().getAssets(),"font.ttf");
        make.setTypeface(myTypeFace);
        make.setText(getItem(position));
        return row;
    }
}
