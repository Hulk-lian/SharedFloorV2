package com.jtcode.sharedfloor.adapters;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.fragments.FragmentCleanTurn;
import com.jtcode.sharedfloor.fragments.FragmentExpenses;
import com.jtcode.sharedfloor.fragments.FragmentHome;
import com.jtcode.sharedfloor.fragments.FragmentsPurchaseList;
import com.jtcode.sharedfloor.interfaces.CustomConstants;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private Context context;

    public ViewPagerAdapter(FragmentManager fm,int tabCount,Context context){
        super(fm);
        this.tabCount=tabCount;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragmentShow=null;
        Bundle bundle= new Bundle();
        switch (position){
            case 0:
                bundle.putString(CustomConstants.KEY_BUNDL_TEXT_TAB, context.getResources().getString(R.string.home_title));
                fragmentShow= FragmentHome.newInstance(bundle);
                break;

            case 1:
                bundle.putString(CustomConstants.KEY_BUNDL_TEXT_TAB, context.getResources().getString(R.string.expense_title));
                fragmentShow= FragmentExpenses.newInstance(bundle);
                break;

            case 2:
                bundle.putString(CustomConstants.KEY_BUNDL_TEXT_TAB, context.getResources().getString(R.string.purchase_title));
                fragmentShow= FragmentsPurchaseList.newInstance(bundle);
                break;

            case 3:
                bundle.putString(CustomConstants.KEY_BUNDL_TEXT_TAB, context.getResources().getString(R.string.clean_title));
                fragmentShow= FragmentCleanTurn.newInstance(bundle);
                break;
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
