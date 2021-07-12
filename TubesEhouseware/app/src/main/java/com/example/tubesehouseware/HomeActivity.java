package com.example.tubesehouseware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    DBHelper db;
    ArrayList<String> itemId, iType, iName, iQty;
    CustomAdapter custAdapter;
    Button logout;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView emptyImage;
    TextView emptyDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DBHelper(this);
        logout = (Button)findViewById(R.id.btnLogout);

        Boolean checkSession = db.checkSession("ada");
        if (checkSession == false) {
            Intent loginIntent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        }

        emptyImage = findViewById(R.id.emptyImageView);
        emptyDataText = findViewById(R.id.imageText);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(HomeActivity.this, AddDataActivity.class);
                startActivity(in);
            }
        });

        db = new DBHelper(HomeActivity.this);
        itemId = new ArrayList<>();
        iType = new ArrayList<>();
        iName = new ArrayList<>();
        iQty = new ArrayList<>();

        storeDataInArrays();

        custAdapter = new CustomAdapter(HomeActivity.this, itemId, iType, iName, iQty);
        recyclerView.setAdapter(custAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        // logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean updtSession = db.upgradeSession("kosong", 1);
                if (updtSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

    }


    void storeDataInArrays(){
        Cursor cursor = db.readDataItem();
        if (cursor.getCount() == 0){
            emptyImage.setVisibility(View.VISIBLE);
            emptyDataText.setVisibility(View.VISIBLE);
        }
        else{
            while (cursor.moveToNext()){
                itemId.add(cursor.getString(0));
                iType.add(cursor.getString(1));
                iName.add(cursor.getString(2));
                iQty.add(cursor.getString(3));
            }
            emptyImage.setVisibility(View.GONE);
            emptyDataText.setVisibility(View.GONE);
        }
    }
}