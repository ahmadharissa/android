package com.ahmadharissa.reverse;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText input = findViewById(R.id.inputid);
        final TextView text3 = findViewById(R.id.text3id);
        Button reverse = findViewById(R.id.buttonid);


        reverse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String input1 = input.getText().toString();
                String s = "";

                for (int i=input1.length()-1;i>=0;i--)
                {
                    s=s+input1.charAt(i);
                }
                text3.setText(""+s);
            }

        });
    }
}