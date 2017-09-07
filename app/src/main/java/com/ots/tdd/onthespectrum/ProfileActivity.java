package com.ots.tdd.onthespectrum;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfileActivity extends AppCompatActivity {
    ArrayAdapter<ProfileElement> adapter;
    EditText editInfoType;
    EditText editUserInfo;
    TextView alertTextView;

    ArrayList<ProfileElement> itemList;
    ProfileElement[] test = {
            new ProfileElement("Name", "Testing Name"),
            new ProfileElement("Birth Date", "September 7, 2017"),
            new ProfileElement("Gender", "Female"),
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        itemList = new ArrayList<ProfileElement>(Arrays.asList(test));
        adapter=new ArrayAdapter<ProfileElement>(this, R.layout.profile_item, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ProfileElement current = itemList.get(position);

                // Inflate only once
                if(convertView == null) {
                    convertView = getLayoutInflater()
                            .inflate(R.layout.profile_item, null, false);
                }

                EditText infoType = (EditText) convertView.findViewById(R.id.infoType);
                EditText userInfo = (EditText) convertView.findViewById(R.id.userInfo);

                infoType.setText(current.infoType);
                userInfo.setText(current.userInfo);

                return convertView;
            }
        };
        ListView listV=(ListView)findViewById(R.id.list);
        listV.setAdapter(adapter);
        editInfoType=(EditText)findViewById(R.id.infoType);
        editUserInfo = (EditText)findViewById(R.id.userInfo);
        alertTextView = (TextView)findViewById(R.id.alert);
        Button btAdd=(Button)findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newType = editInfoType.getText().toString();
                String newInfo = editUserInfo.getText().toString();

                if (!newType.isEmpty() && !newInfo.isEmpty()) {
                    alertTextView.setTextColor(Color.argb(0, 255, 64, 129));
                    // add new item to arraylist
                    itemList.add(new ProfileElement(newType, newInfo));
                    // notify listview of data changed
                    adapter.notifyDataSetChanged();

                    // resets the text boxes
                    editInfoType.setText("");
                    editUserInfo.setText("");
                } else {
                    alertTextView.setTextColor(Color.argb(255, 255, 64, 129));
                }
            }

        });

    }


}

