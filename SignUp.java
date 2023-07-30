package com.example.propay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        DatabaseManager dm = new DatabaseManager(this);
        EditText name = findViewById(R.id.name);
        EditText pass = findViewById(R.id.pass);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "ENTER USERNAME", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().equals("")) {
                    Toast.makeText(SignUp.this, "ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                }
                else{
                    dm.open();
                    dm.insert1(name.getText().toString(),pass.getText().toString(),0,0);
                    dm.close();
                    Toast.makeText(SignUp.this, "Successful Signup", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, DashBoard.class);
                    intent.putExtra("name",name.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}