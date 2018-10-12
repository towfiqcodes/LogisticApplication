package com.rit.logisticapplication.Actitvity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.rit.logisticapplication.CameraCaptureActivity;
import com.rit.logisticapplication.MainActivity;
import com.rit.logisticapplication.R;
import com.rit.logisticapplication.details_model.Details;
import com.rit.logisticapplication.shipmentDetailsActivity.ShipmentsDetails_1;
import com.rit.logisticapplication.shipmentDetailsActivity.ShipmentsDetails_2;
import com.rit.logisticapplication.web_api.RetrofitClient;
import com.rit.logisticapplication.web_api.TrackNumber;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends AppCompatActivity {
    public static int taskChange = 1;
    public static String[] id = {
            "MXK0013663393", "MXK0013663191", "MXK0013663975", "MXK0013663976",
            "MXK0013663977", "MXK0013663980", "MXK0013664023", "MXK0013664027",
            "MXK0013664030", "MXK0013664031", "MXK0013664032", "MXK0013664034",
            "MXK0013664041", "MXK0013664042", "MXK0013664043", "MXK0013664044",
            "MXK0013664045", "MXK0013664046", "MXK0013664047", "MXK0013664049",
            "MXK0013664051", "MXK0013664052", "MXK0013664053", "MXK0013664054",
            "MXK0013664055", "MXK0013664056", "MXK0013664057", "MXK0013664058",
            "MXK0013664059"
    };

    TextView userNameTV;
    TrackNumber trackNumber;
    private CardView locationCV, parcelCV, shipmentCV, helpCV;
    private Context context = DashBoard.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dash_board );
        userNameTV = findViewById( R.id.userName );
        String name = getIntent().getExtras().getString( "name" );
        userNameTV.setText( name );
        intialization();
        setListener();
    }

    public void intialization() {
        locationCV = findViewById( R.id.locationCardViewId );
        parcelCV = findViewById( R.id.parcelCardViewId );
        shipmentCV = findViewById( R.id.shipmentCardViewId );
        helpCV = findViewById( R.id.helpId );

    }
    public void setListener(){
        locationCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskChange=1;
                startQRScanner();
            }
        } );
        parcelCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskChange=2;
                startQRScanner();
            }
        } );

        shipmentCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskChange=3;
                startQRScanner();
            }
        } );
        helpCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( context,HelpActivity.class ) );
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
                if(taskChange==1){
                    if(iid.equals( "MXK0013663393" )){

                            Intent intent = new Intent( context, LocationSorting.class );
                            intent.putExtra( "manifestno","123456789" );
                            intent.putExtra( "checkinDate","10/11/2018" );
                            intent.putExtra( "batchNo","1235388" );
                            intent.putExtra( "From","08000"  );
                            intent.putExtra( "To","08899");
                            intent.putExtra( "totalValue","4838978" );
                            startActivity( intent );


                    }else if(iid.equals( "MXK0013663191" )){

                        Intent intent = new Intent( context, LocationSorting.class );
                        intent.putExtra( "manifestno","123789654" );
                        intent.putExtra( "checkinDate","13/11/2018" );
                        intent.putExtra( "batchNo","11097681" );
                        intent.putExtra( "From","09000"  );
                        intent.putExtra( "To","09899");
                        intent.putExtra( "totalValue","4938979" );
                        startActivity( intent );
                    }




                }else if(taskChange==2){
                    if(iid.equals(  "MXK0013663393" ) ) {
                        Intent intent = new Intent( context, Parcel_Tracking.class );
                        intent.putExtra( "manifestNo", "123456789" );
                        intent.putExtra( "checkInDate", "10/11/2018" );
                        intent.putExtra( "batchNo", "1235388" );
                        intent.putExtra( "totalShipments", "4838978" );
                        intent.putExtra( "shipperName", "Mr. Xi Fui" );
                        intent.putExtra( "shipperAddress", "Park Street Road" );
                        intent.putExtra( "deliveryDate", "11/11/2018" );
                        startActivity( intent );

                    }else if(iid.equals(  "MXK0013663191" ) ){

                        Intent intent = new Intent( context, Parcel_Tracking.class );
                        intent.putExtra( "manifestNo", "123789654" );
                        intent.putExtra( "checkInDate", "13/11/2018" );
                        intent.putExtra( "batchNo", "11097681" );
                        intent.putExtra( "totalShipments", "4938979" );
                        intent.putExtra( "shipperName", "Mr. Yi Fui" );
                        intent.putExtra( "shipperAddress", "Bangsui Road" );
                        intent.putExtra( "deliveryDate", "14/11/2018" );
                        startActivity( intent );

                    }

                }else if(taskChange==3){
                    if(iid.equals( "MXK0013663393" )){
                        startActivity( new Intent( context,ShipmentsDetails_1.class ) );

                    }else if(iid.equals( "MXK0013663191" )){
                        startActivity( new Intent( context,ShipmentsDetails_2.class ) );

                    }else if(iid.equals( "MXK0013663975" )){
                        startActivity( new Intent( context,ShipmentsDetails_2.class ) );

                    }else if(iid.equals( "MXK0013663976" )){
                        startActivity( new Intent( context,ShipmentsDetails_2.class ) );

                    }else if(iid.equals( "MXK0013663977" )){
                        startActivity( new Intent( context,ShipmentsDetails_2.class ) );

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
    }


    public void startQRScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator( this);
        intentIntegrator.setDesiredBarcodeFormats( IntentIntegrator.ONE_D_CODE_TYPES );
        intentIntegrator.setCaptureActivity( CameraCaptureActivity.class );
        intentIntegrator.setOrientationLocked( false );
        intentIntegrator.setPrompt( "scan" );
        intentIntegrator.initiateScan();

    }

    private String getId(String id1) {
        String ID = "";
        for (int i = 0; i < id.length; i++) {
            if (id[i].equals( id1 ))
                return id1;
        }
        return ID;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult( requestCode, resultCode, data );
        if (result != null) {

            if (result.getContents() == null) {
                Intent intent = new Intent( context, DashBoard.class );
            } else if (!getId( result.getContents() ).equals( "" )) {
                getTrack( result.getContents() );
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






