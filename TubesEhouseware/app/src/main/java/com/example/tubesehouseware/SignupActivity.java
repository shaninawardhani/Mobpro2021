package com.example.tubesehouseware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    DBHelper db;
    Button backlogin, submitsignup;
    EditText username2, password2, passwordConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DBHelper(this);

        username2 = (EditText)findViewById(R.id.teksUnameSignup);
        password2 = (EditText)findViewById(R.id.teksPasswordSignup);
        passwordConf = (EditText)findViewById(R.id.teksConfirmPassword);
        backlogin = (Button)findViewById(R.id.btnLoginSignup);
        submitsignup = (Button)findViewById(R.id.btnSignupSignup);

        //login
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
        submitsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username2.getText().toString();
                String strPassword = password2.getText().toString();
                String strPasswordConf = passwordConf.getText().toString();
                if (strPassword.equals(strPasswordConf)) {
                    boolean daftar = db.insertUser(strUsername, strPassword);
                    if (daftar == true) {
                        Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}