package com.rit.logisticapplication.Actitvity;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.rit.logisticapplication.R;

import java.util.Arrays;

public class LocationSorting extends AppCompatActivity {
    TextView userName, fromColorTv, toColorTV,manifestNoTV,checkInDateTV,routingCode,fromId,toId,eventDesTV,
            xr2AgentCode,xr1,remarksId,hawbNo;

    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_location_sorting );
        fromColorTv = findViewById( R.id.fromColor );
        toColorTV = findViewById( R.id.toColor );
        manifestNoTV=findViewById( R.id.manifestNo );
        checkInDateTV=findViewById( R.id.checkInDate );
        routingCode=findViewById( R.id.routingCodeId );
        fromId=findViewById( R.id.fromCodeId );
        toId=findViewById( R.id.toCodeId );
        eventDesTV=findViewById( R.id.eventDes );
        xr2AgentCode=findViewById( R.id.xr2AgentId );
        hawbNo=findViewById( R.id.hawbId );
        remarksId=findViewById( R.id.remarksId );

        userName=findViewById( R.id.userName );
         sharedPreferences =getSharedPreferences( "name", MODE_PRIVATE );
         String name = sharedPreferences.getString( "name",null );
         userName.setText( name );


         sharedPreferences =getSharedPreferences( "details", MODE_PRIVATE );
        String manifest=sharedPreferences.getString( "manifestno" ,null);
        String checkInDates=sharedPreferences.getString( "checkinDate",null );

        manifestNoTV.setText( manifest );
        checkInDateTV.setText( checkInDates );
        remarksId.setText( getIntent().getExtras().getString( "remarks" ) );
        eventDesTV.setText( getIntent().getExtras().getString( "eventDes" ) );
      hawbNo.setText( getIntent().getExtras().getString( "hwabNo") );
        xr2AgentCode.setText(  getIntent().getExtras().getString( "agentNo"));

    }

    @Override
    protected void onStart() {
        super.onStart();
        getFromColor();

    }

    public void getFromColor() {
        String from = getIntent().getExtras().getString( "From" );
        if (from.equals( "JHBB" )) {
            fromColorTv.setBackgroundColor( Color.parseColor(  "#deb887"));
            toColorTV.setBackgroundColor( Color.parseColor( "#F5F5DC"));
            routingCode.setText( from );
            fromId.setText( "79000" );
            toId.setText( "79699" );

        }
        else if (from.equals("SGPB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#7fff00" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#D2691E" ) );
            routingCode.setText( from );
            fromId.setText( "08000" );
            toId.setText( "08899" );
        }else if (from.equals("KLMB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#faebd7" ) );
            toColorTV.setBackgroundColor( Color.parseColor(  "#00FFFF"));
            routingCode.setText(from  );
            fromId.setText( "09000" );
            toId.setText( "09899" );
        } else if (from.equals( "PENB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#f88379" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#6495ed"));
            routingCode.setText( from );
            fromId.setText( "10000" );
            toId.setText( "11999" );

        } else if (from.equals( "BTWB" )) {
            fromColorTv.setBackgroundColor( Color.parseColor("#fff8dc" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#DC143C"));
            routingCode.setText( from );
            fromId.setText( "12000" );
            toId.setText( "14499" );

        } else if (from.equals( "KBRB")) {
            fromColorTv.setBackgroundColor( Color.RED );
            toColorTV.setBackgroundColor( Color.parseColor( "#F5F5DC"));
            routingCode.setText( from );
            fromId.setText( "16200" );
            toId.setText( "16249" );

        } else if (from.equals("MCHB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#F4A460" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#fffacd"));
            routingCode.setText(from  );
            fromId.setText( "16450" );
            toId.setText( "16499" );
        } else if (from.equals( "TGGB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#87CEEB" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#7FFFD4"));
            routingCode.setText( from );
            fromId.setText( "20000" );
            toId.setText( "21899" );

        } else if (from.equals( "DGNB" )) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#708090" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#FAFAD2"));
            routingCode.setText( from );
            fromId.setText( "23000" );
            toId.setText( "23099" );

        } else if (from.equals( "KTEB")) {
            fromColorTv.setBackgroundColor( Color.parseColor("#D2B48C" ));
            toColorTV.setBackgroundColor( Color.parseColor( "#FFFF33"));
            routingCode.setText( from );
            fromId.setText( "23100" );
            toId.setText( "23199" );

        } else if (from.equals("KMNB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#FA8072" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#f0e68c"));
            routingCode.setText(from  );
            fromId.setText( "24050" );
            toId.setText( "24109" );
        } else if (from.equals( "KUAB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#00ff7f" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#afeeee"));
            routingCode.setText( from );
            fromId.setText( "26600" );
            toId.setText( "26689" );

        } else if (from.equals( "TMLB" )) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#d8bfd8" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#FF00FF"));
            routingCode.setText( from );
            fromId.setText( "27150" );
            toId.setText( "27199" );

        } else if (from.equals( "IPHB")) {
            fromColorTv.setBackgroundColor(Color.parseColor( "#1e90ff" ));
            toColorTV.setBackgroundColor( Color.parseColor( "#D2691E"));
            routingCode.setText( from );
            fromId.setText( "30000" );
            toId.setText( "30999" );

        } else if (from.equals("SHM")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#EE82EE" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#800000"));
            routingCode.setText(from  );
            fromId.setText( "40000" );
            toId.setText( "40999" );
        } else if (from.equals( "PKGB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#f5deb3" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#0000cd"));
            routingCode.setText( from );
            fromId.setText( "41050" );
            toId.setText( "41099" );

        } else if (from.equals( "KJGB" )) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#DA70D6" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#E6E6FA"));
            routingCode.setText( from );
            fromId.setText( "43000" );
            toId.setText( "43499" );

        } else if (from.equals( "PJY")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#C0C0C0" ));
            toColorTV.setBackgroundColor( Color.parseColor( "#ffa07a"));
            routingCode.setText( from );
            fromId.setText( "46000" );
            toId.setText( "46999" );

        } else if (from.equals("BCVB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#ffdab9" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#228B22"));
            routingCode.setText(from  );
            fromId.setText( "47000" );
            toId.setText( "47099" );
        } else if (from.equals( "PCH")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#ff6347" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#9370d8"));
            routingCode.setText( from );
            fromId.setText( "47100" );
            toId.setText( "47199" );

        } else if (from.equals( "SBG")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#008080" ));
            toColorTV.setBackgroundColor( Color.parseColor( "#3cb371"));
            routingCode.setText( from );
            fromId.setText( "47500" );
            toId.setText( "47599" );

        } else if (from.equals( "USJ")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#DDA0DD" ) );
            toColorTV.setBackgroundColor( Color.parseColor( " #ffe4b5"));
            routingCode.setText( from );
            fromId.setText( "47600" );
            toId.setText( "47699" );

        } else if (from.equals("KULB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#cd853f" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#FF0080"));
            routingCode.setText(from  );
            fromId.setText( "50000" );
            toId.setText( "50999" );
        } else if (from.equals( "CRSB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#afeeee" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#696969"));
            routingCode.setText( from );
            fromId.setText( "55000" );
            toId.setText( "57999" );

        } else if (from.equals( "SBNB" )) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#9ACD32" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#f5f5dc"));
            routingCode.setText( from );
            fromId.setText( "70000" );
            toId.setText( "70999" );

        } else if (from.equals( "MKZB")) {
            fromColorTv.setBackgroundColor( Color.YELLOW );
            toColorTV.setBackgroundColor( Color.parseColor( "#FFE4C4"));
            routingCode.setText( from );
            fromId.setText( "75000" );
            toId.setText( "75999" );

        } else if (from.equals("BPHB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#7fff00" ) );
            toColorTV.setBackgroundColor(  Color.BLACK);
            routingCode.setText(from  );
            fromId.setText( "83000" );
            toId.setText( "83099" );
        }  else if (from.equals( "JTHB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#D2691E" ));
            toColorTV.setBackgroundColor( Color.parseColor( "#800080"));
            routingCode.setText( from );
            fromId.setText( "22000" );
            toId.setText( "22099" );

        } else if (from.equals( "BNTB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#98FB98" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#9932cc"));
            routingCode.setText( from );
            fromId.setText( "42500" );
            toId.setText( "42609" );

        } else if (from.equals( "AORB" )) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#b22222" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#87cefa"));
            routingCode.setText( from );
            fromId.setText( "05000" );
            toId.setText( "05999" );

        } else if (from.equals( "KGAB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#1e90ff " ));
            toColorTV.setBackgroundColor( Color.parseColor( "#778899"));
            routingCode.setText( from );
            fromId.setText( "01000" );
            toId.setText( "02099" );

        } else if (from.equals("SKCB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#daa520" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#ffefd5"));
            routingCode.setText(from  );
            fromId.setText( "45000" );
            toId.setText( "45899" );
        } else if (from.equals( "SMTB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#ffa07a" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#00ff7f"));
            routingCode.setText( from );
            fromId.setText( "85000" );
            toId.setText( "85299" );

        } else if (from.equals( "STWB" )) {
            fromColorTv.setBackgroundColor( Color.parseColor( " #ff6347" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#f5deb3"));
            routingCode.setText( from );
            fromId.setText( "31750" );
            toId.setText( "31799" );

        } else if (from.equals( "ITNB")) {
            fromColorTv.setBackgroundColor( Color.parseColor( "#008080" ) );
            toColorTV.setBackgroundColor( Color.parseColor( "#D2B48C"));
            routingCode.setText( from );
            fromId.setText( "36000" );
            toId.setText( "36899" );

        }


    }



}









