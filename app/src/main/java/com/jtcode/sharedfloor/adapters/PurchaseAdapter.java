package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.RepTestsDatos.PurchaseList;
import com.jtcode.sharedfloor.model.PurchaseItem;

public class PurchaseAdapter extends ArrayAdapter<PurchaseItem>{


    public PurchaseAdapter(Context context) {
        super(context, R.layout.item_purchase_list);
        this.addAll(PurchaseList.getAll());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView= convertView;
        PurchaseHolder purchaseHolder;

        if (rootView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rootView = inflater.inflate(R.layout.item_purchase_list, null);

            purchaseHolder = new PurchaseHolder();

            purchaseHolder.txvItemName = (TextView) rootView.findViewById(R.id.ITEM_PURCHASE_txvelemtName);
            purchaseHolder.txvTypeName=(TextView)rootView.findViewById(R.id.ITEM_PURCHASE_txvtypeElemet);

            rootView.setTag(purchaseHolder);
        } else {

            purchaseHolder=(PurchaseHolder)rootView.getTag();
        }
        purchaseHolder.txvItemName.setText(getItem(position).getName());
        purchaseHolder.txvTypeName.setText(getItem(position).getType().getNameType());

        return rootView;
    }

    class PurchaseHolder{
        TextView txvTypeName,txvItemName;
    }
}
