package com.example.wagba_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DishInfoAdapter extends RecyclerView.Adapter<DishInfoAdapter.ViewHolder> {
    private final DishRecyclerViewInterface dishRecyclerViewInterface;

    ArrayList<DishInfoModel>dishInfoModelsInternal;

    public DishInfoAdapter(ArrayList<DishInfoModel> dishInfoModels,DishRecyclerViewInterface dishRecyclerViewInterface) {
        this.dishInfoModelsInternal = dishInfoModels;
        this.dishRecyclerViewInterface=dishRecyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dish_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view,dishRecyclerViewInterface);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DishInfoModel dishInfoModel=dishInfoModelsInternal.get(position);
        holder.name.setText(dishInfoModel.getName());
        holder.price.setText(dishInfoModel.getPrice());
        holder.availability.setText(dishInfoModel.getAvailability());
        holder.imgv.setImageDrawable(holder.itemView.getContext().getDrawable(dishInfoModel.getImg()));

    }

    @Override
    public int getItemCount() {
        return dishInfoModelsInternal.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,availability;
        ImageView imgv;
        public ViewHolder(@NonNull View itemView ,DishRecyclerViewInterface dishRecyclerViewInterface) {
            super(itemView);
            name=itemView.findViewById(R.id.dish_name);
            price=itemView.findViewById(R.id.dish_price);
            availability=itemView.findViewById(R.id.dish_available);
            imgv=itemView.findViewById(R.id.dish_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dishRecyclerViewInterface!=null)
                    {
                        int pos=getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION)
                        {
                            dishRecyclerViewInterface.onDishClick(pos);
                        }
                    }
                }
            });
        }
    }
}
