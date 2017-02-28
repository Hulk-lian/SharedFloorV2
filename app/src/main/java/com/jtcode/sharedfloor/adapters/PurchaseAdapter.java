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
import com.jtcode.sharedfloor.model.PurchaseItem;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAdapter extends ArrayAdapter<PurchaseItem>{

    boolean ASC=false;
    public PurchaseAdapter(Context context) {
        super(context, R.layout.item_purchase_list);
    }


    class PurchaseHolder{
        TextView txvItemName;
    }
}
