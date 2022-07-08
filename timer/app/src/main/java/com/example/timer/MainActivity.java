package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    counter ct ;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.text);
        ct = new counter(60000,1);
    }

    NotificationManager manager;
    static  int id = 1 ;
    public void stop(View view)
    {
        ct.cancel();
        manager.cancelAll();
    }

    public void start(View view)
    {
        /*
        NotificationCompat.Builder nbuild = new NotificationCompat.Builder(this)
                .setContentTitle("Clock")
                .setContentText("Timer is runing")
                .setSmallIcon(R.drawable.ic_clock);
        manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id,nbuild.build());
        id++;
        */

        ct.start();
    }

    class counter extends CountDownTimer
    {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            txt.setText(String.valueOf(millisUntilFinished));
        }

        @Override
        public void onFinish()
        {
            txt.setText("Done");
        }
    }
}

