package com.jtcode.sharedfloor.RepTestsDatos;


import com.jtcode.sharedfloor.model.Expense;
import com.jtcode.sharedfloor.model.Home;
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
        User usuario= new User("Usuarido","eluser69@suspenso.com","upcuo");
        Home choso= new Home("choso de usuarido",usuario);
        Random posRnd=new Random();
        int posRandom;

        for (int i=0;i<10;i++){
            posRandom=(int)(posRnd.nextDouble()*nombres.length);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {e.printStackTrace();}

            addExpense(new Expense(nombres[posRandom],posRandom*10.5,usuario,choso));
        }
    }

    public  static List<Expense> getAll(){
     return expenseList;
    }
}
