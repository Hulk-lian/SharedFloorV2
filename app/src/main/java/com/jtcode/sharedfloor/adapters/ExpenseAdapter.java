package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.RepTestsDatos.ExpensesList;
import com.jtcode.sharedfloor.model.Expense;



public class ExpenseAdapter extends ArrayAdapter<Expense> {

    Context context;

    public ExpenseAdapter(Context context) {
        super(context, R.layout.fragment_expense);
        addAll(ExpensesList.getAll());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item= convertView;
        ExpenseHolder expenseHolder;

        if(item==null){
            //create the inflater object
            LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //inflate the view
            item=inflater.inflate(R.layout.item_expense,null);

            //memory for the holder
            expenseHolder= new ExpenseHolder();

            //vinculate
            expenseHolder.txvnameExp=(TextView)item.findViewById(R.id.ITEM_EXPENSE_txvName);
            expenseHolder.txvamountExp=(TextView)item.findViewById(R.id.ITEM_EXPENSE_txvAmount);
            expenseHolder.txvwhopaindExp=(TextView)item.findViewById(R.id.ITEM_EXPENSE_txvWhoPaid);
            expenseHolder.txvpriceperuserExp=(TextView)item.findViewById(R.id.ITEM_EXPENSE_txvAmountPerUser);

            item.setTag(expenseHolder);
        }
        else{
            expenseHolder=(ExpenseHolder)item.getTag();
        }

        expenseHolder.txvnameExp.setText(getItem(position).getName());
        expenseHolder.txvamountExp.setText(String.valueOf(getItem(position).getAmount()));
        expenseHolder.txvwhopaindExp.setText(getItem(position).getPaid().getName());
        expenseHolder.txvpriceperuserExp.setText(String.valueOf(getItem(position).getAmountPerUser()));

        return item;
    }

    class ExpenseHolder {
        TextView txvnameExp,txvamountExp,txvwhopaindExp,txvpriceperuserExp;
    }
}
