package com.example.wagba_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartInfoAdapter extends RecyclerView.Adapter<CartInfoAdapter.ViewHolder> {

    ArrayList<CartInfoModel>cartInfoModelsInternal;

    public CartInfoAdapter(ArrayList<CartInfoModel> cartInfoModels) {
        this.cartInfoModelsInternal = cartInfoModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.order_details_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartInfoModel cartInfoModel=cartInfoModelsInternal.get(position);
        holder.nametv.setText(cartInfoModel.getName());
        holder.pricetv.setText(cartInfoModel.getPrice());
        holder.availabilitytv.setText(cartInfoModel.getAvailability());
    }

    @Override
    public int getItemCount() {
        return cartInfoModelsInternal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nametv, pricetv, availabilitytv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nametv=itemView.findViewById(R.id.dish_name_cart);
            pricetv=itemView.findViewById(R.id.price_cart);
            availabilitytv=itemView.findViewById(R.id.availability_cart);

        }
    }
}
