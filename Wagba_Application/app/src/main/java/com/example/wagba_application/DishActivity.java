package com.example.wagba_application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.Objects;
public class DishActivity extends AppCompatActivity implements DishRecyclerViewInterface {
    int pos;
    int rest_pos;
    int number_of_times_pressed=1;
    RecyclerView recyclerView;
    ArrayList<DishInfoModel> dishInfoModels = new ArrayList<>();
    ArrayList<DishInfoModel> dishesarr = new ArrayList<>();
    FirebaseDatabase databasedish;
    FirebaseAuth auth;
    String v1,v2,v3;
    Button add_to_cartbtn;
    String uid="id";
    boolean status_done=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        Intent dishIntent = getIntent();
        pos = dishIntent.getIntExtra("pos", 0);
        rest_pos=pos;
        Log.d("firebase", Integer.toString(pos));

        //FireBase DataBase//
        databasedish = FirebaseDatabase.getInstance("https://wagba-application-93ef9-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = databasedish.getReference("Dishes");

        //remove
        //FireBase Auth
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null){
            Log.d("CART", user.getUid());
            uid=user.getUid();
        }
        DatabaseReference orderRefnew = databasedish.getReference("Users").child(uid).child("Order1");
        orderRefnew.removeValue();

        add_to_cartbtn=findViewById(R.id.add_to_cart);

