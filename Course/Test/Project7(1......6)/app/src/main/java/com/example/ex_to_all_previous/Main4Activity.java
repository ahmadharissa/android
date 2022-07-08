package com.example.ex_to_all_previous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity
{
    EditText username;
    EditText email;
    EditText password;
    EditText confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void send(View view)
    {
        username = findViewById(R.id.A4Username);
        //email = findViewById(R.id.A4Email);
        //password = findViewById(R.id.A4Password);

        String User = username.getText().toString();
        //String Email = email.getText().toString();
        //String Pass = password.getText().toString();

        Intent myitent = new Intent(this,Main5Activity.class);
        Bundle b = new Bundle();
        b.putString("user", User);
        //b.putString("email", Email);
        //b.putString("pass", Pass);
        myitent.putExtras(b);
        startActivity(myitent);
    }

    public void cancel(View view)
    {
        username = findViewById(R.id.A4Username);
        email = findViewById(R.id.A4Email);
        password = findViewById(R.id.A4Password);
        confirm = findViewById(R.id.A4Confirm);

        username.setText("");
        email.setText("");
        password.setText("");
        confirm.setText("");
    }
}
