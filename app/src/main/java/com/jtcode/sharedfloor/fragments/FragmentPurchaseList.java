package com.jtcode.sharedfloor.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.adapters.PurchaseAdapter;
import com.jtcode.sharedfloor.interfaces.SendStuff;
import com.jtcode.sharedfloor.model.PurchaseItem;


public class FragmentPurchaseList extends Fragment implements SendStuff.SendItem{

    private ListView purchaseList;
    private static PurchaseAdapter purchaseAdapter;
    private PurchaseListInteraction callBack;

    private Context context;

    public static Fragment newInstance(Bundle args,PurchaseAdapter adapter) {
        FragmentPurchaseList frag = new FragmentPurchaseList();
        if (args != null)
            frag.setArguments(args);

        purchaseAdapter=adapter;
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchase, container, false);

        context = view.getContext();

       // purchaseAdapter = new PurchaseAdapter(view.getContext());

        purchaseList = (ListView) view.findViewById(R.id.F_PURCHASE_list);
        purchaseList.setAdapter(purchaseAdapter);

        callBack = (PurchaseListInteraction) context;

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        purchaseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PurchaseItem tmp = purchaseAdapter.getItem(i);
                purchaseAdapter.strikeItem(tmp, !tmp.getStrike());
            }
        });

        purchaseList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                confirmation(purchaseAdapter.getItem(i));
                return true;
            }
        });
    }

    private void confirmation(final PurchaseItem item) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle(getContext().getString(R.string.deleteitem_dialog_title));
        builder.setMessage(getContext().getString(R.string.deleteitem_dialog_mens)+" " + item.getName());
        builder.setPositiveButton(R.string.deleteitem_dialog_okbtn, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
               purchaseAdapter.removeItem(item);
                callBack.sendMensagePurchaseList(item.getName());

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_purchaselist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_purchaseList_sort_alph:
                purchaseAdapter.sortAlph();
                purchaseAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callBack = (PurchaseListInteraction) activity;
        } catch (ClassCastException cex) {
            throw new ClassCastException(activity.toString() + " must implement FragmentIterationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBack = null;
    }

    @Override
    public void sendItem(PurchaseItem item) {
        this.purchaseAdapter.addItem(item);
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
    public interface PurchaseListInteraction {
        // TODO: Update argument type and name
        void sendMensagePurchaseList(String mens);
    }

}
