package com.ahmadharissa.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText enter1 = findViewById(R.id.enter1id);
        final EditText enter2 = findViewById(R.id.enter2id);
        final TextView result = findViewById(R.id.resultid);
        Button X = findViewById(R.id.Xid);
        Button division = findViewById(R.id.divisionid);
        Button minus = findViewById(R.id.minusid);
        Button plus = findViewById(R.id.plusid);
        Button mode = findViewById(R.id.modeid);



        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nume1 = enter1.getText().toString();
                String nume2 = enter2.getText().toString();

                if (nume1.matches("") || nume2.matches(""))
                    result.setText("You did enter 2 number");
                else
                {
                    int num1 = Integer.parseInt(enter1.getText().toString());
                    int num2 = Integer.parseInt(enter2.getText().toString());
                    int sum = num1 + num2;
                    result.setText(Integer.toString(sum));
                }
            }
        });

        X.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nume1 = enter1.getText().toString();
                String nume2 = enter2.getText().toString();

                if (nume1.matches("") || nume2.matches(""))
                    result.setText("You did enter 2 number");
                else
                {
                    int num1 = Integer.parseInt(enter1.getText().toString());
                    int num2 = Integer.parseInt(enter2.getText().toString());
                    long sum = num1 * num2 ;
                    result.setText(""+sum);
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nume1 = enter1.getText().toString();
                String nume2 = enter2.getText().toString();

                if (nume1.matches("") || nume2.matches(""))
                    result.setText("You did enter 2 number");
                else
                {
                    int num1 = Integer.parseInt(enter1.getText().toString());
                    int num2 = Integer.parseInt(enter2.getText().toString());
                    int sum = num1 - num2;
                    result.setText(Integer.toString(sum));
                }
            }
        });

        mode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nume1 = enter1.getText().toString();
                String nume2 = enter2.getText().toString();

                if (nume1.matches("") || nume2.matches(""))
                    result.setText("You did enter 2 number");
                else if(nume2.matches("0"))
                    result.setText("c'ant "+nume1+" mode 0");
                else
                {
                    int num1 = Integer.parseInt(enter1.getText().toString());
                    int num2 = Integer.parseInt(enter2.getText().toString());
                    int sum = num1 % num2;
                    result.setText(Integer.toString(sum));
                }
            }
        });

        division.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nume1 = enter1.getText().toString();
                String nume2 = enter2.getText().toString();

                if (nume1.matches("") || nume2.matches(""))
                    result.setText("You did enter 2 number");
                else
                {
                    double num1 = Integer.parseInt(enter1.getText().toString());
                    double num2 = Integer.parseInt(enter2.getText().toString());
                    double sum = num1 / num2 ;
                    result.setText(""+sum);
                }
            }
        });
    }

    public void clear(View v){

        final EditText enter1 = findViewById(R.id.enter1id);
        final EditText enter2 = findViewById(R.id.enter2id);
        final TextView result = findViewById(R.id.resultid);

        enter1.setText("");
        enter2.setText("");
        result.setText("Result");

    }

    public void pawer(View v) {

        final EditText enter1 = findViewById(R.id.enter1id);
        final EditText enter2 = findViewById(R.id.enter2id);
        final TextView result = findViewById(R.id.resultid);

        String nume1 = enter1.getText().toString();
        String nume2 = enter2.getText().toString();

        if (nume1.matches("") || nume2.matches(""))
            result.setText("You did enter 2 number");
        else
        {
            int num1 = Integer.parseInt(enter1.getText().toString());
            int num2 = Integer.parseInt(enter2.getText().toString());
            int sum =1;
            for(int i=1 ; i<=num2 ; i++)
                sum = sum*num1 ;
           
            result.setText(Integer.toString(sum));
        }
    }

    public void pawer2(View v) {

        final EditText enter1 = findViewById(R.id.enter1id);
        final TextView result = findViewById(R.id.resultid);

        String nume1 = enter1.getText().toString();

        if (nume1.matches(""))
            result.setText("You did enter 1 number");
        else
        {
            int num1 = Integer.parseInt(enter1.getText().toString());
            int sum =0;
            for(int i=1 ; i<=num1 ; i++)
                sum = sum+num1 ;

            result.setText(Integer.toString(sum));
        }
    }

    public void vector(View v) {

        final EditText enter1 = findViewById(R.id.enter1id);
        final TextView result = findViewById(R.id.resultid);

        String nume1 = enter1.getText().toString();

        if (nume1.matches(""))
            result.setText("You did enter 1 number");
        else
        {
            int num1 = Integer.parseInt(enter1.getText().toString());
            long sum =1;
            for(int i=1 ; i<=num1 ; i++)
                sum = sum*num1 ;

            result.setText(""+sum);
        }
    }

    public void radical(View v) {

        final EditText enter1 = findViewById(R.id.enter1id);
        final TextView result = findViewById(R.id.resultid);

        String nume1 = enter1.getText().toString();

        if (nume1.matches(""))
            result.setText("You did enter 1 number");
        else
        {
            double num1 = Integer.parseInt(enter1.getText().toString());
            double sum =Math.sqrt(num1);
            result.setText(Double.toString(sum));
        }
    }

    public void positive(View v) {

        final EditText enter1 = findViewById(R.id.enter1id);
        final TextView result = findViewById(R.id.resultid);
        String nume1 = enter1.getText().toString();

        if (nume1.matches(""))
        {  result.setText("You did enter 1 number");}
        else if (Integer.parseInt(nume1)>0)
        {   result.setText(""+Integer.parseInt(nume1));}
        else
        {
            int sum =Integer.parseInt(nume1)* -1;
            result.setText(Integer.toString(sum));
        }
    }

    public void visible(View v){

        Button mode = findViewById(R.id.modeid);
        Button radical = findViewById(R.id.radicalid);
        Button vector = findViewById(R.id.vectorid);
        Button positive = findViewById(R.id.positiveid);
        Button pawer = findViewById(R.id.pawerid);
        final TextView forchild = findViewById(R.id.forchildid);
        Button pawer2 = findViewById(R.id.pawer2id);
        Button child = findViewById(R.id.childid);
        Button show = findViewById(R.id.showid);


        mode.setVisibility(View.GONE);
        radical.setVisibility(View.GONE);
        vector.setVisibility(View.GONE);
        positive.setVisibility(View.GONE);
        pawer.setVisibility(View.GONE);
        forchild.setVisibility(View.VISIBLE);
        pawer2.setVisibility(View.GONE);
        child.setAlpha(.1f);
        show.setVisibility(View.VISIBLE);

    }

    public void show(View v){

        Button mode = findViewById(R.id.modeid);
        Button radical = findViewById(R.id.radicalid);
        Button vector = findViewById(R.id.vectorid);
        Button positive = findViewById(R.id.positiveid);
        Button pawer = findViewById(R.id.pawerid);
        final TextView forchild = findViewById(R.id.forchildid);
        Button pawer2 = findViewById(R.id.pawer2id);
        Button child = findViewById(R.id.childid);
        Button show = findViewById(R.id.showid);


        mode.setVisibility(View.VISIBLE);
        radical.setVisibility(View.VISIBLE);
        vector.setVisibility(View.VISIBLE);
        positive.setVisibility(View.VISIBLE);
        pawer.setVisibility(View.VISIBLE);
        forchild.setVisibility(View.GONE);
        pawer2.setVisibility(View.VISIBLE);
        child.setAlpha(1f);
        show.setVisibility(View.GONE);

    }
}
