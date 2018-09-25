package com.rit.logisticapplication.Actitvity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rit.logisticapplication.R;
import com.rit.logisticapplication.details_model.Details;
import com.rit.logisticapplication.web_api.RetrofitClient;
import com.rit.logisticapplication.web_api.TrackNumber;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends AppCompatActivity {
    TextView userNameTV;
    private CardView locationCV, parcelCV;
    private Context context = DashBoard.this;
    Button button;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    List <Address> addresses;
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
    String detail;
    TrackNumber trackNumber;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dash_board );
        userNameTV = findViewById( R.id.userName );
        String name = getIntent().getExtras().getString( "name" );
        userNameTV.setText( name );
        locationCV = findViewById( R.id.locationCardViewId );
        parcelCV = findViewById( R.id.parcelCardViewId );
        parcelCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startQRScanner();
            }
        } );


        locationCV.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startQRScanner();
            }
        } );
        locationManager = (LocationManager) getSystemService( LOCATION_SERVICE );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED)
            if (ActivityCompat.checkSelfPermission( DashBoard.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION )
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions( this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION );
                return;
            }

        if (locationManager.isProviderEnabled( LocationManager.NETWORK_PROVIDER )) {
            locationManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latLng = location.getLatitude();
                    double longitude = location.getLongitude();
                    getgetLocationAddress( getApplicationContext(), latLng, longitude );
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    //Bundle bundle=getIntent().getBundleExtra( "hello" );

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            } );

        }
        
    }

    public void getTrack(String iid) {
        trackNumber = RetrofitClient.getRetrofit().create( TrackNumber.class );

        Call <Details> trackingSystemCall = trackNumber.getTrackingNumber( iid );
        trackingSystemCall.enqueue( new Callback <Details>() {
            @Override
            public void onResponse(Call <Details> call, Response <Details> response) {
                Details details = response.body();
                // detail = details.getTrackDetailsResponse().getEventList().get( 0 ).getHawbNo();
                getAddress();
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


    private void startQRScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator( this );
        intentIntegrator.setPrompt( "Scan" );
        //intentIntegrator.setCaptureLayout( R.layout.custom_scanner );
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
                getTrack( getId( result.getContents() ) );
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


    public void getgetLocationAddress(Context context, double lat, double lng) {
        Geocoder geocoder;
        geocoder = new Geocoder( context, Locale.getDefault() );
        try {
            addresses = geocoder.getFromLocation( lat, lng, 1 ); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAddress() {
        String result = null;
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get( 0 );
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                sb.append( address.getAddressLine( i ) );
            }
            sb.append( address.getLocality() );
            result = sb.toString();
            Intent intent = new Intent( context, LocationSorting.class );
            intent.putExtra( "address", result );
            startActivity( intent );
        }
    }

}

