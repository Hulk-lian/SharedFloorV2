package com.jtcode.sharedfloor;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jtcode.sharedfloor.adapters.ViewPagerAdapter;
import com.jtcode.sharedfloor.fragments.FragmentsPurchaseList;
import com.jtcode.sharedfloor.model.PurchaseItem;

public class Activity_Selection extends AppCompatActivity implements FragmentsPurchaseList.PurchaseListInteraction{


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__selection);
        init();
    }
    private void init(){

        tabLayout=(TabLayout)findViewById(R.id.A_SEL_TabLayout);

        for(String title : getResources().getStringArray(R.array.titlesTabs)) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        viewPager=(ViewPager) findViewById(R.id.A_SEL_ViewPager);
        adapter=new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),Activity_Selection.this);
        viewPager.setAdapter(adapter);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setupWithViewPager(viewPager);

        setIcon();

        viewPager.setPageTransformer(true, new AnimationSwip());

        //FAB
        fab=(FloatingActionButton)findViewById(R.id.A_SEL_FAB);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fab in the 4 fragment action
                switch (viewPager.getCurrentItem()){
                    case 0:
                        break;

                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:

                        break;
                }
            }
        });
    }


    private void setIcon(){
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_expenses);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_pruchase_list);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_clean_turn);
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
    public void onPurchaseItemLongClick(PurchaseItem item) {

    }
}
