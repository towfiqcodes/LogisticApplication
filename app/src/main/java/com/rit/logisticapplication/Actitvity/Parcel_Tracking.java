package com.rit.logisticapplication.Actitvity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rit.logisticapplication.R;


public class Parcel_Tracking extends AppCompatActivity {
    TextView manifestNo,checkInDate,batchNo,totalShipments,shipperInfo,shipperAddress,delivaryDate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.parcel_information );
        manifestNo=findViewById( R.id.manifestId );
        checkInDate=findViewById( R.id.checkId );
        batchNo=findViewById( R.id.batchId );
        totalShipments=findViewById( R.id.totalShipmentsId );
        shipperInfo=findViewById( R.id.shipperInfoId );
        shipperAddress=findViewById( R.id.shipperAddressId );
        delivaryDate=findViewById( R.id.delivaryId );

        manifestNo.setText(getIntent().getExtras().getString( "manifestNo" ) );
        checkInDate.setText( getIntent().getExtras().getString( "checkInDate" ) );
        batchNo.setText( getIntent().getExtras().getString( "batchNo" ) );
        totalShipments.setText( getIntent().getExtras().getString( "totalShipments" ) );
        shipperInfo.setText( getIntent().getExtras().getString( "shipperName" ) );
        shipperAddress.setText( getIntent().getExtras().getString( "shipperAddress" ) );
        delivaryDate.setText( getIntent().getExtras().getString( "deliveryDate" ) );


    }


}



