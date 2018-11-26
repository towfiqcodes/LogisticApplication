package com.rit.logisticapplication;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.rit.logisticapplication.Actitvity.DashBoard;
import com.rit.logisticapplication.Actitvity.SignIn_Activity;

public class MainActivity extends AppCompatActivity {
   private ProgressBar progressBar;
ImageView imageView;
   private int progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        progressBar=findViewById( R.id.progressBarId );
       imageView=findViewById( R.id.logo );
        Animation animation= AnimationUtils.loadAnimation( this, R.anim.my_anim);
        imageView.startAnimation(animation);
        Thread thread=new Thread( new Runnable() {
            @Override
            public void run() {
             progressBarWorking();
             startApp();
            }
        } );
        thread.start();
    }


//splashscreen
    private void progressBarWorking() {
        for (progress=25; progress<=100;progress+=25){
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

