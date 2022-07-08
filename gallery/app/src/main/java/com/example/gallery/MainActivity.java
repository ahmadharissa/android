package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int i =0 ;
    int[] array = new int[]{R.drawable.komodo,R.drawable.sublime,R.drawable.best1};
    public void next(View view)
    {
        ImageView mypic = findViewById(R.id.imageView);
        mypic.setImageResource(array[i]);
        i++;
        if (i==array.length)
            i=0;
    }
}
