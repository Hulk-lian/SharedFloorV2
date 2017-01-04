package com.jtcode.sharedfloor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.adapters.PurchaseAdapter;


public class FragmentsPurchaseList extends Fragment {

    private ListView purchaseList;
    private PurchaseAdapter purchaseAdapter;

    public static Fragment newInstance(Bundle args){
        FragmentsPurchaseList frag = new FragmentsPurchaseList();
        if (args != null)
            frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_purchase,container,false);
        purchaseList=(ListView)view.findViewById(R.id.F_PURCHASE_list);

        purchaseAdapter= new PurchaseAdapter(view.getContext());

        purchaseList.setAdapter(purchaseAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
