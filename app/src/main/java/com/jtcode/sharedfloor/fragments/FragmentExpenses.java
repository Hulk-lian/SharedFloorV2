package com.jtcode.sharedfloor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.adapters.ExpenseAdapter;
import com.jtcode.sharedfloor.model.Expense;


public class FragmentExpenses extends Fragment {

    ListView listExpenses;
    ExpenseAdapter expenseAdapter;
   // private ExpenseInteraction mListener;
    
    public static Fragment newInstance(Bundle args){
        FragmentExpenses frag = new FragmentExpenses();
        if (args != null)
            frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView=inflater.inflate(R.layout.fragment_expense,container,false);

        listExpenses=(ListView)rootView.findViewById(R.id.F_EXPENSE_list);

        expenseAdapter= new ExpenseAdapter(rootView.getContext());

        listExpenses.setAdapter(expenseAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /if (context instanceof ExpenseInteraction) {
            mListener = (ExpenseInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + R.string.errorImpExpenseInterface);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ExpenseInteraction {
        // TODO: Update argument type and name
        void onItemShortClick(Expense expense);
    }
}
