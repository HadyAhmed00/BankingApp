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

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.MyViewHolder>  {

    public ArrayList<Transaction> mylist;

    public TransactionListAdapter(ArrayList<Transaction> mylist) {
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_itme,parent,false);
        return new MyViewHolder(itemview);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Transaction tmp = mylist.get(position);
        holder.src_name.setText(tmp.getSrc().getName());
        holder.dst_name.setText(tmp.getDst().getName());
        holder.amount.setText(String.valueOf(tmp.getAmount()));
        holder.srcImge.setBackgroundResource(tmp.src.getPhoto());
        holder.dstImge.setBackgroundResource(tmp.dst.getPhoto());
        if(tmp.status)
        {
            holder.stat.setText("succeed");
            holder.stat.setTextColor(R.color.sucses);
        }
        else {
            holder.stat.setText("Fail");
            holder.stat.setTextColor(R.color.fail);
        }
    }

    @Override
    public int getItemCount()
    {
        return mylist.size();
    }//enetitemcountgd



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView src_name,
                 dst_name,
                 amount,
                 stat;
        ImageView srcImge,dstImge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            src_name = itemView.findViewById(R.id.src_name);
            srcImge = itemView.findViewById(R.id.src_imge);
            dstImge = itemView.findViewById(R.id.dst_imge);
            dst_name = itemView.findViewById(R.id.dst_name);
            amount = itemView.findViewById(R.id.trns_amount);
            stat = itemView.findViewById(R.id.stat);
        }
    }
}


//public class TransactionListAdapter {
//}
