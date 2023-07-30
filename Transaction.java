package com.example.propay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Transaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        TextView nametv = findViewById(R.id.name);
        nametv.setText(name);

        LinearLayout ll = findViewById(R.id.llist);

        DatabaseManager dm = new DatabaseManager(this);
        dm.open();
        Cursor c = dm.fetch2(name);
        while (c.moveToNext()){
            View newview = getLayoutInflater().inflate(R.layout.card, null, false);
            TextView toname = (TextView) newview.findViewById(R.id.name);
            TextView amt1 = (TextView) newview.findViewById(R.id.amt1);
            toname.setText(c.getString(1));
            amt1.setText(String.valueOf(c.getInt(2)));
            if(c.getInt(3) == 0){
                amt1.setTextColor(Color.parseColor("#E91E63"));
            }else{
                amt1.setTextColor(Color.parseColor("#4CAF50"));
            }
            ll.addView(newview);
        }

    }
}