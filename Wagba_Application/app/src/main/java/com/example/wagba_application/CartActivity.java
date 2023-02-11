package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {
    FirebaseDatabase databasecart;
    RecyclerView recyclerView;
    ArrayList<CartInfoModel> cartInfoModels =new ArrayList<>();
    int dish_count=0;
    String Av;

    FirebaseAuth auth;
    String uid="id";

    int subtotal=0;
    TextView tv_subtotal;
    Button btn_place_order;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        tv_subtotal = (TextView)findViewById(R.id.subtotal);
        btn_place_order=(Button)findViewById(R.id.placeordercart);

        //FireBase Auth//
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null){
            Log.d("CART", user.getUid());
            uid=user.getUid();
        }

        //FireBase DataBase//
        //DatabaseReference orderRefnew = databasedish.getReference("Users").child(uid).child("Order1");
        databasecart = FirebaseDatabase.getInstance("https://wagba-application-93ef9-default-rtdb.firebaseio.com/");
        DatabaseReference cartRef = databasecart.getReference("Users").child(uid).child("Order1");


        recyclerView=findViewById(R.id.rv_order_details_item);
        CartInfoAdapter cartInfoAdapter=new CartInfoAdapter(cartInfoModels);
        recyclerView.setAdapter(cartInfoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(1 <= (snapshot.getChildrenCount()-2))
                {
                    /*Log.d("CART1", "IN DATA CHANGE");
                    Log.d("Dishes", snapshot.child("dish1").child("name").getValue().toString());
                    Log.d("Dishes", snapshot.child("dish1").child("price").getValue().toString());
                    Log.d("Dishes", snapshot.child("dish1").child("availability").getValue().toString());*/
                    for (dish_count = 1; dish_count <= (snapshot.getChildrenCount()-2); dish_count++) {
                        Log.d("CART", "IN FOR");
                        if(Objects.requireNonNull(snapshot.child("dish" + dish_count).child("availability").getValue()).toString().equals("YES"))
                        {
                            Av="Available";
                        }else
                        {
                            Av="Not Available";
                        }
                        cartInfoModels.add(new CartInfoModel(

                                Objects.requireNonNull(snapshot.child("dish" + dish_count).child("name").getValue()).toString(),
                                Objects.requireNonNull(snapshot.child("dish" + dish_count).child("price").getValue()).toString()+"  EGP",
                                Av

                        ));
                        Log.d("CART", "ADDED ONE DISH");
                        if(Objects.requireNonNull(snapshot.child("dish" + dish_count).child("availability").getValue()).toString().equals("YES")) {
                            String pricestring = Objects.requireNonNull(snapshot.child("dish" + dish_count).child("price").getValue()).toString();
                            subtotal += Integer.parseInt(pricestring);
                        }
                    }
                    Log.d("CART", "ADDED ALL DISHES");
                }
                cartInfoAdapter.notifyDataSetChanged();
                Log.d("CART", "NOTIFY SET CHANGED");
                Log.d("CART", Integer.toString(subtotal));
                tv_subtotal.setText(Integer.toString(subtotal));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("CART", "EEEEEEEEEEEEEEEEEEEEEEEEE");
            }
        });

        btn_place_order.setOnClickListener(view -> {
            startActivity(new Intent(CartActivity.this,TrackingActivity.class));
        });
    }
}