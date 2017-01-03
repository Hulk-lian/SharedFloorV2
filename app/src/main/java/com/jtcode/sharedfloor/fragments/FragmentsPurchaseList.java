package com.jtcode.sharedfloor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public class FragmentsPurchaseList extends Fragment {

    public static Fragment newInstance(Bundle args){
        FragmentsPurchaseList frag = new FragmentsPurchaseList();
        if (args != null)
            frag.setArguments(args);

        return frag;
    }
}
