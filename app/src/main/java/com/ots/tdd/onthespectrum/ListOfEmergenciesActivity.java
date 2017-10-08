package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.Arrays;

public class ListOfEmergenciesActivity extends AppCompatActivity {

    ArrayAdapter<EmergencyElement> adapter;

    ArrayList<EmergencyElement> itemList = new ArrayList<>();

    int emergencyElementCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_emergencies);


        GridView gridView = (GridView) findViewById(R.id.listOfEmergenciesGridView);
        gridView.setNumColumns(3);

        /*final ImageButton emergency1 = new ImageButton(this);
        emergency1.setBackground( getResources().getDrawable(R.drawable.breakin));
        emergency1.setTag("Break in");
        emergency1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent intent = new Intent(ListOfEmergenciesActivity.this, SelectedEmergencyActivity.class);
                String generalEmergencyTag = (emergency1.getTag()).toString();
                intent.putExtra("scenario", "There is an emergency happening around me.");
                startActivity(intent);
            }
        });
        emergency1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));

        final ImageButton emergency2 = new ImageButton(this);
        emergency2.setBackground( getResources().getDrawable(R.drawable.choking));
        emergency2.setTag("Choking");
        emergency2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent intent = new Intent(ListOfEmergenciesActivity.this, SelectedEmergencyActivity.class);
                String generalEmergencyTag = (emergency2.getTag()).toString();
                intent.putExtra("scenario", "There is an emergency happening around me.");
                startActivity(intent);
            }
        });
        emergency2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));

        final ImageButton emergency3 = new ImageButton(this);
        emergency3.setBackground( getResources().getDrawable(R.drawable.fire));
        emergency3.setTag("Fire");
        emergency3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent intent = new Intent(ListOfEmergenciesActivity.this, SelectedEmergencyActivity.class);
                String generalEmergencyTag = (emergency3.getTag()).toString();
                intent.putExtra("scenario", "There is an emergency happening around me.");
                startActivity(intent);
            }
        });
        emergency3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));

        final ImageButton emergency4 = new ImageButton(this);
        emergency4.setBackground( getResources().getDrawable(R.drawable.injury));
        emergency4.setTag("Injury");
        emergency4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent intent = new Intent(ListOfEmergenciesActivity.this, SelectedEmergencyActivity.class);
                String generalEmergencyTag = (emergency4.getTag()).toString();
                intent.putExtra("scenario", "There is an emergency happening around me.");
                startActivity(intent);
            }
        });
        emergency4.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));

        final ImageButton emergency5 = new ImageButton(this);
        emergency5.setBackground( getResources().getDrawable(R.drawable.lost));
        emergency5.setTag("Lost");
        emergency5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent intent = new Intent(ListOfEmergenciesActivity.this, SelectedEmergencyActivity.class);
                String generalEmergencyTag = (emergency5.getTag()).toString();
                intent.putExtra("scenario", "There is an emergency happening around me.");
                startActivity(intent);
            }
        });
        emergency5.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));

        final ImageButton emergency6 = new ImageButton(this);
        emergency6.setBackground( getResources().getDrawable(R.drawable.pain));
        emergency6.setTag("Pain");
        emergency6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent intent = new Intent(ListOfEmergenciesActivity.this, SelectedEmergencyActivity.class);
                String generalEmergencyTag = (emergency6.getTag()).toString();
                intent.putExtra("scenario", "There is an emergency happening around me.");
                startActivity(intent);
            }
        });
        emergency6.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));


        itemList.add(new EmergencyElement(emergency1));
        itemList.add(new EmergencyElement(emergency2));
        itemList.add(new EmergencyElement(emergency3));
        itemList.add(new EmergencyElement(emergency4));
        itemList.add(new EmergencyElement(emergency5));
        itemList.add(new EmergencyElement(emergency6));*/

        //gridView.setAdapter(adapter);
        //this is added in attempt to prevent the edittext from being refreshed
        //gridView.setItemsCanFocus(true);

        EmergencyElement emergencyElement1 = new EmergencyElement("Break in", getResources().getDrawable(R.drawable.breakin), 0);
        EmergencyElement emergencyElement2 = new EmergencyElement("Choking", getResources().getDrawable(R.drawable.choking), 1);
        EmergencyElement emergencyElement3 = new EmergencyElement("Fire", getResources().getDrawable(R.drawable.fire), 2);
        EmergencyElement emergencyElement4 = new EmergencyElement("Injury", getResources().getDrawable(R.drawable.injury), 3);
        EmergencyElement emergencyElement5 = new EmergencyElement("Lost", getResources().getDrawable(R.drawable.lost), 4);
        EmergencyElement emergencyElement6 = new EmergencyElement("Pain", getResources().getDrawable(R.drawable.pain), 5);
        itemList.add(emergencyElement1);
        itemList.add(emergencyElement2);
        itemList.add(emergencyElement3);
        itemList.add(emergencyElement4);
        itemList.add(emergencyElement5);
        itemList.add(emergencyElement6);
        emergencyElementCounter = 6;


        adapter=new ArrayAdapter<EmergencyElement>(this, R.layout.emergency_item, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                EmergencyElement current = itemList.get(position);

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
                    imageButton.setBackground( current.getImage());
                    imageButton.setTag(current.getTitle());
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Intent is what you use to start another activity
                            Intent intent = new Intent(ListOfEmergenciesActivity.this, SelectedEmergencyActivity.class);
                            String emergencyTag = (imageButton.getTag()).toString(); //to be passed in
                            // save full message somewhere?
                            String emergencyMessage = "I am in a " + emergencyTag + " emergency.";
                            intent.putExtra("scenario", emergencyMessage);
                            startActivity(intent);
                        }
                    });
                    //imageButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));
                    imageButton.setLayoutParams(new LinearLayout.LayoutParams(350, 350)); //currently hardcoded, change later


                    EmergencyElementViewContainer newEmergencyElement = new EmergencyElementViewContainer(
                            imageButton, current.getEmergencyNumber());
                    EmergencyElementViewContainer.addEEVCToArray(newEmergencyElement);


                } else {
                    final ImageButton imageButton= (ImageButton) convertView.findViewById(R.id.emergencyImageButton);

                    imageButton.setBackground( current.getImage());
                    imageButton.setTag(current.getTitle());
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Intent is what you use to start another activity
                            Intent intent = new Intent(ListOfEmergenciesActivity.this, SelectedEmergencyActivity.class);
                            String emergencyTag = (imageButton.getTag()).toString(); //to be passed in
                            // save full message somewhere?
                            String emergencyMessage = "I am in a " + emergencyTag + " emergency.";
                            intent.putExtra("scenario", emergencyMessage);
                            startActivity(intent);
                        }
                    });
                    //imageButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 132));
                    imageButton.setLayoutParams(new LinearLayout.LayoutParams(350, 350)); //currently hardcoded, change later

                }

                return convertView;
            }
        };

        gridView.setAdapter(adapter);

    }

    public void editInfo(View v) {

    }

    public void saveInfo(View v) {

    }

    public void cancelInfo(View v) {

    }

}
