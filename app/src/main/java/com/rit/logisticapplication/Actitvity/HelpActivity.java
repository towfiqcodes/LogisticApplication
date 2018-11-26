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


import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
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
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.rit.logisticapplication.Actitvity.DashBoard.taskChange;

public class HelpActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private TextView menuClick, userName;
    LinearLayout linearLayout;

    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String id = "id";
    TrackNumber trackNumber;
    private Context context = HelpActivity.this;


    static String value;
    static String reason;
    static String stationCode;
    public static SharedPreferences sharedPreferences1;
    static int data;
    String remarks;
    String transactionDate;
    String eventDescription;
    String strDate1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_help );
        linearLayout = findViewById( R.id.menuLinearLayout );
        menuClick = findViewById( R.id.menu_click );
        userName = findViewById( R.id.userName );
        sharedPreferences = getSharedPreferences( "name", MODE_PRIVATE );
        String name = sharedPreferences.getString( "name", null );
        userName.setText( name );


        sharedPreferences = getSharedPreferences( mypreference, Context.MODE_PRIVATE );
        value = sharedPreferences.getString( "value", null );

        menuClick.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu( HelpActivity.this, view );
                popupMenu.setOnMenuItemClickListener( HelpActivity.this );
                popupMenu.inflate( R.menu.menu );
                popupMenu.show();
            }
        } );

    }
//tracking Api
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

                    sharedPreferences1 = context.getSharedPreferences( "details", MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putString( "manifestno", strDate );
                    editor.putString( "checkinDate", thisDate );
                    editor.putString( "reason", reason );
                    editor.putString( "stationCode",stationCode );
                    editor.putString( "eventDes",eventDescription );
                    editor.putString( "remarks",remarks );
                    editor.apply();
                    editor.commit();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder( HelpActivity.this ).create();
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

            if (result.getContents() == null) {
                Intent intent = new Intent( context, HelpActivity.class );
                startActivity( intent );
            } else if (result.getContents()!=null ||result.getContents().equals( value )) {
                getTrack( result.getContents());
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder( context ).create();
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
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item_dashBoard:
                startActivity( new Intent( context, DashBoard.class ) );
                return true;
            case R.id.item_location:
                taskChange = 1;
                startQRScanner();

                return true;
            case R.id.item_parcel:
                taskChange = 2;
                startQRScanner();

                return true;
            case R.id.item_shipments:
                taskChange = 3;
                startQRScanner();
                return true;
            case R.id.item_help:
                startActivity( new Intent( context, HelpActivity.class ) );
                return true;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
