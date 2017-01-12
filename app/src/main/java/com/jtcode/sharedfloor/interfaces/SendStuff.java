package com.jtcode.sharedfloor.interfaces;


import com.jtcode.sharedfloor.model.PurchaseItem;

public interface SendStuff {
    interface SendExpense{
        void sendNewExpense();
        void sendEditedExpense();
    }
    interface SendItem{
        void sendItem(PurchaseItem item);
    }
}
