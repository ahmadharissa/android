package com.ahmadharissa.age;

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


        Button button = findViewById(R.id.buttonid1);
        final EditText name = findViewById(R.id.nameid1);
        final EditText age = findViewById(R.id.ageid1);
        final TextView text = findViewById(R.id.text4);
        final TextView text1 = findViewById(R.id.textid1);


        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v ){
                String namee = name.getText().toString();
                String agee = age.getText().toString();

                if(namee.matches(""))
                    text.setText("Enter your name");
                else if(agee.matches(""))
                    text.setText("Enter your age");
                else
                {
                    int iage= Integer.valueOf(age.getText().toString());

                    text1.setText("Hello "+namee);

                    if (iage>=18 && iage<=35 )
                    {
                        text.setText(namee+", you are big");

                    }else if ( iage>35 && iage<=49)
                    {
                        text.setText(namee+", you are very big");
                    }else if ( iage>=50)
                    {
                        text.setText(namee+", you are old");
                    }else
                    {
                        text.setText(namee+", you are child get out of here");
                    }
                }

            }
        });
    }
}
