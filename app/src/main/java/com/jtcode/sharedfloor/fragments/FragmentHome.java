package com.jtcode.sharedfloor.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.adapters.HomeAdapter;


public class FragmentHome extends Fragment {

    private EditText edtHomeName;
    private RecyclerView listUserOnHome;
    private static HomeAdapter adapterHome;

    public static Fragment newInstance(Bundle args,HomeAdapter ahome){
        FragmentHome frag = new FragmentHome();
        if (args != null)
            frag.setArguments(args);
        adapterHome=ahome;
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView=inflater.inflate(R.layout.fragment_home,container,false);

        //adapterHome= new HomeAdapter(rootView.getContext());

        edtHomeName=(EditText)rootView.findViewById(R.id.F_HOME_edtNameHome);
        edtHomeName.setEnabled(false);

        listUserOnHome=(RecyclerView)rootView.findViewById(R.id.F_HOME_ReciclerViewUseronHome);

        listUserOnHome.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        listUserOnHome.setAdapter(adapterHome);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
