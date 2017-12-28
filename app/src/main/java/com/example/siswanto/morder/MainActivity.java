package com.example.siswanto.morder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mMenuList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Menu");

        mMenuList = findViewById(R.id.user_manu_list);
        mMenuList.setHasFixedSize(true);
        mMenuList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting){
            startActivity(new Intent(MainActivity.this, AdminActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Menu, AdminActivity.MenuViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Menu, AdminActivity.MenuViewHolder>(
                Menu.class,
                R.layout.user_menu_row,
                AdminActivity.MenuViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(AdminActivity.MenuViewHolder viewHolder, Menu model, int position) {
                viewHolder.setNamemenu(model.getNamemenu());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setStock(model.getStock());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());
            }
        };
        mMenuList.setAdapter(firebaseRecyclerAdapter);
    }
}
