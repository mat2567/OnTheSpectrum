package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SelectedEmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_emergency);

        Bundle bundle = getIntent().getExtras();
        String emergency1Tag = bundle.getString("emergency1Tag");
        String emergency2Tag = bundle.getString("emergency2Tag");
        String emergency3Tag = bundle.getString("emergency3Tag");
        String emergency4Tag = bundle.getString("emergency4Tag");
        String emergency5Tag = bundle.getString("emergency5Tag");
        String emergency6Tag = bundle.getString("emergency6Tag");
        TextView txtView = (TextView) findViewById(R.id.textView2);
        txtView.setText(emergency1Tag);
        txtView.setText(emergency2Tag);
        txtView.setText(emergency3Tag);
        txtView.setText(emergency4Tag);
        txtView.setText(emergency5Tag);
        txtView.setText(emergency6Tag);

        // Show the action bar and return arrow at the top of the screen
        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }
}
