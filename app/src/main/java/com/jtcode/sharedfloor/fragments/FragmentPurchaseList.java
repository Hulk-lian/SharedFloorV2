package com.jtcode.sharedfloor.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.adapters.PurchaseAdapter;
import com.jtcode.sharedfloor.model.PurchaseItem;


public class FragmentPurchaseList extends Fragment {

    private ListView purchaseList;
    private PurchaseAdapter purchaseAdapter;
    private PurchaseListInteraction callBack;
    private Context context;

    public static Fragment newInstance(Bundle args){
        FragmentPurchaseList frag = new FragmentPurchaseList();
        if (args != null)
            frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_purchase,container,false);

        context=view.getContext();

        purchaseAdapter= new PurchaseAdapter(view.getContext());

        purchaseList=(ListView)view.findViewById(R.id.F_PURCHASE_list);
        purchaseList.setAdapter(purchaseAdapter);

        callBack=(PurchaseListInteraction)context;

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callBack = (PurchaseListInteraction) activity;
        } catch (ClassCastException cex){
            throw new ClassCastException(activity.toString() + " must implement FragmentIterationListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        callBack=null;
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
        boolean onPurchaseItemLongClick(PurchaseItem item);//para que se muestre si se quiere eliminar o editar

    }
}
