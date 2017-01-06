package com.jtcode.sharedfloor.interfaces;


import com.jtcode.sharedfloor.model.PurchaseItem;

public interface IPurchaseItemPresenter {
    void addItem(PurchaseItem item);
    void deleteItem(PurchaseItem item);
    void updateItem(PurchaseItem itemold,PurchaseItem itemnew);
    PurchaseItem getPurchaseItem(int item);
    void loadItems();
    void onDestroy();

    interface View{
        void showPurchaseList();
        void showEmptyState(boolean show);
        void showMessage(String message, PurchaseItem purchaseItem);
    }
}
