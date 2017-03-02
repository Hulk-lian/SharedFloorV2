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
import com.jtcode.sharedfloor.database.DatabaseHelper;
import com.jtcode.sharedfloor.database.DatabaseManager;
import com.jtcode.sharedfloor.model.PurchaseItem;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAdapter extends ArrayAdapter<PurchaseItem>{

    boolean ASC=false;
    DatabaseManager db;
    Context context;

    public PurchaseAdapter(Context context) {
        super(context, R.layout.item_purchase_list);
        db=DatabaseManager.getInstance();
        this.addAll(db.getAllPurchase());
        this.context=context;
    }

    public boolean addItem(PurchaseItem item){
        boolean res= true;
        db.addPurchase(item);
        this.add(item);
        notifyDataSetChanged();
        return res;
    }

    public boolean removeItem(PurchaseItem item){
        boolean res=true;
        remove(item);
        db.removeItem(item);
        notifyDataSetChanged();
        return res;
    }

    public void updateItems(){
        this.clear();
        this.addAll(db.getAllPurchase());
        notifyDataSetChanged();
    }

    public void strikeItem(PurchaseItem item,boolean strike){
        this.getItem(this.getPosition(item)).setStrike(strike);
        db.updateItem(item);
        notifyDataSetChanged();
    }

    //sort item
    public void sortAlph(){
        ASC=!ASC;
        this.clear();
        this.addAll(db.getSorted(ASC));
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootview=convertView;
        PurchaseHolder holder;
        if(rootview==null){
            holder=new PurchaseHolder();
            rootview=LayoutInflater.from(context).inflate(R.layout.item_purchase_list,null);
            holder.txvItemName=(TextView)rootview.findViewById(R.id.ITEM_PURCHASE_txvelemtName);
            rootview.setTag(holder);
        }
        else{
            holder=(PurchaseHolder)rootview.getTag();
        }
        if(!getItem(position).isStrike()){
            holder.txvItemName.setPaintFlags(holder.txvItemName.getPaintFlags()&(~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        else{
            holder.txvItemName.setPaintFlags(holder.txvItemName.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.txvItemName.setText(getItem(position).getName());

        return rootview;
    }

    class PurchaseHolder{
        TextView txvItemName;
    }
}
