package com.ahmadharissa.full_name;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText fName = findViewById(R.id.fn);
        final EditText lName = findViewById(R.id.ln);

        Button btnName = findViewById(R.id.b1);

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = fName.getText() + " " + lName.getText();
                Toast.makeText(getApplicationContext(), fullName, Toast.LENGTH_SHORT).show();
            }
        });

        Button btnNext = findViewById(R.id.b2);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("Name", "ahmad");
                startActivity(i);
            }
        });
    }
    public void koko(View v) {

        setContentView(R.layout.newlayout);
    }
    public void lolo(View v) {

        setContentView(R.layout.activity_main);
    }
}