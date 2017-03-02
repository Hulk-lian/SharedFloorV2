package com.jtcode.sharedfloor.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jtcode.sharedfloor.Activity_About;
import com.jtcode.sharedfloor.Activity_ExpenseManage;
import com.jtcode.sharedfloor.Activity_Selection;
import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.adapters.ExpenseAdapter;
import com.jtcode.sharedfloor.interfaces.CustomConstants;
import com.jtcode.sharedfloor.interfaces.SendStuff;
import com.jtcode.sharedfloor.model.Expense;


public class FragmentExpenses extends Fragment{

    private ListView listExpenses;
    private static ExpenseAdapter expenseAdapter;
   private ExpenseInteraction callBack;
    
    public static Fragment newInstance(Bundle args,ExpenseAdapter eAdapter){
        FragmentExpenses frag = new FragmentExpenses();
        if (args != null)
            frag.setArguments(args);
        expenseAdapter=eAdapter;
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView=inflater.inflate(R.layout.fragment_expense,container,false);

        listExpenses=(ListView)rootView.findViewById(R.id.F_EXPENSE_list);

        listExpenses.setAdapter(expenseAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listExpenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callBack.editExpense(expenseAdapter.getItem(position));
            }
        });

        listExpenses.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                confirmation(expenseAdapter.getItem(position));
                return true;
            }
        });
    }

    private void confirmation(final Expense expense) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle(getContext().getString(R.string.deleteexpense_dialog_title));
        builder.setMessage(getContext().getString(R.string.deleteexpense_dialog_mens)+" "+ expense.getName());
        builder.setPositiveButton(R.string.deleteitem_dialog_okbtn, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
               expenseAdapter.removeExpernse(expense);
                callBack.sendMensageExpense(expense.getName());
            }
        });
        builder.setNegativeButton(R.string.deleteitem_dialog_cancelbtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       if (context instanceof ExpenseInteraction) {
            callBack = (ExpenseInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + R.string.errorImpExpenseInterface);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBack = null;
    }

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
        void sendMensageExpense(String mens);
        void editExpense(Expense oldexp);
    }
}
