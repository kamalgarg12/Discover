
package com.example.android.discover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class changeMainLock extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter ;
    EditText password,old,repass,security;
    Button update, cancel;
    CheckBox check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_main_lock);
        update=(Button)findViewById(R.id.update_btn);
        password=(EditText)findViewById(R.id.password_edt);
        old=(EditText)findViewById(R.id.password_old);
        repass=(EditText)findViewById(R.id.repassword_edt);
        security=(EditText)findViewById(R.id.securityhint_edt);
        check= (CheckBox)findViewById(R.id.checkBox1);
       // register=(Button)findViewById(R.id.register_btn);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){
// TODO Auto-generated method stub
                if (!isChecked) {password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        }

        );

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldpass=old.getText().toString();
                String Pass=password.getText().toString();
                String Secu=security.getText().toString();
                String Repass=repass.getText().toString();

                if(oldpass.equals("")||Pass.equals("")||Repass.equals("")||Secu.equals(""))
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
                    loginDataBaseAdapter.insertEntry(Pass, Repass,Secu);

// reg_btn.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Log.d("PASSWORD",Pass);
                    Log.d("RE PASSWORD",Repass);
                    Log.d("SECURITY HINT",Secu);
                    Intent i=new Intent(changeMainLock.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent ii=new Intent(changeMainLock.this,MainActivity.class);
                startActivity(ii);
            }
        });
    }

}
