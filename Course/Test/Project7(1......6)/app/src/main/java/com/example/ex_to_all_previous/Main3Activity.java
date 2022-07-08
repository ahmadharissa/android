package com.example.ex_to_all_previous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView show = findViewById(R.id.A3Show);

        Bundle b = getIntent().getExtras();
        String email = b.getString("email");
        //String pass = b.getString("pass");

        String showb = getResources().getString(R.string.showB);
        String showa = getResources().getString(R.string.showA);
        show.setText(showb + " " + email + " " + showa);
    }

    public void back(View view)
    {
        Intent myintent = new Intent(this, MainActivity.class);
        startActivity(myintent);
    }
}
