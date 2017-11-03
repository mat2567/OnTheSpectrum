package com.ots.tdd.onthespectrum;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ListOfEmergenciesActivity extends AppCompatActivity {

    static ArrayAdapter<EmergencyElement> adapter;

    static ArrayList<EmergencyElement> scenarioList = new ArrayList<>();

    int emergencyElementCounter = 0;

    int titleSize;
    int bodySize;
    int fontChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        int theme = sharedPref.getInt("colorTheme", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_emergencies);

        titleSize = sharedPref.getInt("TitleFontSize", 0);
        bodySize = sharedPref.getInt("BodyFontSize", 0);
        fontChange = sharedPref.getInt("FontSizeChange", 0);

        TextView title = (TextView) findViewById(R.id.listForEditingTitle);
        title.setTextSize(titleSize + fontChange);

        GridView gridView = (GridView) findViewById(R.id.listOfEmergenciesGridView);
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(50);

        if(scenarioList.size() < 1) {
            loadInfo();

            String root = getFilesDir().getAbsolutePath();
            File myDir = new File(root + "/saved_images");
            myDir.mkdirs();
            File file = new File (myDir, "addition.jpg");
            int assignNum = scenarioList.size();
            if (file.exists ()) file.delete ();
            try {
                FileOutputStream out = new FileOutputStream(file);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.addition);
                img.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                scenarioList.add(new EmergencyElement("", file.getAbsolutePath(), assignNum));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
                    imageButton.setTag(current.getEmergencyNumber());
                    if (currEmergencyNumber == scenarioList.size() - 1) {
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!SettingsActivity.isLocked) {
                                    newEmergency(v);
                                } else {
                                    displayExceptionMessage("Customization is Locked");
                                }
                            }
                        });
                    } else {
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!SettingsActivity.isLocked) {
                                    showPopUp(Integer.parseInt((imageButton.getTag()).toString()));
                                } else {
                                    displayExceptionMessage("Customization is Locked");
                                }
                            }
                        });
                    }
                    imageButton.setLayoutParams(new LinearLayout.LayoutParams(350, 350)); //currently hardcoded, change later

                    final TextView textView = (TextView) convertView.findViewById(R.id.emergencyTitle);
                    textView.setText(current.getTitle());
                    textView.setTextSize(bodySize + fontChange);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(350, 55 + fontChange * 4));

                    EmergencyElementViewContainer newEmergencyElement = new EmergencyElementViewContainer(
                            imageButton, textView, current.getEmergencyNumber());
                    EmergencyElementViewContainer.addEEVCToArray(newEmergencyElement);


                } else {
                    final ImageButton imageButton= (ImageButton) convertView.findViewById(R.id.emergencyImageButton);

                    imageButton.setBackground( new BitmapDrawable(getResources(), current.getImage()) );
                    imageButton.setTag(current.getEmergencyNumber());
                    if (currEmergencyNumber == scenarioList.size() - 1) {
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!SettingsActivity.isLocked) {
                                    newEmergency(v);
                                } else {
                                    displayExceptionMessage("Customization is Locked");
                                }
                            }
                        });
                    } else {
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!SettingsActivity.isLocked) {
                                    showPopUp(Integer.parseInt((imageButton.getTag()).toString()));
                                } else {
                                    displayExceptionMessage("Customization is Locked");
                                }
                            }
                        });
                    }
                    //imageButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));
                    imageButton.setLayoutParams(new LinearLayout.LayoutParams(350, 350)); //currently hardcoded, change later

                    final TextView textView = (TextView) convertView.findViewById(R.id.emergencyTitle);
                    textView.setText(current.getTitle());
                    textView.setTextSize(bodySize + fontChange);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(350, 55 + fontChange * 4));
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

    private void showPopUp(int scenarioIndex) {

        final int index = scenarioIndex;

        EmergencyElement currentElement = scenarioList.get(scenarioIndex);
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle(currentElement.getTitle());
        //helpBuilder.setMessage("This is a Simple Pop Up");
        helpBuilder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Intent is what you use to start another activity
                Intent intent = new Intent(ListOfEmergenciesActivity.this, EditEmergencyActivity.class);
                String emergencyNum = "" + index; //to be passed in
                intent.putExtra("emergencyNum", emergencyNum);
                startActivity(intent);
            }
        });

        helpBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int curr = index;
                int len = scenarioList.size() - 1;
                EmergencyElement removed = scenarioList.remove(index);
                while (curr < len) {
                    scenarioList.get(curr).setEmergencyNumber(curr);
                    curr++;
                }

                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                //remove scenario
                editor.remove(removed.getTitle());

                //remove scenario from list of scenario names
                String allScenarios = sharedPref.getString("ScenarioNames", null);
                allScenarios = allScenarios.replace((removed.getTitle() + ";;"), "");
                editor.putString("ScenarioNames", allScenarios);

                editor.commit();

                // notify gridview of data changed
                adapter.notifyDataSetChanged();
            }
        });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

        helpDialog.getButton(Dialog.BUTTON_NEGATIVE).setTextSize(bodySize + fontChange);
        helpDialog.getButton(Dialog.BUTTON_POSITIVE).setTextSize(bodySize + fontChange);
    }

    public void newEmergency(View v) {
        Intent intent = new Intent(ListOfEmergenciesActivity.this, AddEmergencyActivity.class);
        startActivity(intent);
    }

    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
