package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.database.DatabaseHelper;
import com.jtcode.sharedfloor.database.DatabaseManager;
import com.jtcode.sharedfloor.model.Expense;



public class ExpenseAdapter extends ArrayAdapter<Expense> {

    DatabaseManager db;
    Context context;

    public ExpenseAdapter(Context context) {
        super(context, R.layout.item_expense);
        db= DatabaseManager.getInstance();
        this.addAll(db.getAllExpenses());
        this.context=context;
    }
    //add
    public boolean addExpense(Expense e){
        boolean res=true;
        db.addExpense(e);
        this.refreshList();

        return res;
    }
    //remove
    public boolean removeExpernse(Expense e){
        boolean res=true;

        this.remove(e);
        db.deleteExpense(e);
        notifyDataSetChanged();

        return res;
    }

    //update
    public boolean updateExpernse(Expense enew){
        db.updateExpense(enew);
        this.refreshList();
        return true;
    }

    //reflesh list
    private void refreshList(){
        this.clear();
        this.addAll(db.getAllExpenses());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView =convertView;
        ExpenseHolder holder;
        if(rootView==null){
            rootView=LayoutInflater.from(context).inflate(R.layout.item_expense,null);
            holder= new ExpenseHolder();
            holder.txvamountExp=(TextView)rootView.findViewById(R.id.ITEM_EXPENSE_txvAmount);
            holder.txvnameExp=(TextView)rootView.findViewById(R.id.ITEM_EXPENSE_txvName);
            holder.txvwhopaidExp=(TextView)rootView.findViewById(R.id.ITEM_EXPENSE_txvWhoPaid);
            holder.txvpriceperuserExp=(TextView)rootView.findViewById(R.id.ITEM_EXPENSE_txvAmountPerUser);

            rootView.setTag(holder);
        }else{
            holder=(ExpenseHolder)rootView.getTag();
        }

        holder.txvnameExp.setText(getItem(position).getName());
        holder.txvamountExp.setText(String.valueOf(getItem(position).getAmount()));
        holder.txvwhopaidExp.setText(getItem(position).getPaid());
        holder.txvpriceperuserExp.setText(String.format("%.2f",getItem(position).getAmountPerUser()));

        return rootView;
    }

    class ExpenseHolder {
        TextView txvnameExp,txvamountExp,txvwhopaidExp,txvpriceperuserExp;
    }
}
