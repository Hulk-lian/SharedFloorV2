package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.RepTestsDatos.CleanTurnList;
import com.jtcode.sharedfloor.model.ClearTurn;


public class CleanTurnAdapter extends ArrayAdapter<ClearTurn> {

    public CleanTurnAdapter(Context context) {
        super(context, R.layout.item_clean_turn);
        this.addAll(CleanTurnList.getAll());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView=convertView;
        CleanHolder cleanHolder=null;

        if(rootView==null){
            cleanHolder=new CleanHolder();
            LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rootView=inflater.inflate(R.layout.item_clean_turn,null);

            cleanHolder.txvNameUser=(TextView)rootView.findViewById(R.id.ITEM_CLEANTURN_txvUserName);
            cleanHolder.txvNameTurn=(TextView)rootView.findViewById(R.id.ITEM_CLEANTURN_txvTurnDescript);

            rootView.setTag(cleanHolder);
        }
        else{
            cleanHolder=(CleanHolder)rootView.getTag();
        }
        cleanHolder.txvNameTurn.setText(getItem(position).getNameTurn());
        cleanHolder.txvNameUser.setText(getItem(position).getNameUser());

        return rootView;
    }

    class CleanHolder{
        TextView txvNameUser,txvNameTurn;
    }
}
