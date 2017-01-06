package com.jtcode.sharedfloor.RepTestsDatos;


import com.jtcode.sharedfloor.model.PurchaseItem;

import java.util.ArrayList;
import java.util.Collections;
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

        String[] nombre={"Prozac","una cabra","Papel higienico","Ambientador","ojo de triton" ,
                "Donetes", "Kit - Kat","las 7 bolas de dragon","Papel de cocina","Cola-cao","servilletas","lejia","Algo para picar"};
        Random posRnd=new Random();
        int nombrepos=0;

       /* for (int i=0;i<20;i++){
            try {
                nombrepos=(int)(posRnd.nextDouble()*nombre.length);
                Thread.sleep(10);

            } catch (InterruptedException e) {e.printStackTrace();}

            PurchaseItem p=(new PurchaseItem(nombre[nombrepos]));

            listaPurchase.add(p);
        }*/
        //debug
        for(int i=0;i<nombre.length;i++){
            listaPurchase.add(new PurchaseItem(nombre[i]));
        }

    }

    public static List<PurchaseItem> getAll(){
        return listaPurchase;
    }

    public static void add(PurchaseItem p){
        listaPurchase.add(p);
    }

    public static void delete(PurchaseItem p){
        listaPurchase.remove(p);
    }

    public static void replace(PurchaseItem old, PurchaseItem newitem){
        int positem=listaPurchase.indexOf(old);
        listaPurchase.remove(old);
        listaPurchase.add(positem,newitem);
    }

    public static void sort(boolean asc){
        if (asc)
            Collections.sort(listaPurchase);
        else
            Collections.reverse(listaPurchase);
    }
    public static boolean containsItem(PurchaseItem p){
        return listaPurchase.contains(p);
    }

}
