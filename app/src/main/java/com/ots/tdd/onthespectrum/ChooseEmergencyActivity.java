package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ChooseEmergencyActivity extends AppCompatActivity {

    ArrayList<EmergencyElement> scenarioList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_list);
        loadInfo();
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
                Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
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
                Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
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
                Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
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
                Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
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
                Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
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
                Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
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
                Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
                String generalEmergencyTag = (generalEmergency.getTag()).toString();
                intent.putExtra("scenario", "There is an emergency happening around me.");
                startActivity(intent);
            }
        });
    }


    protected void onPause() {
        super.onPause();
        SharedPreferences sp = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String scenarioNames = "";
        for (EmergencyElement item : scenarioList) {
            scenarioNames += item.title;
            scenarioNames += ";;";
            editor.putString(item.title, item.imageMemLocation);
        }
        editor.putString("ScenarioNames", scenarioNames);

        editor.apply();
    }

    private void loadInfo() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String savedScenarios = sharedPref.getString("ScenarioNames", null);
        if (null == savedScenarios) {
            String root = getFilesDir().getAbsolutePath();
            File myDir = new File(root + "/saved_images");
            myDir.mkdirs();

            File file = new File (myDir, "breakIn.jpg");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.breakin);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("Break In", file.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File (myDir, "choking");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.choking);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("Choking", file.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File (myDir, "lost");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.lost);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("Lost", file.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File (myDir, "pain");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.pain);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("In Pain", file.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String[] fields = savedScenarios.split(";;");
            for (int i = 0; i < fields.length; i++) {
                String scenario = sharedPref.getString(fields[i], "");

            }
        }
    }

}

