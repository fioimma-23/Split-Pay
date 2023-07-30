package com.example.propay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Split extends AppCompatActivity {


    String givername;
    double lastval = 0;
    double o = 0, l = 0;
    int countoflist = 1;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        
        DatabaseManager dm = new DatabaseManager(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        o = intent.getDoubleExtra("o",0);
        l = intent.getDoubleExtra("l",0);
        LinearLayout sv = findViewById(R.id.lllist);

        TextView nametv = findViewById(R.id.name);
        nametv.setText(name);

        EditText toname = findViewById(R.id.nameto);
        EditText amt = findViewById(R.id.amt);

        Button add = findViewById(R.id.add);
        Button save = findViewById(R.id.save);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mydia = new AlertDialog.Builder(Split.this);
                mydia.setTitle("ENTER A NAME : ");
                EditText nameofgiver = new EditText(Split.this);
                nameofgiver.setTextSize(20);
                mydia.setView(nameofgiver);
                mydia.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        givername = nameofgiver.getText().toString();
                        View newview = getLayoutInflater().inflate(R.layout.card, null, false);
                        countoflist++;
                        TextView tv = (TextView) newview.findViewById(R.id.name);
                        arrayList.add(givername);
                        tv.setText(givername);
                        TextView tvamt = (TextView) newview.findViewById(R.id.amt1);
                        double val = Integer.parseInt(amt.getText().toString())/countoflist;
                        lastval = val;
                        tvamt.setText(String.valueOf(val));
                        sv.addView(newview);
                    }
                });
                mydia.show();
            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toname.getText().toString().equals("")){
                    Toast.makeText(Split.this, "ENTER RECEIVER NAME", Toast.LENGTH_SHORT).show();
                }
                else if(amt.getText().toString().equals("")){
                    Toast.makeText(Split.this, "ENTER AMOUNT", Toast.LENGTH_SHORT).show();
                }
                else{
                    dm.open();
                    dm.insert2(name,toname.getText().toString(),Integer.parseInt(amt.getText().toString()),1);
                    l = l + Integer.parseInt(amt.getText().toString());
                    if(arrayList.size() > 0){
                        for (String toname:arrayList) {
                            dm.insert2(name,toname,lastval,0);
                            o += lastval;
                        }
                    }
                    dm.update1(name, l, o);
                    Toast.makeText(Split.this, "Successful transaction", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Split.this, DashBoard.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }
            }
        });
    }
}