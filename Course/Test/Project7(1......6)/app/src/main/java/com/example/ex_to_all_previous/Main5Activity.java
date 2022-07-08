package com.example.ex_to_all_previous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        show = findViewById(R.id.A5Show);
        Bundle b = getIntent().getExtras();
        String user = b.getString("user");
        //String email = b.getString("email");
        //String pass = b.getString("pass");

        String showb = getResources().getString(R.string.showB1);
        String showa = getResources().getString(R.string.showA);
        show.setText(showb + " " + user + " " + showa);
    }

    public void back(View view)
    {
        /*
        Main3Activity M3 =new Main3Activity();
        M3.back(view);
        */
        Intent myintent = new Intent(this, MainActivity.class);
        startActivity(myintent);
    }
}
