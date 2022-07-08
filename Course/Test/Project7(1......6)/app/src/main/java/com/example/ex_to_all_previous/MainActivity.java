package com.example.ex_to_all_previous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.search)
        {

        }

        if (id == R.id.contactus)
        {

        }

        if (id == R.id.signup)
        {
            Intent myintent = new Intent(this,Main4Activity.class);
            startActivity(myintent);
        }

        if (id == R.id.login)
        {
            Intent myintent = new Intent(this,Main2Activity.class);
            startActivity(myintent);
        }

        return super.onOptionsItemSelected(item);
    }
}
