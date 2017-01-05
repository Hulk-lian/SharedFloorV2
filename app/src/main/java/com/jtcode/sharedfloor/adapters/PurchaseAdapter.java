package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.RepTestsDatos.PurchaseList;
import com.jtcode.sharedfloor.model.PurchaseItem;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAdapter extends ArrayAdapter<PurchaseItem>{

    private ArrayList<PurchaseItem> strikes;

    public PurchaseAdapter(Context context) {
        super(context, R.layout.item_purchase_list);
        this.addAll(PurchaseList.getAll());
        strikes= new ArrayList<>();
    }

    public List<PurchaseItem> getSelectedItems(){
        return strikes;
    }

    public void removeStrike(PurchaseItem item){
        strikes.remove(item);
    }

    public void updateView(){

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rootView= convertView;
        final PurchaseHolder purchaseHolder;

        if (rootView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rootView = inflater.inflate(R.layout.item_purchase_list, null);

            purchaseHolder = new PurchaseHolder();

            purchaseHolder.txvItemName = (TextView) rootView.findViewById(R.id.ITEM_PURCHASE_txvelemtName);

            rootView.setTag(purchaseHolder);
        } else {

            purchaseHolder=(PurchaseHolder)rootView.getTag();
        }
        purchaseHolder.txvItemName.setText(getItem(position).getName());

        return rootView;
    }


    class PurchaseHolder{
        TextView txvItemName;
    }
}
