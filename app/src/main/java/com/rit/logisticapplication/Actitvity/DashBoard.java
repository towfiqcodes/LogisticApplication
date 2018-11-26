package com.rit.logisticapplication.Actitvity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends AppCompatActivity {
    public static int taskChange = 1;
    private SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String id = "id";
    TextView userNameTV;
    EditText trackingId;
    Button trackingBtn;

    TrackNumber trackNumber;
    private CardView locationCV, parcelCV, shipmentCV, helpCV;
    private Context context = DashBoard.this;

    String value;
    String reason;
    String stationCode;
    String strDate1;
    static SharedPreferences sharedPreferences1;
    int data;
    String remarks;
    String transactionDate;
    String eventDescription;
    String ref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dash_board );
        userNameTV = findViewById( R.id.userName );
        trackingId = findViewById( R.id.trackingId );
        trackingBtn = findViewById( R.id.trackingBTN );

        //get data from SignInActivity
        sharedPreferences1 = getSharedPreferences( "name", Context.MODE_PRIVATE );
        String name = sharedPreferences1.getString( "name", null );
        userNameTV.setText( name );

        trackingBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from CameraCapture Activity
                value = trackingId.getText().toString();
                sharedpreferences = context.getSharedPreferences( mypreference, Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString( "value", value );
                editor.apply();
                editor.commit();

            }
        } );
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

    // tracking Api
    public void getTrack(final String iid) {
        trackNumber = RetrofitClient.getRetrofit().create( TrackNumber.class );

        final Call <Details> trackingSystemCall = trackNumber.getTrackingNumber( iid );
        trackingSystemCall.enqueue( new Callback <Details>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call <Details> call, Response <Details> response) {
                Details details = response.body(); //modelclass details

                if (response.isSuccessful() && response.body().getTrackDetailsResponse().getEventList().size() > 0) {
                    data = details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().size();
                        for (int i = 0; i < data; i++) {
                                reason = String.valueOf( details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().get( i ).getReasonDescription() );
                                stationCode = String.valueOf( details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().get( i ).getStationCode() );
                                transactionDate = String.valueOf( details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().get( i ).getTransactionDate() );
                                remarks = String.valueOf( details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().get( i ).getRemarks() );
                                eventDescription=String.valueOf( details.getTrackDetailsResponse().getEventList().get( 0 ).getEvents().get( i ).getEventDescription() );
                            }
                            //tranactiondate
                            String myString = transactionDate.substring( 6, transactionDate.length() - 7 );
                            Timestamp ts = new Timestamp( Long.valueOf( myString ) );
                            Date date = ts;
                            SimpleDateFormat formatter = new SimpleDateFormat( "dd-MM-yyyy hh:mm" );
                            String strDate = formatter.format( date );

                            //check in date
                            SimpleDateFormat currentDate = new SimpleDateFormat( "dd/MM/yyyy" );
                            Date todayDate = new Date();
                            String thisDate = currentDate.format( todayDate );

                            sharedpreferences = context.getSharedPreferences( "details", MODE_PRIVATE );
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString( "manifestno", strDate );
                            editor.putString( "checkinDate", thisDate );
                            editor.putString( "reason", reason );
                            editor.putString( "stationCode",stationCode );
                            editor.putString( "eventDes",eventDescription );
                            editor.putString( "remarks",remarks );
                            editor.apply();
                            editor.commit();






                } else


                {
                    AlertDialog alertDialog = new AlertDialog.Builder( DashBoard.this ).create();
                    alertDialog.setTitle( "Error" );
                    alertDialog.setMessage( "Error Code: " + details.getTrackDetailsResponse().getErrorCode() +
                                                    "\n" + "Error Message: " + details.getTrackDetailsResponse().getErrorMessage() );
                    alertDialog.setButton( AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    } );
                    alertDialog.show();
                }
            }


            @Override
            public void onFailure(Call <Details> call, Throwable t) {

            }
        } );
        Call <ShipmentSummery> shipmentSummeryCall = trackNumber.getShipmentSummery( iid );
        shipmentSummeryCall.enqueue( new Callback <ShipmentSummery>() {
            @Override
            public void onResponse(Call <ShipmentSummery> call, Response <ShipmentSummery> response) {
                ShipmentSummery shipmentSummery = response.body();

                //check response is successfully done and get data from api
                if (response.isSuccessful() && response.body().getTrackSummaryResponse().getSummaryList().size() > 0) {

                    String origin = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getOriginStationDescription();
                    String cCity = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getCCity();
                    String cPostcode = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getCPostCode();
                    String cState = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getCState();
                    String shipmentDate = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getShipmentDate();
                    String eventCode = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getEventCode();
                    String signedName = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getSignedName();
                   // ref2 = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getXR1();
                    String deliveryDate = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getDeliveryDate();
                    String xr2Agent = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getXR2AgentCode();
                    String hawbNo = shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getHawbNo();
                    String destination=shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getDestinationStationDescription();
                    String reasonCode=shipmentSummery.getTrackSummaryResponse().getSummaryList().get( 0 ).getReasonCode();
                    //shipment date and time formate
                    String myString = shipmentDate.substring( 6, shipmentDate.length() - 7 );
                    Timestamp ts = new Timestamp( Long.valueOf( myString ) );
                    Date date = ts;
                    SimpleDateFormat formatter = new SimpleDateFormat( "dd-MM-yyyy hh:mm:ss a" );
                    String strDate = formatter.format( date );

                    //delivery date and time formate
                    if (deliveryDate == null) {
                    } else {
                        String string = deliveryDate.substring( 6, deliveryDate.length() - 7 );

                        Long delDate = Long.valueOf( string );
                        Timestamp time = new Timestamp( delDate );
                        Date date1 = time;
                        SimpleDateFormat formatter1 = new SimpleDateFormat( "dd/MM/yyyy" );
                        strDate1 = formatter1.format( date1 );
                    }
                    SharedPreferences sharedPreferences = getSharedPreferences( "details", MODE_PRIVATE );
                    String manifest = sharedPreferences.getString( "manifestno", null );
                    String checkInDates = sharedPreferences.getString( "checkinDate", null );
                    String reasonDes = sharedPreferences.getString( "reason", null );
                    String eventDescription=sharedPreferences.getString( "eventDes" ,null);
                    String stationCode=sharedPreferences.getString( "stationCode",null);
                    String remarks=sharedPreferences.getString( "remarks",null );

                    if (taskChange == 1) {
                        if (!iid.equals( null )) {
                            Intent intent = new Intent( context, LocationSorting.class );
                            intent.putExtra( "From", stationCode );
                            intent.putExtra( "remarks", remarks );
                            intent.putExtra( "eventDes",eventDescription );
                            //intent.putExtra( "reference",ref2 );
                            intent.putExtra( "agentNo",xr2Agent );
                            intent.putExtra( "hwabNo",hawbNo );
                            startActivity( intent );

                        }

                        }
                    if (taskChange == 2) {
                        if (!iid.equals( null )) {
                            Intent intent = new Intent( context, Parcel_Tracking.class );
                            intent.putExtra( "manifestno", manifest );
                            intent.putExtra( "check", checkInDates );
                            intent.putExtra( "shipperName", signedName );
                            intent.putExtra( "shipperAddress", cCity );
                            intent.putExtra( "reasonDes", reasonDes );
                            intent.putExtra( "startDate", strDate1 );
                            startActivity( intent );

                        }

                    } else if (taskChange == 3) {

                        Intent intent = new Intent( context, ShipmentsDetails_1.class );
                        intent.putExtra( "origin", origin );
                        intent.putExtra( "cCity", cCity );
                        intent.putExtra( "cPostCode", cPostcode );
                        intent.putExtra( "cState", cState );
                        intent.putExtra( "signed", signedName );
                        intent.putExtra( "date", strDate );
                        intent.putExtra( "event", eventCode );
                       intent.putExtra( "destination",destination );
                       intent.putExtra( "reasonCode",reasonCode );
                        startActivity( intent );


                    }
                }
            }

            @Override
            public void onFailure(Call <ShipmentSummery> call, Throwable t) {
                Toast.makeText( context, t.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }


    public void startQRScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator( this );
        intentIntegrator.setDesiredBarcodeFormats( IntentIntegrator.ONE_D_CODE_TYPES );
        intentIntegrator.setCaptureActivity( CameraCaptureActivity.class );
        intentIntegrator.setOrientationLocked( false );
        intentIntegrator.setBeepEnabled( true );
        intentIntegrator.setPrompt( "scan" );
        intentIntegrator.initiateScan();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        IntentResult result = IntentIntegrator.parseActivityResult( requestCode, resultCode, data );
        if (result != null) {

            sharedpreferences = getSharedPreferences( mypreference, MODE_PRIVATE );
            String value1 = sharedpreferences.getString( "value", null );

            if (result.getContents() == null) {
                Intent intent = new Intent( context, DashBoard.class );
                startActivity( intent );
            } else if (result.getContents() != null || result.getContents().equals( value1 )) {
                getTrack( result.getContents() );

            } else {
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

    @Override
    public void onBackPressed() {
        Log.d( "CDA", "onBackPressed Called" );
        Intent setIntent = new Intent( Intent.ACTION_MAIN );
        setIntent.addCategory( Intent.CATEGORY_HOME );
        setIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity( setIntent );
    }

}






