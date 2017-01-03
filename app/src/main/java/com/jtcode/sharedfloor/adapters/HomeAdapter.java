package com.jtcode.sharedfloor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jtcode.sharedfloor.R;
import com.jtcode.sharedfloor.RepTestsDatos.UsersHomeLIST;
import com.jtcode.sharedfloor.model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeUsersHolder>{
    Context context;
    ArrayList<User> userList=null;
    private int nUsersonHome=0;


    public  HomeAdapter(Context context)
    {
        this.context=context;
        this.userList=new ArrayList<>(UsersHomeLIST.getAll());
        this.nUsersonHome=UsersHomeLIST.getNumber();
    }

    @Override
    public HomeUsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(this.context).inflate(R.layout.item_home_user,null);
        return new HomeUsersHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeUsersHolder holder, int position) {
        holder.txvUserName.setText(userList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return this.nUsersonHome;
    }

    public static class HomeUsersHolder extends RecyclerView.ViewHolder{
        TextView txvUserName;

        public HomeUsersHolder(View item){
            super(item);
            txvUserName=(TextView)item.findViewById(R.id.ITEM_HOME_edt_userName);
        }
    }
}
