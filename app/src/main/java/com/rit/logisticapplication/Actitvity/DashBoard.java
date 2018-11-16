package com.rit.logisticapplication.Actitvity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rit.logisticapplication.CameraCaptureActivity;
import com.rit.logisticapplication.R;
import com.rit.logisticapplication.details_model.Details;
import com.rit.logisticapplication.shipmentDetailsActivity.ShipmentsDetails_1;
import com.rit.logisticapplication.shipment_summery_models.ShipmentSummery;
import com.rit.logisticapplication.web_api.RetrofitClient;
import com.rit.logisticapplication.web_api.TrackNumber;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends AppCompatActivity {
    public static int taskChange = 1;
   /* public static String[] id = {
            "MXK0013663393", "MXK0013663191", "MXK0013663975", "MXK0013663976",
            "MXK0013663977", "MXK0013663980", "MXK0013664023", "MXK0013664027",
            "MXK0013664030", "MXK0013664031", "MXK0013664032", "MXK0013664034",
            "MXK0013664041", "MXK0013664042", "MXK0013664043", "MXK0013664044",
            "MXK0013664045", "MXK0013664046", "MXK0013664047", "MXK0013664049",
            "MXK0013664051", "MXK0013664052", "MXK0013664053", "MXK0013664054",
            "MXK0013664055", "MXK0013664056", "MXK0013664057", "MXK0013664058",
            "MXK0013664059"
    };*/
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String id="id";
    TextView userNameTV;
    TrackNumber trackNumber;
    private CardView locationCV, parcelCV, shipmentCV, helpCV;
    private Context context = DashBoard.this;

    String strDate;
    static  String value;
    static int idd;
    public static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dash_board );
        userNameTV = findViewById( R.id.userName );
        String name = getIntent().getExtras().getString( "name" );
        userNameTV.setText( name );
        sharedpreferences = getSharedPreferences(mypreference,
                                                 Context.MODE_PRIVATE);
        value=sharedpreferences.getString( Name,null);
        //idd=sharedpreferences.getInt( id,0 );
        intialization();
        setListener();
    }

    public void intialization() {
        locationCV = findViewById( R.id.locationCardViewId );
        parcelCV = findViewById( R.id.parcelCardViewId );
        shipmentCV = findViewById( R.id.shipmentCardViewId );
        helpCV = findViewById( R.id.helpId );

    }

    public void setListener() {
        locationCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskChange = 1;
                startQRScanner();
            }
        } );
        parcelCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskChange = 2;
                startQRScanner();
            }
        } );

        shipmentCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskChange = 3;
                startQRScanner();
            }
        } );
        helpCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( context, HelpActivity.class ) );
            }
        } );
    }


    public void getTrack(final String iid) {
        trackNumber = RetrofitClient.getRetrofit().create( TrackNumber.class );

        Call <Details> trackingSystemCall = trackNumber.getTrackingNumber( iid );
        trackingSystemCall.enqueue( new Callback <Details>() {
            @Override
            public void onResponse(Call <Details> call, Response <Details> response) {
                Details details = response.body();
                for (int i = 0; i < details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().size(); i++) {
                    strDate = details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().get( i ).getStrDate();
                }
                //String strDate= details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents() ;
               /* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(Date.parse(strDate));*/


                if (taskChange == 1) {

                    if (iid.equals(value )) {
                        sharedPreferences = context.getSharedPreferences( "details", MODE_PRIVATE );
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString( "manifestno", "123456789" );
                       /* editor.putString( "checkinDate", date );*/
                        editor.putString( "batchNo", "1235388" );
                        editor.apply();
                        editor.commit();

                        Intent intent = new Intent( context, LocationSorting.class );
                        intent.putExtra( "From", "08000" );
                        intent.putExtra( "To", "08899" );
                        intent.putExtra( "totalValue", "4838978" );
                        startActivity( intent );


                    } else if (iid.equals( value )) {

                        Intent intent = new Intent( context, LocationSorting.class );
                        intent.putExtra( "manifestno", "123789654" );
                        intent.putExtra( "checkinDate", strDate );
                        intent.putExtra( "batchNo", "11097681" );
                        intent.putExtra( "From", "09000" );
                        intent.putExtra( "To", "09899" );
                        intent.putExtra( "totalValue", "4938979" );
                        startActivity( intent );
                    }

                }
            }


            @Override
            public void onFailure(Call <Details> call, Throwable t) {
                AlertDialog alertDialog = new AlertDialog.Builder( DashBoard.this ).create();
                alertDialog.setTitle( "Alert" );
                alertDialog.setMessage( "Please Check Internet Connection" );
                alertDialog.setButton( AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
                alertDialog.show();
            }
        } );
        Call <ShipmentSummery> shipmentSummeryCall = trackNumber.getShipmentSummery( iid );
        shipmentSummeryCall.enqueue( new Callback <ShipmentSummery>() {
            @Override
            public void onResponse(Call <ShipmentSummery> call, Response <ShipmentSummery> response) {
                ShipmentSummery shipmentSummery = response.body();
                String origin = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getOriginStationDescription();

                String cCity=shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getCCity();

                String cPostcode=shipmentSummery.getTrackSummaryResponse().getSummaryList().get(0).getCPostCode();

                String cState=shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getCState();
                String shipmentDate=shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getShipmentDate();

                String signedName=  shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getSignedName();
                String ref=shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getXR1();


                if (taskChange == 2) {
                    //String remark=details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().get( 6 ).getRemarks().toString();
                    if (iid.equals( value )) {
                        SharedPreferences sharedPreferences = getSharedPreferences( "details", MODE_PRIVATE );
                        String manifest = sharedPreferences.getString( "manifestno", null );
                        String checkInDates = sharedPreferences.getString( "checkinDate", null );
                        String batch = sharedPreferences.getString( "batchNo", null );
                        Intent intent = new Intent( context, Parcel_Tracking.class );
                        intent.putExtra( "number", manifest );
                        intent.putExtra( "check", checkInDates );
                        intent.putExtra( "batch", batch );
                        intent.putExtra( "totalShipments", "4838978" );
                        intent.putExtra( "shipperName", "Mr. Xi Fui" );
                        intent.putExtra( "shipperAddress", "Park Street Road" );
                        // intent.putExtra( "deliveryDate", remark );
                        startActivity( intent );

                    } else if (iid.equals( "MXK0013663191" )) {
                        Intent intent = new Intent( context, Parcel_Tracking.class );
                        intent.putExtra( "totalShipments", "4938979" );
                        intent.putExtra( "shipperName", "Mr. Yi Fui" );
                        intent.putExtra( "shipperAddress", "Bangsui Road" );
                        //intent.putExtra( "deliveryDate", remark );
                        startActivity( intent );

                    }

                } else if (taskChange == 3) {

                        Intent intent = new Intent( context, ShipmentsDetails_1.class );
                        intent.putExtra( "origin", origin );
                        intent.putExtra( "cCity", cCity );
                        intent.putExtra( "cPostCode", cPostcode );
                        intent.putExtra( "cState", cState );
                        intent.putExtra( "signed",signedName );
                        intent.putExtra( "ref",shipmentDate );

                        // intent.putExtra( "date", shipmentDate);
                        startActivity( intent );



                }
            }

            @Override
            public void onFailure(Call <ShipmentSummery> call, Throwable t) {

            }
        } );
    }


    public void startQRScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator( this );
        intentIntegrator.setDesiredBarcodeFormats( IntentIntegrator.ONE_D_CODE_TYPES );
        intentIntegrator.setCaptureActivity( CameraCaptureActivity.class );
        intentIntegrator.setOrientationLocked( false );
        intentIntegrator.setPrompt( "scan" );
        intentIntegrator.initiateScan();

    }

   /* private String getId(String id1) {
        String ID = "";
        for (int i = 0; i < id.length; i++) {
            if (id[i].equals( id1 ))
                return id1;
        }
        return ID;

    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        IntentResult result = IntentIntegrator.parseActivityResult( requestCode, resultCode, data );
        if (result != null) {

            if (result.getContents() == null) {
                Intent intent = new Intent( context, DashBoard.class );
            } /*else if (!getId( result.getContents() ).equals( "" ))*/
            else if(result.getContents().equals( value)){
                getTrack( result.getContents() );
                //getTrack( String.valueOf( idd ));
            //    Toast.makeText( context,"succecfully value pass",Toast.LENGTH_SHORT ).show();
            } else {
                //Toast.makeText( context, "Barcode does not Match!", Toast.LENGTH_SHORT ).show();
                AlertDialog alertDialog = new AlertDialog.Builder( DashBoard.this ).create();
                alertDialog.setTitle( "Alert" );
                alertDialog.setMessage( "Barcode does not match" );
                alertDialog.setButton( AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } );
                alertDialog.show();
            }
        }
    }
}






