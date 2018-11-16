package com.rit.logisticapplication.shipmentDetailsActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rit.logisticapplication.R;

public class ShipmentsDetails_1 extends AppCompatActivity {
   TextView originStationDescriptionTV,cCityIdTV,cPostCodeIdTV,cStateId,shipmentDateTV,signedName,refTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shipments_details_1 );

        originStationDescriptionTV=findViewById( R.id.originStationDescription );
        cCityIdTV=findViewById( R.id.cCityId );
        signedName=findViewById( R.id.signedName );
        cPostCodeIdTV=findViewById( R.id.cPostCodeId );
        cStateId=findViewById( R.id.cStateId );
        shipmentDateTV=findViewById( R.id.shipmentDate );

        refTV=findViewById( R.id.refId );


        originStationDescriptionTV.setText( getIntent().getExtras().getString( "origin" ) );
        cCityIdTV.setText( getIntent().getExtras().getString( "cCity" ) );
        cPostCodeIdTV.setText( getIntent().getExtras().getString( "cPostCode" ) );
        cStateId.setText( getIntent().getExtras().getString( "cState" ) );
        shipmentDateTV.setText( getIntent().getExtras().getString( "date" ) );
        signedName.setText(  getIntent().getExtras().getString( "signed" ) );
         refTV.setText(  getIntent().getExtras().getString( "ref" ) );

    }
}
