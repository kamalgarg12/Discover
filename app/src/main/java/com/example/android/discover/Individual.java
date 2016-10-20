package com.example.android.discover;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Individual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_lock);
    }




    public void openLockMenu1(View view) {
        switch (view.getId()) {

            case R.id.add_lock:
                Intent i1 = new Intent(Individual.this, addIndividualLock.class);
                startActivity(i1);


            case R.id.change_lock:

                Intent i2 = new Intent(Individual.this, changeIndividualLock.class);
                startActivity(i2);

            case R.id.remove_lock:

                Intent i3 = new Intent(Individual.this, removeIndividualLock.class);
                startActivity(i3);


        }


    }
}
