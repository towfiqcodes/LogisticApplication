package com.rit.logisticapplication.shipmentDetailsActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.rit.logisticapplication.R;

public class ShipmentsDetails_4 extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shipments_details_4 );
        pdfView=findViewById( R.id.pdfViewer1 );
        pdfView.fromAsset( "Towfiqul-3.pdf" ).load();
    }
}
