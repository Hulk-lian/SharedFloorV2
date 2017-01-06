package com.jtcode.sharedfloor.RepTestsDatos;


import com.jtcode.sharedfloor.model.Expense;
import com.jtcode.sharedfloor.model.Home;
import com.jtcode.sharedfloor.model.PurchaseItem;
import com.jtcode.sharedfloor.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpensesList {

    private static List<Expense> expenseList;

    private static ExpensesList ourInstance = new ExpensesList();

    public static ExpensesList getInstance() {
        return ourInstance;
    }

    public static void addExpense(Expense e){
        if (expenseList==null){
            expenseList= new ArrayList<>();
        }
          expenseList.add(e);
    }

    private ExpensesList() {
        String[] nombres={"luz","Gas","Agua","Internet","Café","Limpieza"};
        List<User> usuarios=UsersHomeLIST.getAll();

        Home choso= new Home("choso de usuarido",usuarios.get(0));
        Random posRnd=new Random();
        int posRandom;
        int posus;

        for (int i=0;i<20;i++){
            posRandom=(int)(posRnd.nextDouble()*nombres.length);
            posus=(int)(posRnd.nextDouble()*usuarios.size());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {e.printStackTrace();}

            addExpense(new Expense(nombres[posRandom],posRandom*10.5,usuarios.get(posus),choso));
        }
    }

    public  static List<Expense> getAll(){
     return expenseList;
    }

    public static void add(Expense expense){
        expenseList.add(expense);
    }

    public static void delete(Expense expense){
        expenseList.remove(expense);
    }

    public static void replace(Expense old, Expense newExpense){
        int posItem=expenseList.indexOf(old);
        expenseList.remove(old);
        expenseList.add(posItem,newExpense);
    }

    public static boolean containsItem(Expense expense){
        return expenseList.contains(expense);
    }
}
