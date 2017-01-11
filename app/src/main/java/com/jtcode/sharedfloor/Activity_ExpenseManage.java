package com.jtcode.sharedfloor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;

import com.jtcode.sharedfloor.adapters.SpinnerAdapter;
import com.jtcode.sharedfloor.interfaces.CustomConstants;
import com.jtcode.sharedfloor.model.Expense;
public class Activity_ExpenseManage extends AppCompatActivity{

    TextInputLayout tilName,tilAmount;
    Spinner spnPaid;
    FloatingActionButton fabSave;

    SpinnerAdapter spinnerAdapter;
    Expense expenseTmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_manager);
        init();
        try{
            this.expenseTmp =  getIntent().getExtras().getParcelable(CustomConstants.KEY_EXPENSE);
        }catch (Exception e){expenseTmp=null;}

        if(expenseTmp!=null){
            chargeData();
        }
    }
    private void init(){
        tilName=(TextInputLayout)findViewById(R.id.A_EXPENSE_MANAGER_tilNameExpense);
        tilAmount=(TextInputLayout)findViewById(R.id.A_EXPENSE_MANAGER_tilAmount);
        spnPaid=(Spinner)findViewById(R.id.A_EXPENSE_MANAGER_spinerwhoPaid);
        fabSave=(FloatingActionButton)findViewById(R.id.A_EXPENSE_MANAGER_fab);

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        spinnerAdapter= new SpinnerAdapter(Activity_ExpenseManage.this);
        spnPaid.setAdapter(spinnerAdapter);
    }

    private void chargeData(){
        tilName.getEditText().setText(expenseTmp.getName());
        tilAmount.getEditText().setText(String.valueOf(expenseTmp.getAmount()));

        if (!expenseTmp.getPaid().getName().equals(null)) {
            int spinnerPosition = spinnerAdapter.getPosition( expenseTmp.getPaid().getName());
            spnPaid.setSelection(spinnerPosition);
        }
    }
    private void save(){
        if(okAllData()){

        }
    }
    private boolean okAllData(){
        boolean res=true;

        if(TextUtils.isEmpty(tilName.getEditText().getText().toString().trim()) ||
                TextUtils.isEmpty(tilAmount.getEditText().getText().toString().trim())){
            res=false;
         }
        return res;
    }
}
