package com.rit.logisticapplication.shipmentDetailsActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rit.logisticapplication.R;

import static com.rit.logisticapplication.Actitvity.LocationSorting.sharedPreferences;

public class ShipmentsDetails_1 extends AppCompatActivity {
   TextView userName,
           originStationDescriptionTV,cCityIdTV,
           cPostCodeIdTV,cStateId,
           shipmentDateTV, signedName,
           packageTV,desTV,reasonTV;

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
        packageTV=findViewById( R.id.packageId );
        desTV=findViewById( R.id.destinationDes );
        reasonTV=findViewById( R.id.reasonCode );

         userName=findViewById( R.id.userName );
        sharedPreferences =getSharedPreferences( "name", MODE_PRIVATE );
        String name = sharedPreferences.getString( "name",null );
        userName.setText( name );

        originStationDescriptionTV.setText( getIntent().getExtras().getString( "origin" ) );
        cCityIdTV.setText( getIntent().getExtras().getString( "cCity" ) );
        cPostCodeIdTV.setText( getIntent().getExtras().getString( "cPostCode" ) );
        cStateId.setText( getIntent().getExtras().getString( "cState" ) );
        shipmentDateTV.setText( getIntent().getExtras().getString( "date" ) );
        signedName.setText(  getIntent().getExtras().getString( "signed" ) );
        packageTV.setText( getIntent().getExtras().getString( "event" ) );
        desTV.setText( getIntent().getExtras().getString( "destination" ) );
        reasonTV.setText(getIntent().getExtras().getString( "reasonCode" )  );
    }
}
