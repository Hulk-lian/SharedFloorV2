package com.jtcode.sharedfloor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jtcode.sharedfloor.RepTestsDatos.UsersHomeLIST;
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
            createExpense();
            Intent i= new Intent();
            i.putExtra(CustomConstants.KEY_EXPENSE,expenseTmp);
            setResult(RESULT_OK,i);
            finish();
        }
    }
    private void createExpense(){
        expenseTmp.setName(tilName.getEditText().getText().toString());
        expenseTmp.setAmount(Double.parseDouble(tilAmount.getEditText().getText().toString()));
        //expense user who paid
        expenseTmp.setAmountPerUser(UsersHomeLIST.getNumber());
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
