package com.example.siswanto.morder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView mMenuList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Menu");

        mMenuList = findViewById(R.id.user_manu_list);
        mMenuList.setHasFixedSize(true);
        mMenuList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            startActivity(new Intent(AdminActivity.this, PostActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Menu, MenuViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Menu, MenuViewHolder>(
                Menu.class,
                R.layout.menu_row,
                MenuViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Menu model, int position) {
                viewHolder.setNamemenu(model.getNamemenu());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setStock(model.getStock());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());
            }
        };
        mMenuList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public MenuViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setNamemenu(String namemenu){
            TextView post_namemenu = mView.findViewById(R.id.post_menuname);
            post_namemenu.setText(namemenu);
        }

        public void setPrice(String price){
            TextView post_price = mView.findViewById(R.id.post_price);
            post_price.setText(price);
        }

        public void setStock(String stock){
            TextView post_stock = mView.findViewById(R.id.post_stock);
            post_stock.setText(stock);
        }

        public void setDesc(String desc){
            TextView post_desc = mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String image){
            ImageView post_image = mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }
    }
}
