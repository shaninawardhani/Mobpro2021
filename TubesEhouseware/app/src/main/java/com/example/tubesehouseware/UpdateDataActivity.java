package com.example.tubesehouseware;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataActivity extends AppCompatActivity {

    EditText editType, editName, editQty;
    Button updateButton, deleteButton;

    String id, type, name, qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        editType = findViewById(R.id.editType);
        editName = findViewById(R.id.editName);
        editQty = findViewById(R.id.editQty);
        updateButton = findViewById(R.id.btnUpdate);
        deleteButton = findViewById(R.id.btnDelete);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setTitle("Update Item of " + type);
        }

        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBHelper db = new DBHelper(UpdateDataActivity.this);
                boolean update = db.updateDataItem(id, editType.getText().toString().trim(),
                        editName.getText().toString().trim(),
                        Integer.valueOf(editQty.getText().toString().trim()));
                if (update == true) {
                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent successIntent = new Intent(UpdateDataActivity.this, HomeActivity.class);
                    startActivity(successIntent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("type") && getIntent().hasExtra("name") && getIntent().hasExtra("qty")){
            // getting data from intent
            id = getIntent().getStringExtra("id");
            type = getIntent().getStringExtra("type");
            name = getIntent().getStringExtra("name");
            qty = getIntent().getStringExtra("qty");

            //setting intent data
            editType.setText(type);
            editName.setText(name);
            editQty.setText(qty);
        }
        else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + type + "?");
        builder.setMessage("Are you sure you want to delete " + type + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper db = new DBHelper(UpdateDataActivity.this);
                boolean delete = db.deleteOneRow(id);
                if (delete == true){
                    Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    Intent delIntent = new Intent(UpdateDataActivity.this, HomeActivity.class);
                    startActivity(delIntent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}