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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_list);


        GridView gridView = (GridView) findViewById(R.id.listOfEmergenciesGridView);
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(50);

        loadInfo();

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
                EmergencyElementViewContainer currEEVC = null;
                currEEVC = EmergencyElementViewContainer.findContainerUsingNumber(currEmergencyNumber);
                if (currEEVC == null) {
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
                            String emergencyMessage = "I am in a " + emergencyTag + " emergency.";
                            intent.putExtra("scenario", emergencyMessage);
                            startActivity(intent);
                        }
                    });
                    imageButton.setLayoutParams(new LinearLayout.LayoutParams(350, 350)); //currently hardcoded, change later

                    final TextView textView = (TextView) convertView.findViewById(R.id.emergencyTitle);
                    textView.setText(current.getTitle());
                    textView.setLayoutParams(new LinearLayout.LayoutParams(350, 45));

                    EmergencyElementViewContainer newEmergencyElement = new EmergencyElementViewContainer(
                            imageButton, textView, current.getEmergencyNumber());
                    EmergencyElementViewContainer.addEEVCToArray(newEmergencyElement);


                } else {
                    final ImageButton imageButton= (ImageButton) convertView.findViewById(R.id.emergencyImageButton);

                    imageButton.setBackground( new BitmapDrawable(getResources(), current.getImage()) );
                    imageButton.setTag(current.getTitle());
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Intent is what you use to start another activity
                            Intent intent = new Intent(ChooseEmergencyActivity.this, SelectedEmergencyActivity.class);
                            String emergencyTag = (imageButton.getTag()).toString(); //to be passed in
                            // save full message somewhere?
                            String emergencyMessage = "I am in a " + emergencyTag + " emergency.";
                            intent.putExtra("scenario", emergencyMessage);
                            startActivity(intent);
                        }
                    });
                    //imageButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));
                    imageButton.setLayoutParams(new LinearLayout.LayoutParams(350, 350)); //currently hardcoded, change later

                    final TextView textView = (TextView) convertView.findViewById(R.id.emergencyTitle);
                    textView.setText(current.getTitle());
                    textView.setLayoutParams(new LinearLayout.LayoutParams(350, 45));
                }

                return convertView;
            }
        };

        gridView.setAdapter(adapter);
    }


    protected void onPause() {
        super.onPause();
        SharedPreferences sp = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String scenarioNames = "";
        for (EmergencyElement item : scenarioList) {
            scenarioNames += item.getTitle();
            scenarioNames += ";;";
            editor.putString(item.getTitle(), item.imageMemLocation);
        }
        editor.putString("ScenarioNames", scenarioNames);

        editor.apply();
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

