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
        final ImageButton emergency1 = (ImageButton) findViewById(R.id.emergency1);
        final ImageButton emergency2 = (ImageButton) findViewById(R.id.emergency2);
        final ImageButton emergency3 = (ImageButton) findViewById(R.id.emergency3);
        final ImageButton emergency4 = (ImageButton) findViewById(R.id.emergency4);
        final ImageButton emergency5 = (ImageButton) findViewById(R.id.emergency5);
        final ImageButton emergency6 = (ImageButton) findViewById(R.id.emergency6);
        final Button generalEmergency = (Button) findViewById(R.id.generalEmergency);

        emergency1.setOnClickListener(new View.OnClickListener(){
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View view){
                // Intent is what you use to start another activity
                Intent intent = new Intent(EmergencyListActivity.this, SelectedEmergencyActivity.class);
                String emergency1Tag = (emergency1.getTag()).toString();
                intent.putExtra("scenario", "Someone is breaking in.");
                startActivity(intent);
            }
        });

        emergency2.setOnClickListener(new View.OnClickListener(){
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View view){
                // Intent is what you use to start another activity
                Intent intent = new Intent(EmergencyListActivity.this, SelectedEmergencyActivity.class);
                String emergency2Tag = (emergency2.getTag()).toString();
                intent.putExtra("scenario", "I or someone near me cannot breathe.");
                startActivity(intent);
            }
        });

        emergency3.setOnClickListener(new View.OnClickListener(){
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View view){
                // Intent is what you use to start another activity
                Intent intent = new Intent(EmergencyListActivity.this, SelectedEmergencyActivity.class);
                String emergency3Tag = (emergency3.getTag()).toString();
                intent.putExtra("scenario", "There is a large fire.");
                startActivity(intent);
            }
        });

        emergency4.setOnClickListener(new View.OnClickListener(){
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View view){
                // Intent is what you use to start another activity
                Intent intent = new Intent(EmergencyListActivity.this, SelectedEmergencyActivity.class);
                String emergency4Tag = (emergency4.getTag()).toString();
                intent.putExtra("scenario", "I or someone near me is seriously injured.");
                startActivity(intent);
            }
        });

        emergency5.setOnClickListener(new View.OnClickListener(){
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View view){
                // Intent is what you use to start another activity
                Intent intent = new Intent(EmergencyListActivity.this, SelectedEmergencyActivity.class);
                String emergency5Tag = (emergency5.getTag()).toString();
                intent.putExtra("scenario", "I am lost and need help getting home.");
                startActivity(intent);
            }
        });

        emergency6.setOnClickListener(new View.OnClickListener(){
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View view){
                // Intent is what you use to start another activity
                Intent intent = new Intent(EmergencyListActivity.this, SelectedEmergencyActivity.class);
                String emergency6Tag = (emergency6.getTag()).toString();
                intent.putExtra("scenario", "I or someone near me is in extreme pain.");
                startActivity(intent);
            }
        });

        generalEmergency.setOnClickListener(new View.OnClickListener(){
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View view){
                // Intent is what you use to start another activity
                Intent intent = new Intent(EmergencyListActivity.this, SelectedEmergencyActivity.class);
                String generalEmergencyTag = (generalEmergency.getTag()).toString();
                intent.putExtra("scenario", "There is an emergency happening around me.");
                startActivity(intent);
            }
        });
    }


}

