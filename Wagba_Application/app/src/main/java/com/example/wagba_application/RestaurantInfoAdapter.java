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

public class RestaurantInfoAdapter extends RecyclerView.Adapter<RestaurantInfoAdapter.Viewholder> {
    private final RestaurantRecyclreViewInterface restaurantRecyclreViewInterface;
    ArrayList<RestaurantsInfoModel>restaurantsInfoModelsInternal;

    public RestaurantInfoAdapter(ArrayList<RestaurantsInfoModel> restaurantsInfoModels,RestaurantRecyclreViewInterface restaurantRecyclreViewInterface) {
        this.restaurantsInfoModelsInternal = restaurantsInfoModels;
        this.restaurantRecyclreViewInterface=restaurantRecyclreViewInterface;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.restaurant_item,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        RestaurantsInfoModel restaurantsInfoModel=restaurantsInfoModelsInternal.get(position);
        holder.name.setText(restaurantsInfoModel.getName());
        holder.imgv.setImageDrawable(holder.itemView.getContext().getDrawable(restaurantsInfoModel.getImg()));
    }

    @Override
    public int getItemCount() {
        return restaurantsInfoModelsInternal.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imgv;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.restaurant_name);
            imgv=itemView.findViewById(R.id.restaurant_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(restaurantRecyclreViewInterface!= null){
                        int pos =getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION)
                        {
                            restaurantRecyclreViewInterface.OnItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
