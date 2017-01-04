package com.jtcode.sharedfloor.RepTestsDatos;


import com.jtcode.sharedfloor.model.TypeItem;

import java.util.ArrayList;
import java.util.List;

public class TypeItemList {

    private static List<TypeItem> typeItemList;

    private static TypeItemList ourInstance = new TypeItemList();

    public static TypeItemList getInstance() {
        return ourInstance;
    }

    private TypeItemList() {
        typeItemList= new ArrayList<>();
        typeItemList.add(new TypeItem("Lacteos"));
        typeItemList.add(new TypeItem("Reposteria"));
        typeItemList.add(new TypeItem("Desayuno"));
        typeItemList.add(new TypeItem("Bebidas"));
        typeItemList.add(new TypeItem("Infusiones"));
        typeItemList.add(new TypeItem("Cafes"));
        typeItemList.add(new TypeItem("Utensilios"));
        typeItemList.add(new TypeItem("Elementos de Limpieza"));
        typeItemList.add(new TypeItem("Consumibles Cocina"));
        typeItemList.add(new TypeItem("Consumibles Baño"));
        typeItemList.add(new TypeItem("Farmacia"));
        typeItemList.add(new TypeItem("Parafarmacia"));
        typeItemList.add(new TypeItem("Otros"));
    }
    public static List<TypeItem> getAll(){
        return typeItemList;
    }
}
