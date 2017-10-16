package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class EditEmergencyActivity extends AppCompatActivity {

    public EmergencyElement emergency;
    public EditText emergencyTitle;
    public ImageButton emergencyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emergency);
        int emergencyNum = Integer.parseInt(getIntent().getStringExtra("emergencyNum"));
        emergency = ListOfEmergenciesActivity.scenarioList.get(emergencyNum);
        emergencyTitle = (EditText) findViewById(R.id.emergencyTitle);
        emergencyImage = (ImageButton) findViewById(R.id.emergencyImage);

        emergencyTitle.setText(emergency.getTitle());
        emergencyImage.setBackground( new BitmapDrawable(getResources(), emergency.getImage()) );
        emergencyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change image
            }
        });
        emergencyImage.setLayoutParams(new LinearLayout.LayoutParams(350, 350)); //currently hardcoded, change later


    }

    public void saveChanges(View v) {
        emergency.setTitle(emergencyTitle.getText().toString());
        emergency.setImage(((BitmapDrawable)emergencyImage.getBackground()).getBitmap());

        // notify gridview of data changed
        ListOfEmergenciesActivity.adapter.notifyDataSetChanged();
        finish();
    }

    public void cancelChanges(View v) {
        finish();
    }
}
