package com.ots.tdd.onthespectrum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    ArrayAdapter<String> adapter2;
    EditText editText;
    ArrayList<ProfileElement> itemList;
    ProfileElement[] test = {
            new ProfileElement("Apple", "1"),
            new ProfileElement("Banana", "2"),
            new ProfileElement("Clementine", "3"),
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String[] items={"Apple","Banana","Clementine"};

        //itemList=new ArrayList<String>(Arrays.asList(items));
        //test = new ArrayList<String>(Arrays.asList(testArr));
        itemList = new ArrayList<ProfileElement>(Arrays.asList(test));
        adapter=new ArrayAdapter<ProfileElement>(this, 0, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ProfileElement current = test[position];

                // Inflate only once
                if(convertView == null) {
                    convertView = getLayoutInflater()
                            .inflate(R.layout.profile_item, null, false);
                }

                TextView infoType = (TextView)convertView.findViewById(R.id.infoType);
                TextView userInfo = (TextView)convertView.findViewById(R.id.userInfo);

                infoType.setText(current.infoType);
                userInfo.setText(current.userInfo);

                return convertView;
            }
        };
        ListView listV=(ListView)findViewById(R.id.list);
        listV.setAdapter(adapter);
        editText=(EditText)findViewById(R.id.txtInput);
        Button btAdd=(Button)findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem=editText.getText().toString();
                // add new item to arraylist
                //itemList.add(new ProfileElement(newItem, "testblah"));
                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }

        });

    }


}

