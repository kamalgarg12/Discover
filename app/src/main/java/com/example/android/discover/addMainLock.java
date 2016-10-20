package com.example.android.discover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class addMainLock extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText password,repassword_edt,securityhint_edt;
    Button register, cancel;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main_lock);
        password=(EditText)findViewById(R.id.password_edt);
        repassword_edt=(EditText)findViewById(R.id.repassword_edt);
        securityhint_edt=(EditText)findViewById((R.id.securityhint_edt));
        check= (CheckBox)findViewById(R.id.checkBox1);
        register=(Button)findViewById(R.id.register_btn);

        check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){
// TODO Auto-generated method stub
                if (!isChecked) {password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        }

        );
        register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                String Pass=password.getText().toString();
                String Secu=securityhint_edt.getText().toString();
                String Repass=repassword_edt.getText().toString();

                if(Pass.equals("")||Repass.equals("")||Secu.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!Pass.equals(Repass))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
// Save the Data in Database
                    loginDataBaseAdapter.insertEntry1(Pass, Repass,Secu);

// reg_btn.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Log.d("PASSWORD",Pass);
                    Log.d("RE PASSWORD",Repass);
                    Log.d("SECURITY HINT",Secu);
                    Intent i=new Intent(addMainLock.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });

        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent ii=new Intent(addMainLock.this,MainActivity.class);
                startActivity(ii);
            }
        });
    }


}
