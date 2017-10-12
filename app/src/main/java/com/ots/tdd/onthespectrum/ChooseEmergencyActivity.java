package com.ots.tdd.onthespectrum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ChooseEmergencyActivity extends AppCompatActivity {

    ArrayAdapter<EmergencyElement> adapter;

    ArrayList<EmergencyElement> itemList = new ArrayList<>();

    int emergencyElementCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_list);


        GridView gridView = (GridView) findViewById(R.id.listOfEmergenciesGridView);
        gridView.setNumColumns(3);

        // These instantiations are repeated in ListOfEmergencyActivity
        // Need to double check when persistence is added

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

                }

                return convertView;
            }
        };

        gridView.setAdapter(adapter);
    }


}

