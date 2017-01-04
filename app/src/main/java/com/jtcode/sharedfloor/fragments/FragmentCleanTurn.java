package com.jtcode.sharedfloor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.adapters.CleanTurnAdapter;
import com.jtcode.sharedfloor.adapters.ExpenseAdapter;


public class FragmentCleanTurn extends Fragment {

    ListView cleanList;
    CleanTurnAdapter adapter;
    public static Fragment newInstance(Bundle args){
        FragmentCleanTurn frag = new FragmentCleanTurn();
        if (args != null)
            frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView=inflater.inflate(R.layout.fragment_clean_turn,container,false);

        cleanList=(ListView)rootView.findViewById(R.id.F_CLEAN_list);

        adapter= new CleanTurnAdapter(rootView.getContext());

        cleanList.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof ExpenseInteraction) {
            mListener = (ExpenseInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + R.string.errorImpExpenseInterface);
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // mListener = null;
    }
}
