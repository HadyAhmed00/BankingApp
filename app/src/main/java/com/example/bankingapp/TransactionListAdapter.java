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
    //////////////////////////////////////////////////////////////////////////////////////////////
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
        /*Trip trip = mylist.get(position);
        holder.travel_img.setImageResource(trip.getPhoto());
        holder.from.setText(trip.getFrom());
        holder.to.setText(trip.getTo());
        holder.price.setText(String.valueOf(trip.getPrice()));
        holder.time.setText(trip.getTime());
        holder.qunt.setText(String.valueOf(trip.quantity));*/
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
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int getItemCount()
    {
        return mylist.size();
    }//enetitemcountgd

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView src_name,
                 dst_name,
                 amount,
                 stat;
        ImageView srcImge,dstImge;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            /*travel_img=itemView.findViewById(R.id.travel_img_id);
            from=itemView.findViewById(R.id.fromtextid);
            to =itemView.findViewById(R.id.totextid);
            price = itemView.findViewById(R.id.pricetextid);
            time=itemView.findViewById(R.id.timetextid);
            qunt= itemView.findViewById(R.id.card_qunt);*/
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
