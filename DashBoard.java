package com.example.propay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        DatabaseManager dm = new DatabaseManager(this);
        TextView n = findViewById(R.id.name);
        TextView owe = findViewById(R.id.owe);
        TextView lent = findViewById(R.id.lent);
        RelativeLayout rl1 = findViewById(R.id.rl1);
        RelativeLayout rl2 = findViewById(R.id.rl2);
        n.setText(name);

        dm.open();
        Cursor c = dm.fetch1(name);
        c.moveToNext();
        double oweex = c.getDouble(3);
        double lentex = c.getDouble(2);
        owe.setText("$"+String.valueOf(oweex));
        lent.setText("$"+String.valueOf(lentex));

        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, Split.class);
                intent.putExtra("name", name);
                intent.putExtra("o",oweex);
                intent.putExtra("l",lentex);
                startActivity(intent);
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, Transaction.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}