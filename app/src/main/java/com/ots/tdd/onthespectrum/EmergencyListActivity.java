package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EmergencyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_list);
        ImageButton btn = (ImageButton) findViewById(R.id.imageButton10);

        btn.setOnClickListener(new View.OnClickListener(){
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View view){
                // Intent is what you use to start another activity
                Intent intent = new Intent(EmergencyListActivity.this, SelectedEmergencyActivity.class);
                startActivity(intent);
            }
        });

        // Show the action bar and return arrow at the top of the screen
        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }


}

