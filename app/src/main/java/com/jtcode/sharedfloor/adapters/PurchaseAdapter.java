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

    boolean ASC=false;
    public PurchaseAdapter(Context context) {
        super(context, R.layout.item_purchase_list);
        this.addAll(PurchaseList.getAll());
    }

    public boolean addItem(PurchaseItem item){
        boolean res=true;
        if(canAddItem(item)) {
            add(item);
            PurchaseList.add(item);
            notifyDataSetChanged();
        }else{
            res=false;
        }
        return res;
    }

    private boolean canAddItem(PurchaseItem p){
        boolean canAdd=true;
        if(PurchaseList.containsItem(p)){
            canAdd=false;
        }
        return  canAdd;
    }

    public boolean removeItem(PurchaseItem item){
        remove(item);
        PurchaseList.delete(item);
        notifyDataSetChanged();

        //debug move
        return true;
    }

    public void sortAlph(){
        ASC=!ASC;
        PurchaseList.sort(ASC);
        updateItems();
    }

    public void updateItems(){
        this.clear();
        this.addAll(PurchaseList.getAll());
    }

    public void strikeItem(PurchaseItem item,boolean strike){
        this.getItem(this.getPosition(item)).setStrike(strike);
        PurchaseList.replace(item,this.getItem(this.getPosition(item)));
        notifyDataSetChanged();
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

        if(!getItem(position).getStrike()){
            purchaseHolder.txvItemName.setPaintFlags(purchaseHolder.txvItemName.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        else{
            purchaseHolder.txvItemName.setPaintFlags(purchaseHolder.txvItemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        purchaseHolder.txvItemName.setText(getItem(position).getName());

        return rootView;
    }

    class PurchaseHolder{
        TextView txvItemName;
    }
}
