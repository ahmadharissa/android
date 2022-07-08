package com.example.ex_to_all_previous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void login(View view)
    {
        EditText email = findViewById(R.id.A2Email);
        //EditText password = findViewById(R.id.A2Password);
        String Email = email.getText().toString();
        //String Pass = password.getText().toString();

        Intent myintent = new Intent(this,Main3Activity.class);
        Bundle b = new Bundle();
        b.putString("email",Email);
        //b.putString("pass",Pass);
        myintent.putExtras(b);
        startActivity(myintent);
    }
}
