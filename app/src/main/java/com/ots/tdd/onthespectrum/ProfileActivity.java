package com.ots.tdd.onthespectrum;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    ArrayAdapter<ProfileElement> adapter;
    EditText editInfoType;
    EditText editUserInfo;
    TextView alertTextView;

    TextWatcher textWatcher=new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String editingText = s.toString();
            // displayExceptionMessage("editing");
            if (editingNum >= 0) {
                itemList.get(editingNum).userInfo = editingText;
            }

        }
    };

    static int editingNum = -1;

    static int profileElementCounter = 0;


    static ArrayList<ProfileElement> itemList = new ArrayList<>();
    static HashMap<Integer, ImageView> editMap = new HashMap<>();
    static HashMap<Integer, ImageView> saveMap = new HashMap<>();
    static HashMap<Integer, ImageView> cancelMap = new HashMap<>();
    static HashMap<Integer, EditText> textMap = new HashMap<>();


    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        int theme = sharedPref.getInt("colorTheme", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (itemList.isEmpty()) {
            loadInfo();
        }

        /*Backs the ListView. Enables TextViews to be added dynamically */
        adapter=new ArrayAdapter<ProfileElement>(this, R.layout.profile_item, itemList) {


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ProfileElement current = itemList.get(position);
                current.profileNumber = position;
                //displayExceptionMessage("got view: " + position);

                // Inflate only once
                if(convertView == null) {
                    convertView = getLayoutInflater()
                            .inflate(R.layout.profile_item, null, false);
                }

                TextView infoType = (TextView) convertView.findViewById(R.id.infoType);
                EditText userInfo = (EditText) convertView.findViewById(R.id.userInfo);
                ImageView editInfo = (ImageView) convertView.findViewById(R.id.editInfo);
                ImageView saveInfo = (ImageView) convertView.findViewById(R.id.saveInfo);
                ImageView cancelInfo = (ImageView) convertView.findViewById(R.id.cancelInfo);

                /*userInfo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                        if (hasFocus) {
                            EditText currEditText = (EditText) view;
                            currEditText.setSelection(currEditText.getText().length());
                            //Toast.makeText(getApplicationContext(), "Got the focus", Toast.LENGTH_LONG).show();
                        } else {
                            //Toast.makeText(getApplicationContext(), "Lost the focus", Toast.LENGTH_LONG).show();
                        }
                    }
                });*/

                if (current.profileEdit == null) {
                    editInfo.setTag(current.profileNumber);
                    saveInfo.setTag(current.profileNumber);
                    cancelInfo.setTag(current.profileNumber);
                    infoType.setText(current.infoType);
                    userInfo.setText(current.userInfo);
                    editMap.put(current.profileNumber, editInfo);
                    saveMap.put(current.profileNumber, saveInfo);
                    cancelMap.put(current.profileNumber, cancelInfo);
                    textMap.put(current.profileNumber, userInfo);

                    userInfo.setEnabled(current.editTextEnabled);

                    userInfo.getBackground().clearColorFilter();



                    editInfo.setVisibility(current.editVis);
                    saveInfo.setVisibility(current.saveVis);
                    cancelInfo.setVisibility(current.cancelVis);


                    current.profileEdit = editInfo;
                    current.profileSave = saveInfo;
                    current.profileCancel = cancelInfo;
                    current.profileTextView = infoType;
                    current.profileEditText = userInfo;
                } else {
                    editInfo.setTag(current.profileNumber);
                    saveInfo.setTag(current.profileNumber);
                    cancelInfo.setTag(current.profileNumber);
                    infoType.setText(current.infoType);
                    userInfo.setText(current.userInfo); // might need to watch for refreshes

                    userInfo.setEnabled(current.editTextEnabled);

                    /*if (editingNum == current.profileNumber) {
                        userInfo.addTextChangedListener(textWatcher);
                    }*/


                    userInfo.getBackground().clearColorFilter();

                    editInfo.setVisibility(current.editVis);
                    saveInfo.setVisibility(current.saveVis);
                    cancelInfo.setVisibility(current.cancelVis);

                    current.profileEdit = editInfo;
                    current.profileSave = saveInfo;
                    current.profileCancel = cancelInfo;
                    current.profileTextView = infoType;
                    current.profileEditText = userInfo;
                    editMap.put(current.profileNumber, editInfo);
                    saveMap.put(current.profileNumber, saveInfo);
                    cancelMap.put(current.profileNumber, cancelInfo);
                    textMap.put(current.profileNumber, userInfo);
                    adapter.notifyDataSetChanged();
                }



                // if this remains unused, may delete the ProfileTextWatcher class
                //userInfo.addTextChangedListener(new ProfileTextWatcher(userInfo));

                infoType.setText(current.infoType);
                userInfo.setText(current.userInfo);


                return convertView;
            }
        };
        ListView listV=(ListView)findViewById(R.id.list);
        listV.setAdapter(adapter);
        //this is added in attempt to prevent the edittext from being refreshed
        listV.setItemsCanFocus(true);


        // Set button listener
        Button callLogButton =(Button)findViewById(R.id.callLogButton);
        callLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToCallLogScreen(v);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!SettingsActivity.isLocked) {
            Button addProfileButton =(Button)findViewById(R.id.addProfileButton);
            addProfileButton.setEnabled(true);
        } else {
            Button addProfileButton =(Button)findViewById(R.id.addProfileButton);
            addProfileButton.setEnabled(false);
        }
    }

    protected void onPause() {
        super.onPause();
        SharedPreferences sp = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String fieldNames = "";
        for (ProfileElement item : itemList) {
            fieldNames += item.infoType;
            fieldNames += ";;";
            editor.putString(item.infoType, item.userInfo);
        }
        editor.putString("ProfileFields", fieldNames);

        editor.apply();

        if (editingNum != -1) {
            int profilePos = editingNum;
            ProfileElement current = itemList.get(profilePos);

            displayExceptionMessage("Element number: " + profilePos);

            ImageView currEditInfo = editMap.get(profilePos);
            ImageView currSaveInfo = saveMap.get(profilePos);
            ImageView currCancelInfo = cancelMap.get(profilePos);
            EditText currEditText = textMap.get(profilePos);

            current.editTextEnabled = false;
            currEditText.setEnabled(current.editTextEnabled);
            currEditText.setText(current.previousUserInfo);
            currEditText.setBackgroundColor(0x0106000d); //R.color.transparent

            currEditText.removeTextChangedListener(textWatcher);

            current.editVis = View.VISIBLE;
            current.saveVis = View.GONE;
            current.cancelVis = View.GONE;

            currEditInfo.setVisibility(current.editVis);
            currSaveInfo.setVisibility(current.saveVis);
            currCancelInfo.setVisibility(current.cancelVis);

            current.profileEdit = currEditInfo;
            current.profileSave = currSaveInfo;
            current.profileCancel = currCancelInfo;
            current.profileEditText = currEditText;
            adapter.notifyDataSetChanged();
            editingNum = -1;
        }

    }


    public void editInfo(View v) {
        if (!SettingsActivity.isLocked) {

            if (editingNum != -1) {
                displayExceptionMessage("Currently Editing an Item");
            } else {
                ImageView currEditInfo = (ImageView) v;

                int profilePos = Integer.parseInt((currEditInfo.getTag()).toString());
                ProfileElement current = itemList.get(profilePos);

                editingNum = profilePos;

                displayExceptionMessage("Element number: " + profilePos);

                ImageView currSaveInfo = saveMap.get(profilePos);
                ImageView currCancelInfo = cancelMap.get(profilePos);
                EditText currEditText = textMap.get(profilePos);

                current.editTextEnabled = true;
                currEditText.setEnabled(current.editTextEnabled);
                current.previousUserInfo = current.userInfo;
                currEditText.setBackgroundColor(0x01060000); //R.color.darker_grey


                currEditText.addTextChangedListener(textWatcher);

                current.editVis = View.GONE;
                current.saveVis = View.VISIBLE;
                current.cancelVis = View.VISIBLE;

                currEditInfo.setVisibility(current.editVis);
                currSaveInfo.setVisibility(current.saveVis);
                currCancelInfo.setVisibility(current.cancelVis);

                current.profileEdit = currEditInfo;
                current.profileSave = currSaveInfo;
                current.profileCancel = currCancelInfo;
                current.profileEditText = currEditText;
                adapter.notifyDataSetChanged();
            }
        } else {
            displayExceptionMessage("Customization is Locked");
        }
    }

    public void saveInfo(View v) {
        ImageView currSaveInfo = (ImageView) v;

        int profilePos = Integer.parseInt((currSaveInfo.getTag()).toString());
        ProfileElement current = itemList.get(profilePos);

        editingNum = -1;

        displayExceptionMessage("Element number: " + profilePos);

        ImageView currEditInfo = editMap.get(profilePos);
        ImageView currCancelInfo = cancelMap.get(profilePos);
        EditText currEditText = textMap.get(profilePos);

        currEditText.removeTextChangedListener(textWatcher);

        String field = itemList.get(profilePos).infoType;
        String info = currEditText.getText().toString();
        // Why create a new one?
        itemList.set(profilePos, new ProfileElement(field, info, profilePos));

        current.editTextEnabled = false;
        currEditText.setEnabled(current.editTextEnabled);
        currEditText.setBackgroundColor(0x0106000d); //R.color.transparent

        current.editVis = View.VISIBLE;
        current.saveVis = View.GONE;
        current.cancelVis = View.GONE;

        currEditInfo.setVisibility(current.editVis);
        currSaveInfo.setVisibility(current.saveVis);
        currCancelInfo.setVisibility(current.cancelVis);

        current.profileEdit = currEditInfo;
        current.profileSave = currSaveInfo;
        current.profileCancel = currCancelInfo;
        current.profileEditText = currEditText;
        adapter.notifyDataSetChanged();
    }

    public void cancelInfo(View v) {
        ImageView currCancelInfo = (ImageView) v;

        int profilePos = Integer.parseInt((currCancelInfo.getTag()).toString());
        ProfileElement current = itemList.get(profilePos);

        editingNum = -1;

        displayExceptionMessage("Element number: " + profilePos);

        ImageView currEditInfo = editMap.get(profilePos);
        ImageView currSaveInfo = saveMap.get(profilePos);
        EditText currEditText = textMap.get(profilePos);

        currEditText.removeTextChangedListener(textWatcher);

        current.editTextEnabled = false;
        currEditText.setEnabled(current.editTextEnabled);
        currEditText.setText(current.previousUserInfo);
        currEditText.setBackgroundColor(0x0106000d); //R.color.transparent

        current.editVis = View.VISIBLE;
        current.saveVis = View.GONE;
        current.cancelVis = View.GONE;

        currEditInfo.setVisibility(current.editVis);
        currSaveInfo.setVisibility(current.saveVis);
        currCancelInfo.setVisibility(current.cancelVis);

        current.profileEdit = currEditInfo;
        current.profileSave = currSaveInfo;
        current.profileCancel = currCancelInfo;
        current.profileEditText = currEditText;
        adapter.notifyDataSetChanged();

    }

    private void loadInfo() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String savedProfFields = sharedPref.getString("ProfileFields", null);
        if (null == savedProfFields) {
            itemList.add(new ProfileElement("Name", "", 0));
            itemList.add(new ProfileElement("Gender", "", 1));
            itemList.add(new ProfileElement("Age", "", 2));
            itemList.add(new ProfileElement("Phone Number", "", 3));
            itemList.add(new ProfileElement("Home Address", "", 4));
        } else {
            String[] fields = savedProfFields.split(";;");
            for (int i = 0; i < fields.length; i++) {
                String info = sharedPref.getString(fields[i], "");
                itemList.add(new ProfileElement(fields[i], info, i));
            }
        }
        profileElementCounter = itemList.size();
    }

    public void moveToCallLogScreen(View v) {
        Intent intentCall = new Intent(this, CallLogActivity.class);
        startActivity(intentCall);
    }

    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showPopUp(View v) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_field_dialog);

        dialog.setTitle("Create New Profile Field");
        dialog.setCancelable(true);

        Button createField = (Button) dialog.findViewById(R.id.createFieldButton);
        Button cancelField = (Button) dialog.findViewById(R.id.cancelFieldButton);

        createField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newField = (EditText) dialog.findViewById(R.id.fieldEditText);
                EditText newInfo = (EditText) dialog.findViewById(R.id.infoEditText);
                if(newField.getText().toString().isEmpty() || newInfo.getText().toString().isEmpty()){
                    displayExceptionMessage("Please fill out both text boxes.");
                } else{
                    if (!SettingsActivity.isLocked) {
                        String newFieldString = newField.getText().toString();
                        String newInfoString = newInfo.getText().toString();

                        // add new item to arraylist
                        itemList.add(new ProfileElement(newFieldString, newInfoString, profileElementCounter));
                        profileElementCounter++;
                        // notify listview of data changed
                        adapter.notifyDataSetChanged();

                    } else {
                        displayExceptionMessage("Customization is Locked");
                    }

                    dialog.dismiss();
                }
            }
        });

        cancelField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }
}

