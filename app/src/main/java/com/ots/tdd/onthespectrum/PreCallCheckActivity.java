package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by francestsenn on 9/28/17.
 */

public class PreCallCheckActivity extends AppCompatActivity {
    ScrollView infoScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precallcheck);
        String scenarioInfo = getIntent().getStringExtra("Selected");
        String infoText = "I am in an emergency. There is a <b>" + scenarioInfo + "</b>.";
        Spanned text = Html.fromHtml(infoText);

        infoScrollView = (ScrollView) findViewById(R.id.infoScrollView);
        TextView infoTextView = new TextView(this);
        infoTextView.setText(text);
        infoScrollView.addView(infoTextView);
    }
}
