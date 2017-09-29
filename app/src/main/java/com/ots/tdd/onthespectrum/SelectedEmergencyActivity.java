package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.ScrollView;
import android.widget.TextView;

public class SelectedEmergencyActivity extends AppCompatActivity {

    ScrollView infoScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_emergency);
        String scenarioInfo = getIntent().getStringExtra("scenario");
        String infoText = "Hello. " + scenarioInfo;
        Spanned text = Html.fromHtml(infoText);

        infoScrollView = (ScrollView) findViewById(R.id.infoScrollView);
        TextView infoTextView = new TextView(this);
        infoTextView.setText(text);
        infoScrollView.addView(infoTextView);
    }
}
