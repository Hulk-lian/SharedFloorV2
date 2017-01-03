package com.jtcode.sharedfloor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public class FragmentCleanTurn extends Fragment {

    public static Fragment newInstance(Bundle args){
        FragmentCleanTurn frag = new FragmentCleanTurn();
        if (args != null)
            frag.setArguments(args);

        return frag;
    }
}
