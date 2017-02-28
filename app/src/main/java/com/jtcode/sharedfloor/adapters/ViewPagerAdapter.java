package com.jtcode.sharedfloor.adapters;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jtcode.sharedfloor.R;
//import com.jtcode.sharedfloor.fragments.FragmentCleanTurn;
import com.jtcode.sharedfloor.fragments.FragmentExpenses;
import com.jtcode.sharedfloor.fragments.FragmentHome;
import com.jtcode.sharedfloor.fragments.FragmentPurchaseList;
import com.jtcode.sharedfloor.interfaces.CustomConstants;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private Context context;
    Fragment fragmentShow;

    HomeAdapter homeAdapter;
    ExpenseAdapter expenseAdapter;
    PurchaseAdapter purchaseAdapter;

    public ViewPagerAdapter(FragmentManager fm,int tabCount,Context context,PurchaseAdapter purchaseAdapter,ExpenseAdapter expenseAdapter, HomeAdapter homeAdapter){
        super(fm);
        this.tabCount=tabCount;
        this.context=context;
        this.homeAdapter=homeAdapter;
        this.expenseAdapter=expenseAdapter;
        this.purchaseAdapter=purchaseAdapter;
    }

    public Fragment getFragment(){
        return fragmentShow;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle= new Bundle();
        switch (position){
            case 0:
                bundle.putString(CustomConstants.KEY_BUNDL_TEXT_TAB, context.getResources().getString(R.string.home_title));
                fragmentShow= FragmentHome.newInstance(bundle,this.homeAdapter);
                break;

            case 1:
                bundle.putString(CustomConstants.KEY_BUNDL_TEXT_TAB, context.getResources().getString(R.string.expense_title));
                fragmentShow= FragmentExpenses.newInstance(bundle,this.expenseAdapter);
                break;

            case 2:
                bundle.putString(CustomConstants.KEY_BUNDL_TEXT_TAB, context.getResources().getString(R.string.purchase_title));
                fragmentShow= FragmentPurchaseList.newInstance(bundle,this.purchaseAdapter);
                break;

           /* case 3:
                bundle.putString(CustomConstants.KEY_BUNDL_TEXT_TAB, context.getResources().getString(R.string.clean_title));
                fragmentShow= FragmentCleanTurn.newInstance(bundle);
                break;*/
        }
        return fragmentShow;
    }

    @Override
    public int getCount() {
        return tabCount;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getStringArray(R.array.titlesTabs)[position];
    }
}
