package com.rit.logisticapplication.Actitvity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rit.logisticapplication.R;

import static com.rit.logisticapplication.Actitvity.LocationSorting.sharedPreferences;


public class Parcel_Tracking extends AppCompatActivity {
    TextView userName, manifestNo,checkInDate,batchNo,totalShipments,shipperInfo,shipperAddress,delivaryDate,reasonTV;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.parcel_information );
        checkInDate=findViewById( R.id.checkId );


        shipperInfo=findViewById( R.id.shipperInfoId );
        shipperAddress=findViewById( R.id.shipperAddressId );
        delivaryDate=findViewById( R.id.delivaryId );
         reasonTV=findViewById( R.id.reasonId );
         userName=findViewById( R.id.userName );
        sharedPreferences =getSharedPreferences( "name", MODE_PRIVATE );
        String name = sharedPreferences.getString( "name",null );
        userName.setText( name );
        checkInDate.setText(getIntent().getExtras().getString( "check" ) );
        shipperInfo.setText( getIntent().getExtras().getString( "shipperName" ) );
        shipperAddress.setText( getIntent().getExtras().getString( "shipperAddress" ) );
        delivaryDate.setText( getIntent().getExtras().getString( "startDate" ) );
        reasonTV.setText( getIntent().getExtras().getString( "reasonDes" ) );


    }


}



