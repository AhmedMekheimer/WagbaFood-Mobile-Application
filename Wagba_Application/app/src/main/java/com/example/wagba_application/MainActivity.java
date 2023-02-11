package com.example.wagba_application;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements RestaurantRecyclreViewInterface {
    RecyclerView recyclerView;
    ArrayList<RestaurantsInfoModel> restaurantsInfoModels =new ArrayList<>();
    Button logoutbtn;
    Button profdatabtn;
    String value1;
    String value2;
    String value3;
    String value4;
    String value5;
    String value6;
    String value7;
    String value8;
    String value9;
    String value10;

    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FireBase DataBase//
        database = FirebaseDatabase.getInstance("https://wagba-application-93ef9-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("Restaurants");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value1=(String) snapshot.child("Abo Mazen").getValue().toString();
                value2=(String) snapshot.child("Arabiata").getValue().toString();
                value3=(String) snapshot.child("Burger King").getValue().toString();

                value4=(String) snapshot.child("Chicken Fila").getValue().toString();
                value5=(String) snapshot.child("Cook Door").getValue().toString();
                value6=(String) snapshot.child("Koshary Hend").getValue().toString();

                value7=(String) snapshot.child("Koshary Tahrir").getValue().toString();
                value8=(String) snapshot.child("Papa John's").getValue().toString();
                value9=(String) snapshot.child("Pizza Hut").getValue().toString();
                value10=(String) snapshot.child("TaTa Seafood").getValue().toString();

                Log.d("firebase","WE SSSSSSSSSSSSSS ");
                if(value1 == null || value2==null || value3==null ||
                value4 == null || value5==null || value6==null ||
                value7 == null || value8==null || value9==null ||value10==null)
                {
                    Log.d("firebase","WE FFFFFFFFFFFFFF ");
                }
                restaurantsInfoModels.add(new RestaurantsInfoModel(value1,R.drawable.abo_mazen));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value2,R.drawable.arabiata));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value3,R.drawable.burger_king));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value4,R.drawable.chicken_fila));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value5,R.drawable.cook_door));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value6,R.drawable.koshary_hend));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value7,R.drawable.koshary_tahrir));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value8,R.drawable.papa_john_s));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value9,R.drawable.pizza_hut));
                restaurantsInfoModels.add(new RestaurantsInfoModel(value10,R.drawable.tata_seafood));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logoutbtn=findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(view -> {
            auth.signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        });

        recyclerView=findViewById(R.id.rv_restaurants);
        RestaurantInfoAdapter restaurantInfoAdapter=new RestaurantInfoAdapter(restaurantsInfoModels,this);
        recyclerView.setAdapter(restaurantInfoAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


    }

    @Override
    public void OnItemClick(int position) {
        Intent dishIntent = new Intent(MainActivity.this, DishActivity.class);
        dishIntent.putExtra("pos",position);
        startActivity(dishIntent);
    }
}