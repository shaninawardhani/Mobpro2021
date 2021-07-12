package com.example.tubesehouseware;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList itemId, itemType, itemName, itemQty;

    CustomAdapter(Context context, ArrayList itemId, ArrayList itemType, ArrayList itemName, ArrayList itemQty){
        this.context = context;
        this.itemId = itemId;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemQty = itemQty;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.itemIdTx.setText(String.valueOf(itemId.get(position)));
        holder.itemTypeTx.setText(String.valueOf(itemType.get(position)));
        holder.itemNameTx.setText(String.valueOf(itemName.get(position)));
        holder.itemQtyTx.setText(String.valueOf(itemQty.get(position)));
        holder.addDataLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(context, UpdateDataActivity.class);
                in.putExtra("id", String.valueOf(itemId.get(position)));
                in.putExtra("type", String.valueOf(itemType.get(position)));
                in.putExtra("name", String.valueOf(itemName.get(position)));
                in.putExtra("qty", String.valueOf(itemQty.get(position)));
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemId.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemIdTx, itemTypeTx, itemNameTx, itemQtyTx;
        LinearLayout addDataLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIdTx = itemView.findViewById(R.id.itemIdText);
            itemTypeTx = itemView.findViewById(R.id.itemTypeText);
            itemNameTx = itemView.findViewById(R.id.itemNameText);
            itemQtyTx = itemView.findViewById(R.id.itemQtyText);
            addDataLayout = itemView.findViewById(R.id.addDataLayout);
        }
    }
}
