package com.jtcode.sharedfloor.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.model.TypeItem;

public class TypeItemAdapter extends ArrayAdapter<TypeItem>{

    public TypeItemAdapter(Context context, int resource) {
        super(context, R.layout.layout_typeitem_typename);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;

        ItemHolder itemHolder;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            item = inflater.inflate(R.layout.layout_typeitem_typename, null);

            itemHolder= new ItemHolder();

            itemHolder.txvTypeName=(TextView) item.findViewById(R.id.ITEM_TYPEITEM_txvNameType);

            item.setTag(itemHolder);
        } else {

            itemHolder=(ItemHolder)item.getTag();
        }

        itemHolder.txvTypeName.setText(getItem(position).getNameType());

        return item;
    }
    class ItemHolder {
        TextView txvTypeName;
    }

}
