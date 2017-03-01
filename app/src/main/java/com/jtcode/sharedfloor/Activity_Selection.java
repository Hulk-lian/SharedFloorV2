package com.jtcode.sharedfloor;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.jtcode.sharedfloor.adapters.ExpenseAdapter;
import com.jtcode.sharedfloor.adapters.HomeAdapter;
import com.jtcode.sharedfloor.adapters.PurchaseAdapter;
import com.jtcode.sharedfloor.adapters.ViewPagerAdapter;
import com.jtcode.sharedfloor.database.DatabaseManager;
import com.jtcode.sharedfloor.fragments.FragmentExpenses;
import com.jtcode.sharedfloor.fragments.FragmentPurchaseList;
import com.jtcode.sharedfloor.interfaces.CustomConstants;
import com.jtcode.sharedfloor.login.Activity_Login;
import com.jtcode.sharedfloor.model.Expense;
import com.jtcode.sharedfloor.model.PurchaseItem;

public class Activity_Selection extends AppCompatActivity implements FragmentPurchaseList.PurchaseListInteraction,FragmentExpenses.ExpenseInteraction{

    //adapters
    HomeAdapter homeAdapter;
    ExpenseAdapter expenseAdapter;
    PurchaseAdapter purchaseAdapter;
    //
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private FloatingActionButton fab;
    CoordinatorLayout parent;

    Expense expensetemp;

    DatabaseManager datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__selection);
        //DB
        datos=DatabaseManager.getInstance();

        //new TaskTestData().execute();
        init();
    }
//region asinc
    public class TaskTestData extends AsyncTask<Void,Void,Void>{
    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }
}
    //endregion
    private void init(){

        //memopry to adapter
        purchaseAdapter= new PurchaseAdapter(this);
        //expenseAdapter= new ExpenseAdapter(this);
        homeAdapter= new HomeAdapter(this,R.layout.item_home_user);

        tabLayout=(TabLayout)findViewById(R.id.A_SEL_TabLayout);

        for(String title : getResources().getStringArray(R.array.titlesTabs)) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        parent=(CoordinatorLayout)findViewById(R.id.A_SELECTION_selection);
        viewPager=(ViewPager) findViewById(R.id.A_SEL_ViewPager);
        adapter=new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),Activity_Selection.this,purchaseAdapter,expenseAdapter,homeAdapter);
        viewPager.setAdapter(adapter);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setupWithViewPager(viewPager);

        setIcon();

        viewPager.setPageTransformer(true, new AnimationSwip());


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                fabChangeIcon(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //FAB
        fab=(FloatingActionButton)findViewById(R.id.A_SEL_FAB);


    }

    private void fabChangeIcon(int position){

        switch (position) {
            case 0:
                fab.show();
                fab.setImageResource(R.drawable.ic_add_user);
                break;
            case 1:
                fab.show();
                fab.setImageResource(R.drawable.ic_add_expense);
                break;
            case 2:
                fab.show();
                fab.setImageResource(R.drawable.ic_add_item);
                break;
          /*  case 3:
                fab.hide();
                break;*/
        }
        AnimationSwip.rotateAnimation(fab);

    }

    public void clickFAB(View view) {
        int requestCode=0;
        switch (viewPager.getCurrentItem()){

            case 0:

                break;

            case 1:
                requestCode=CustomConstants.ADDEXPENSE;
              openManageExpense(null,requestCode,null);
                break;

            case 2:
                dialogAddItem();
                break;
        }
    }

    private void openManageExpense(Expense expense,int requestCode,String keyBund){
        Intent i=new Intent(Activity_Selection.this,Activity_ExpenseManage.class);
        if(keyBund!=null) {
            i.putExtra(keyBund,expense);
        }
        startActivityForResult(i,requestCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common,menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i=null;
        boolean close=false;
        switch (item.getItemId()){
            case R.id.action_logout:
                close=true;
                i= new Intent(Activity_Selection.this,Activity_Login.class);
                break;
            case R.id.action_about:
                i= new Intent(Activity_Selection.this,Activity_About.class);
                break;
        }
        if(i!=null){
            startActivity(i);
            if(close){
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setIcon(){
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_expenses);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_pruchase_list);
       // tabLayout.getTabAt(3).setIcon(R.drawable.ic_clean_turn);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CustomConstants.ADDEXPENSE:
                   // expenseAdapter.addItem((Expense) data.getParcelableExtra(CustomConstants.KEY_EXPENSE));
                    break;

                case CustomConstants.EDITEXPENSE:
                   // expenseAdapter.editItem(expensetemp, (Expense) data.getParcelableExtra(CustomConstants.KEY_EXPENSE));
                    expensetemp=null;
                    break;
        }
    }
    }

    private void dialogAddItem(){

        View v=getLayoutInflater().inflate(R.layout.layout_additem_dialog,null);

       final EditText edtemp=(EditText)v.findViewById(R.id.LD_ADD_PURCHASEITEM_edtAddItem);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(this.getString(R.string.additem_dialog_title));
        builder.setMessage(this.getString(R.string.additem_dialog_message));
        builder.setView(v);

        builder.setPositiveButton(R.string.additem_btn_ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (edtemp.getText().toString().trim().length() != 0) {
                   // purchaseAdapter.addItem(new PurchaseItem(edtemp.getText().toString()));
                    showToast(getString(R.string.additem_snackbar)+": "+edtemp.getText().toString());
                }else{
                    showToast(getString(R.string.error_item_empty_name));
                }
            }
        });

        builder.show();
    }


        //fragments
    @Override
    public void sendMensageExpense(String mens) {
        showToast(getResources().getString(R.string.deleteexpense_snackbar)+mens);
    }

    @Override
    public void sendMensagePurchaseList(String mens) {
        showToast(getResources().getString(R.string.deleteitem_snackbarmens)+mens);
    }

    @Override
    public void editExpense(Expense oldexp) {
        this.expensetemp=oldexp;
        openManageExpense(oldexp,CustomConstants.EDITEXPENSE,CustomConstants.KEY_EXPENSE);
    }

    //add user
    public void addUser(){

    }

    public void showToast(String mens){
        Snackbar.make(parent,mens,Snackbar.LENGTH_SHORT).show();
    }


}
