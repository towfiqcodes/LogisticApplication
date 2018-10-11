package com.rit.logisticapplication.Actitvity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.rit.logisticapplication.R;

import static com.rit.logisticapplication.Actitvity.DashBoard.taskChange;

public class HelpActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private TextView menuClick;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_help );
       linearLayout=findViewById( R.id.menuLinearLayout );
        menuClick = findViewById( R.id.menu_click );
        menuClick.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu( HelpActivity.this, view );
                popupMenu.setOnMenuItemClickListener(  HelpActivity.this );
                popupMenu.inflate( R.menu.menu );
                popupMenu.show();
            }
        } );

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item_dashBoard:
                taskChange=1;

                Toast.makeText( this, "dashBorad Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_location:
                Toast.makeText(this, "Location sorting Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_parcel:
                Toast.makeText(this, "Parcel Tracking Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_shipments:
                Toast.makeText(this, "Shipment Details Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_help:
                Toast.makeText(this, "Help Clicked", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }
}
