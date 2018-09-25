package com.rit.logisticapplication.Actitvity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rit.logisticapplication.R;
import com.rit.logisticapplication.web_api.TrackNumber;

import java.util.ArrayList;
import java.util.List;

public class Parcel_Tracking extends AppCompatActivity {
    TrackNumber trackNumber;
    TextView textView;
    public static List<String> city;
    String name=null;


    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_parcel__tracking );
        textView = findViewById( R.id.text1 );
        name = getIntent().getExtras().getString( "address" );
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (name.equalsIgnoreCase(  "Dhaka")){
            textView.setBackgroundColor( Color.BLUE );
            textView.setText( name );


        }
        else
            textView.setBackgroundColor( Color.RED );
        textView.setText( name );
    }
}



