package com.example.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText username,pass;
    Button login;
    Button forgetPassword;
    ProgressDialog mDialog;
    String userid,pass1;
    String item;
    int flag=0;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getReference("adminData");
        getSupportActionBar().hide();

        username =  (EditText) findViewById(R.id.username);
        pass = findViewById(R.id.password);
        forgetPassword = findViewById(R.id.forget);
        login = findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userid = username.getText().toString();
                pass1 = pass.getText().toString();
                String num = "+918875542682";
                mDialog = new ProgressDialog(Login.this);
                mDialog.setMessage("Please Wait..." + userid);
                mDialog.setTitle("Loading");
                mDialog.show();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("teacher_regData");

                if(userid.equals("admin") && pass1.equals("adminpass")){
                    mDialog.dismiss();
                    Bundle basket= new Bundle();
                    basket.putString("number", num);
                    Intent intent = new Intent(Login.this,OTP.class);
                    intent.putExtras(basket);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    mDialog.dismiss();
                    Toast.makeText(Login.this, "Invalid AdminID and Password", Toast.LENGTH_SHORT).show();
                }

            }




        });
    }

    private static long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            finish();
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        }
        else {
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

}
