package com.jtcode.sharedfloor.presenters;

import android.content.Context;
import android.view.View;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.interfaces.IPurchaseItemPresenter;
import com.jtcode.sharedfloor.model.PurchaseItem;


public class PurchasePresenter implements IPurchaseItemPresenter {

    View view;
    Context context;

    public PurchasePresenter(IPurchaseItemPresenter.View vista, Context context) {
        this.view = vista;
        this.context=context;
    }

    @Override
    public void addItem(PurchaseItem item) {

        view.showPurchaseList();
    }

    @Override
    public void deleteItem(PurchaseItem item) {
        view.showMessage(context.getResources().getString(R.string.deleteMens),item);
        loadItems();
    }

    @Override
    public PurchaseItem getPurchaseItem(int item) {
        return null;
    }

    @Override
    public void loadItems() {
       /* if(PurchaseList.getAll().isEmpty())
            view.showEmptyState(true);
        else
            view.showPurchaseList();*/
    }

    @Override
    public void updateItem(PurchaseItem itemold, PurchaseItem itemnew) {
       //PurchaseList.replace(itemold,itemnew);
    }

    @Override
    public void onDestroy() {
        this.view=null;
        this.context=null;
    }
}
