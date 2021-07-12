package com.example.tubesehouseware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {
    Button addbutton;
    EditText ettype, etname, etqty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        ettype = findViewById(R.id.inputType);
        etname = findViewById(R.id.inputName);
        etqty = findViewById(R.id.inputQty);
        addbutton = findViewById(R.id.btnAdd2);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(AddDataActivity.this);
                boolean input = db.insertItem(ettype.getText().toString().trim(),
                        etname.getText().toString().trim(),
                        Integer.valueOf(etqty.getText().toString().trim()));
                if (input == true) {
                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent successIntent = new Intent(AddDataActivity.this, HomeActivity.class);
                    startActivity(successIntent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}