        recyclerView = findViewById(R.id.rv_dishes);
        DishInfoAdapter dishInfoAdapter = new DishInfoAdapter(dishInfoModels, this);
        recyclerView.setAdapter(dishInfoAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                {
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Abo Shawarma Sandwich").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Abo Shawarma Sandwich").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Abo Shawarma Sandwich").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Arab Falafel Sandwich").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Arab Falafel Sandwich").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Arab Falafel Sandwich").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Arab Foul Sandwich").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Arab Foul Sandwich").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Arab Foul Sandwich").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Burger Cheese Sandwich").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Burger Cheese Sandwich").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Burger Cheese Sandwich").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Burger Chicken Crunchy Sandwich").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Burger Chicken Crunchy Sandwich").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Burger Chicken Crunchy Sandwich").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Coca Cola").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Coca Cola").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Coca Cola").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Koshary Jambo Box").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Koshary Jambo Box").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Koshary Jambo Box").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Koshary Regular Box").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Koshary Regular Box").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Koshary Regular Box").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Pickles").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Pickles").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Pickles").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Pizza Cheese Lovers").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Pizza Cheese Lovers").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Pizza Cheese Lovers").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("Pizza Chicken Ranch").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Pizza Chicken Ranch").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("Pizza Chicken Ranch").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("TaTa Grilled Fish").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("TaTa Grilled Fish").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("TaTa Grilled Fish").child("Availability").getValue()).toString(),
                            0));
                    dishesarr.add(new DishInfoModel(
                            (String) Objects.requireNonNull(snapshot.child("TaTa Sinjari Fish").child("Name").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("TaTa Sinjari Fish").child("Price").getValue()).toString(),
                            (String) Objects.requireNonNull(snapshot.child("TaTa Sinjari Fish").child("Availability").getValue()).toString(),
                            0));
                }

                {
                    if (pos == 0) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(0).getName(), dishesarr.get(0).getPrice(), dishesarr.get(0).getAvailability(), R.drawable.abo_shawarma_sandwich));
                        dishInfoModels.add(new DishInfoModel("Shawarma Meat", dishesarr.get(0).getPrice(), dishesarr.get(0).getAvailability(), R.drawable.abo_shawarma_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 1) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(1).getName(), dishesarr.get(1).getPrice(), dishesarr.get(1).getAvailability(), R.drawable.arab_falafel_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(2).getName(), dishesarr.get(2).getPrice(), dishesarr.get(2).getAvailability(), R.drawable.arab_foul_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 2) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(3).getName(), dishesarr.get(3).getPrice(), dishesarr.get(3).getAvailability(), R.drawable.burger_cheese_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(4).getName(), dishesarr.get(4).getPrice(), dishesarr.get(4).getAvailability(), R.drawable.burger_chicken_crunchy_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 3) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(3).getName(), dishesarr.get(3).getPrice(), dishesarr.get(3).getAvailability(), R.drawable.burger_cheese_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(4).getName(), dishesarr.get(4).getPrice(), dishesarr.get(4).getAvailability(), R.drawable.burger_chicken_crunchy_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 4) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(3).getName(), dishesarr.get(3).getPrice(), dishesarr.get(3).getAvailability(), R.drawable.burger_cheese_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(4).getName(), dishesarr.get(4).getPrice(), dishesarr.get(4).getAvailability(), R.drawable.burger_chicken_crunchy_sandwich));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 5) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(6).getName(), dishesarr.get(6).getPrice(), dishesarr.get(6).getAvailability(), R.drawable.koshary_jambo_box));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(7).getName(), dishesarr.get(7).getPrice(), dishesarr.get(7).getAvailability(), R.drawable.koshary_regular_box));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 6) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(6).getName(), dishesarr.get(6).getPrice(), dishesarr.get(6).getAvailability(), R.drawable.koshary_jambo_box));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(7).getName(), dishesarr.get(7).getPrice(), dishesarr.get(7).getAvailability(), R.drawable.koshary_regular_box));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 7) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(9).getName(), dishesarr.get(9).getPrice(), dishesarr.get(9).getAvailability(), R.drawable.pizza_cheese_lovers));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(10).getName(), dishesarr.get(10).getPrice(), dishesarr.get(10).getAvailability(), R.drawable.pizza_chicken_ranch));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 8) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(9).getName(), dishesarr.get(9).getPrice(), dishesarr.get(9).getAvailability(), R.drawable.pizza_cheese_lovers));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(10).getName(), dishesarr.get(10).getPrice(), dishesarr.get(10).getAvailability(), R.drawable.pizza_chicken_ranch));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    } else if (pos == 9) {
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(11).getName(), dishesarr.get(11).getPrice(), dishesarr.get(11).getAvailability(), R.drawable.tata_grilled_fish));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(12).getName(), dishesarr.get(12).getPrice(), dishesarr.get(12).getAvailability(), R.drawable.tata_sinjari_fish));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(5).getName(), dishesarr.get(5).getPrice(), dishesarr.get(5).getAvailability(), R.drawable.coca_cola));
                        dishInfoModels.add(new DishInfoModel(dishesarr.get(8).getName(), dishesarr.get(8).getPrice(), dishesarr.get(8).getAvailability(), R.drawable.pickles));
                    }
                }
                dishInfoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add_to_cartbtn.setOnClickListener(view -> {
            startActivity(new Intent(DishActivity.this,CartActivity.class));
        });
    }

    @Override
    public void onDishClick(int position) {
        //FireBase Auth
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null){
            Log.d("CART", user.getUid());
            uid=user.getUid();
        }
        databasedish = FirebaseDatabase.getInstance("https://wagba-application-93ef9-default-rtdb.firebaseio.com/");
        v1 =dishInfoModels.get(position).getName();
        v2 =dishInfoModels.get(position).getPrice();
        v3 =dishInfoModels.get(position).getAvailability();
        {
            Log.d("DISH ITEM PRESSED", v1);
            Log.d("DISH ITEM PRESSED", v2);
            Log.d("DISH ITEM PRESSED", v3);
        }
        DatabaseReference orderRef = databasedish.getReference("Users").child(uid).child("Order1");
        orderRef.child("dish"+number_of_times_pressed).child("availability").setValue(v3);
        orderRef.child("dish"+number_of_times_pressed).child("name").setValue(v1);
        orderRef.child("dish"+number_of_times_pressed).child("price").setValue(v2);
        number_of_times_pressed++;

        if(status_done) {
            DatabaseReference trackRef = databasedish.getReference("Users").child(uid).child("Order1");
            trackRef.child("Order Status").setValue("Order Pending");
            {
                if (rest_pos == 0) {
                    trackRef.child("Restaurant").setValue("Abo Mazen");
                } else if (rest_pos == 1) {
                    trackRef.child("Restaurant").setValue("Arabiata");
                } else if (rest_pos == 2) {
                    trackRef.child("Restaurant").setValue("Burger King");
                } else if (rest_pos == 3) {
                    trackRef.child("Restaurant").setValue("Chicken Fila");
                } else if (rest_pos == 4) {
                    trackRef.child("Restaurant").setValue("Cook Door");
                } else if (rest_pos == 5) {
                    trackRef.child("Restaurant").setValue("Koshary Hend");
                } else if (rest_pos == 6) {
                    trackRef.child("Restaurant").setValue("Koshary Tahrir");
                } else if (rest_pos == 7) {
                    trackRef.child("Restaurant").setValue("Papa John's");
                } else if (rest_pos == 8) {
                    trackRef.child("Restaurant").setValue("Pizza Hut");
                } else if (rest_pos == 9) {
                    trackRef.child("Restaurant").setValue("TaTa Seafood");
                }
            }
            status_done=false;
        }

    }
}