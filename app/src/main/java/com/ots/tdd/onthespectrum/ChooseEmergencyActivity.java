package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ChooseEmergencyActivity extends AppCompatActivity {

    ArrayAdapter<EmergencyElement> adapter;

    ArrayList<EmergencyElement> scenarioList = new ArrayList<>();

    int emergencyElementCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        int theme = sharedPref.getInt("colorTheme", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_list);

        final int titleSize = sharedPref.getInt("TitleFontSize", 0);
        final int bodySize = sharedPref.getInt("BodyFontSize", 0);
        final int fontChange = sharedPref.getInt("FontSizeChange", 0);

        TextView title = (TextView) findViewById(R.id.emergencyListTitle);
        title.setTextSize(titleSize + fontChange);

        Button generalEmergency = (Button) findViewById(R.id.generalEmergency);
        generalEmergency.setTextSize(bodySize + fontChange);
        generalEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
                // save full message somewhere?
                String emergencyMessage = "I am in an emergency.";
                intent.putExtra("scenario", emergencyMessage);
                startActivity(intent);
            }
        });

        GridView gridView = (GridView) findViewById(R.id.listOfEmergenciesGridView);
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(50);

        loadInfo();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        final LinearLayout.LayoutParams llParamsImage = new LinearLayout.LayoutParams((width-100)/3, (width-100)/3);
        llParamsImage.gravity = Gravity.CENTER_HORIZONTAL;

        adapter=new ArrayAdapter<EmergencyElement>(this, R.layout.emergency_item, scenarioList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                EmergencyElement current = scenarioList.get(position);

                // Inflate only once
                if(convertView == null) {
                    convertView = getLayoutInflater()
                            .inflate(R.layout.emergency_item, null, false);
                }

                int currEmergencyNumber = current.emergencyNumber;

                final ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.emergencyImageButton);
                imageButton.setBackground( new BitmapDrawable(getResources(), current.getImage()) );
                imageButton.setTag(current.getTitle());
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Intent is what you use to start another activity
                        Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
                        String emergencyTag = (imageButton.getTag()).toString(); //to be passed in
                        // save full message somewhere?
                        String emergencyMessage = emergencyTag;
                        intent.putExtra("scenario", emergencyMessage);
                        startActivity(intent);
                    }
                });
                imageButton.setLayoutParams(llParamsImage); //currently hardcoded, change later

                final TextView textView = (TextView) convertView.findViewById(R.id.emergencyTitle);
                textView.setText(current.getTitle());
                textView.setTextSize(bodySize + fontChange);
                textView.setLayoutParams(new LinearLayout.LayoutParams(350, 55 + fontChange * 4));

                return convertView;
            }
        };

        gridView.setAdapter(adapter);
    }

    private void loadInfo() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String savedScenarios = sharedPref.getString("ScenarioNames", null);
        if (null == savedScenarios) {
            int assignCount = 0;
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

                scenarioList.add(new EmergencyElement("Break In", file.getAbsolutePath(), assignCount));
                assignCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File (myDir, "choking.jpg");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.choking);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("Choking", file.getAbsolutePath(), assignCount));
                assignCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File (myDir, "fire.jpg");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.fire);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("Fire", file.getAbsolutePath(), assignCount));
                assignCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File (myDir, "injury.jpg");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.injury);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("Injury", file.getAbsolutePath(), assignCount));
                assignCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File (myDir, "lost.jpg");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.lost);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("Lost", file.getAbsolutePath(), assignCount));
                assignCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File (myDir, "pain.jpg");
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.pain);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("In Pain", file.getAbsolutePath(), assignCount));
                assignCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            emergencyElementCounter = assignCount;
        } else {
            String[] scenarioNames = savedScenarios.split(";;");
            for (int i = 0; i < scenarioNames.length; i++) {
                String imgLocation = sharedPref.getString(scenarioNames[i], "");
                scenarioList.add(new EmergencyElement(scenarioNames[i], imgLocation, i));
            }
            emergencyElementCounter = scenarioNames.length;
        }
    }

}

