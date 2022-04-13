//package com.example.bankingapp;
//
//public class ToUserAdapter {
//}

package com.example.bankingapp;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToUserAdapter extends RecyclerView.Adapter<ToUserAdapter.MyViewHolder>  {

    public ArrayList<User> mylist;


    public OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void onItemclick(int position);
    }

    public void setonItemclickListener (OnItemClickListener listener) {
        mlistener = listener;
    }


    public ToUserAdapter(ArrayList<User> mylist) {
        this.mylist = mylist;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_itme,parent,false);
        return new MyViewHolder(itemview);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User tmp = mylist.get(position);
        holder.name.setText(tmp.getName());
        holder.balace.setText(String.valueOf(tmp.getBalace()));
        holder.photo.setBackgroundResource(tmp.photo);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount()
    {
        return mylist.size();
    }//enetitemcountgd

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,
                balace;
        ImageView photo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.user_name);
            balace = itemView.findViewById(R.id.balace);
            photo = itemView.findViewById(R.id.photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mlistener!= null )
                    {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION)
                        {
                            mlistener.onItemclick(pos);
                        }
                    }

                }
            });

        }
    }
}

