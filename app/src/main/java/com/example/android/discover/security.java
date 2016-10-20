package com.example.android.discover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class security extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security);

    }

    public void openLockMenu(View view){
        switch (view.getId()){

            case R.id.main_lock:
                Intent i1= new Intent(security.this,Main.class);
                startActivity(i1);


            case R.id.individual_lock:

                Intent i2= new Intent(security.this,Individual.class);
                startActivity(i2);
        }
    }
}
