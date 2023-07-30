package com.example.propay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseManager dm = new DatabaseManager(this);
        EditText name = findViewById(R.id.name);
        EditText pass = findViewById(R.id.pass);
        Button btn = findViewById(R.id.btn);
        RelativeLayout rl = findViewById(R.id.reg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "ENTER USERNAME", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                }
                else{
                    dm.open();
                    Cursor c = dm.fetch1(name.getText().toString());
                    if(c.moveToNext() == false){
                        Toast.makeText(MainActivity.this, "please signup", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(MainActivity.this, DashBoard.class);
                        intent.putExtra("name",name.getText().toString());
                        startActivity(intent);
                    }
                    dm.close();
                }
            }
        });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}