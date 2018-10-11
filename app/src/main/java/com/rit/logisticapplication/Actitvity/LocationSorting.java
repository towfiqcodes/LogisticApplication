package com.rit.logisticapplication.Actitvity;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.rit.logisticapplication.R;

import java.util.Arrays;

public class LocationSorting extends AppCompatActivity {
    TextView fromColorTv, toColorTV,manifestNoTV,checkInDateTV,batchNoTV,totalShipmentsTV;
    String name = null;
    public static String[] From = {"08000", "09000", "10000", "12000", "16200", "16250", "16450", "16500", "16600"};
    public static String[] To = {"08899","09899", "11999", "14499", "16249", "16399", "16499", "16599", "16699"};
    //From Post Code color
    String Chartreuse = "#7fff00";
    String AntiqueWhite = "#faebd7";
    String Coral = "#f88379";
    String Cornsilk = "#fff8dc";

    //To Post Code Color
    String Chocolate = "#D2691E";
    String Aqua = "#00FFFF";
    String CornflowerBlue = "#6495ed";
    String Crimson = "#DC143C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_location_sorting );
        fromColorTv = findViewById( R.id.fromColor );
        toColorTV = findViewById( R.id.toColor );
        manifestNoTV=findViewById( R.id.manifestNo );
        checkInDateTV=findViewById( R.id.checkInDate );
        batchNoTV=findViewById( R.id.batchNo );
        totalShipmentsTV=findViewById( R.id.totalValue );
        String manifestNo=getIntent().getExtras().getString( "manifestno" );
        String check=getIntent().getExtras().getString( "checkinDate" );
        String batch=getIntent().getExtras().getString( "batchNo" );
        String totalValue=getIntent().getExtras().getString( "totalValue" );
        manifestNoTV.setText( manifestNo );
        checkInDateTV.setText( check );
        batchNoTV.setText( batch );
        totalShipmentsTV.setText( totalValue );

    }

    @Override
    protected void onStart() {
        super.onStart();
        getFromColor();
       getToColor();
    }

    public void getFromColor() {
        String from = getIntent().getExtras().getString( "From" );
        Arrays.sort( From );
        if (From[0].equals( from )) {
            fromColorTv.setBackgroundColor( Color.parseColor( Chartreuse ) );
        } else if (From[1].equals( from )) {
            fromColorTv.setBackgroundColor( Color.parseColor( AntiqueWhite ) );
        } else if (From[2].equals( from )) {
            fromColorTv.setBackgroundColor( Color.parseColor( Coral ) );

        } else if (From[3].equals( from )) {
            fromColorTv.setBackgroundColor( Color.parseColor( Cornsilk ) );

        } else if (From[4].equals( from )) {
            fromColorTv.setBackgroundColor( Color.RED );

        }


    }

    public void getToColor() {
        String to = getIntent().getExtras().getString( "To" );
        Arrays.sort( To );
        if (To[0].equals( to )) {
            toColorTV.setBackgroundColor( Color.parseColor( Chocolate ) );
        } else if (To[1].equals( to )) {
            toColorTV.setBackgroundColor( Color.parseColor( Aqua ) );
        } else if (To[2].equals( to )) {
            toColorTV.setBackgroundColor( Color.parseColor( CornflowerBlue ) );

        } else if (To[3].equals( to )) {
            toColorTV.setBackgroundColor( Color.parseColor( Crimson ) );

        } else if (To[4].equals( to )) {
            toColorTV.setBackgroundColor( Color.GREEN );

        }

    }


}









