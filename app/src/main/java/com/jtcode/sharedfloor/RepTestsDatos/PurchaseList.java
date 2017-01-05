package com.jtcode.sharedfloor.RepTestsDatos;


import com.jtcode.sharedfloor.model.PurchaseItem;
import com.jtcode.sharedfloor.model.TypeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PurchaseList {

    private static ArrayList<PurchaseItem> listaPurchase;

    private static PurchaseList ourInstance = new PurchaseList();

    public static PurchaseList getInstance() {
        return ourInstance;
    }

    private PurchaseList() {
        listaPurchase= new ArrayList<>();

        List<TypeItem> typeItems=TypeItemList.getAll();

        String[] nombre={"Prozac","una cabra","Papel higienico","Ambientador","ojo de triton" , "Donetes", "Kit - Kat","las 7 bolas de dragon","Papel de cocina","Cola-cao","servilletas"};
        Random posRnd=new Random();
        int nombrepos=0;

        for (int i=0;i<20;i++){
            try {
                nombrepos=(int)(posRnd.nextDouble()*nombre.length);
                Thread.sleep(10);

            } catch (InterruptedException e) {e.printStackTrace();}

            PurchaseItem p=(new PurchaseItem(nombre[nombrepos]));

            listaPurchase.add(p);
        }

    }
    public static List<PurchaseItem> getAll(){
        return listaPurchase;
    }

}
