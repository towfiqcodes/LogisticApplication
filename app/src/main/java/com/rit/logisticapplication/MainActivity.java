package com.rit.logisticapplication;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.rit.logisticapplication.Actitvity.DashBoard;
import com.rit.logisticapplication.Actitvity.SignIn_Activity;

public class MainActivity extends AppCompatActivity {

   private ProgressBar progressBar;

   private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        progressBar=findViewById( R.id.progressBarId );

        Thread thread=new Thread( new Runnable() {
            @Override
            public void run() {
             progressBarWorking();
             startApp();
            }
        } );
        thread.start();
    }



    private void progressBarWorking() {
        for (progress=20; progress<=100;progress+=20){
            try {
                Thread.sleep( 1000 );
                progressBar.setProgress( progress );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    private void startApp() {
startActivity( new Intent( MainActivity.this,SignIn_Activity.class ) );
    }
}

