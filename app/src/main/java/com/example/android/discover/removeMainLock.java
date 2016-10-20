package com.example.android.discover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class removeMainLock extends AppCompatActivity {
Button remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_main_lock);
        remove=(Button)findViewById(R.id.button);
        remove.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {

            }
        }


        );
    }
}
