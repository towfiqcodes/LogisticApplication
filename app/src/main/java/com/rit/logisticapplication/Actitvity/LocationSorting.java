package com.rit.logisticapplication.Actitvity;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.rit.logisticapplication.R;

public class LocationSorting extends AppCompatActivity {
    TextView colorTv,usernNameTV;
    String name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_location_sorting );
        colorTv = findViewById( R.id.colorTV );
        usernNameTV=findViewById( R.id.userNameTV );
        String  username=getIntent().getExtras().getString( "name" );
        usernNameTV.setText( name );

        name = getIntent().getExtras().getString( "address" );

        //getColor();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (name.equalsIgnoreCase(  "Dhaka")){
            colorTv.setBackgroundColor( Color.RED );
        }else if(name.equalsIgnoreCase(  "Chittagong")){
            colorTv.setBackgroundColor( Color.BLUE );
        }else if(name.equalsIgnoreCase(  "Rajshahi")){
            colorTv.setBackgroundColor( Color.BLACK );
        }else if(name.equalsIgnoreCase(  "Khulna")){
            colorTv.setBackgroundColor( Color.MAGENTA );
        }
    }


}









