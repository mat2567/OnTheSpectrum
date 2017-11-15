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
            // displayExceptionMessage("new: " + editingText);
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
    static HashMap<Integer, ImageView> deleteMap = new HashMap<>();
    static HashMap<Integer, EditText> textMap = new HashMap<>();

    SharedPreferences sharedPref;

    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        int theme = sharedPref.getInt("colorTheme", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (sharedPref.getBoolean("RefreshProfile", false)) {
            itemList.clear();
            sharedPref.edit().putBoolean("RefreshProfile", false);
        }

        if (itemList.isEmpty()) {
            loadInfo();
        }

        final int titleSize = sharedPref.getInt("TitleFontSize", 0);
        final int bodySize = sharedPref.getInt("BodyFontSize", 0);
        final int fontChange = sharedPref.getInt("FontSizeChange", 0);

        TextView profileTitle = (TextView) findViewById(R.id.profileTitle);
        profileTitle.setTextSize(titleSize + fontChange);
        Button callLog = (Button) findViewById(R.id.callLogButton);
        callLog.setTextSize(bodySize + fontChange);
        Button add = (Button) findViewById(R.id.addProfileButton);
        add.setTextSize(bodySize + fontChange);


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
                ImageView deleteInfo = (ImageView) convertView.findViewById(R.id.deleteInfo);

                userInfo.setBackgroundColor(0x0106000d); //R.color.transparent

                userInfo.removeTextChangedListener(textWatcher);
                userInfo.setText(current.userInfo);
                if (editingNum != -1) {
                    if (editingNum == current.profileNumber) {
                        userInfo.addTextChangedListener(textWatcher);
                    }
                }

                if (current.profileEdit == null) {
                    editInfo.setTag(current.profileNumber);
                    saveInfo.setTag(current.profileNumber);
                    cancelInfo.setTag(current.profileNumber);
                    deleteInfo.setTag(current.profileNumber);
                    infoType.setText(current.infoType);
                    editMap.put(current.profileNumber, editInfo);
                    saveMap.put(current.profileNumber, saveInfo);
                    cancelMap.put(current.profileNumber, cancelInfo);
                    deleteMap.put(current.profileNumber, deleteInfo);
                    textMap.put(current.profileNumber, userInfo);

                    userInfo.setEnabled(current.editTextEnabled);

                    userInfo.getBackground().clearColorFilter();

                    editInfo.setVisibility(current.editVis);
                    saveInfo.setVisibility(current.saveVis);
                    cancelInfo.setVisibility(current.cancelVis);
                    deleteInfo.setVisibility(current.deleteVis);

                    current.profileEdit = editInfo;
                    current.profileSave = saveInfo;
                    current.profileCancel = cancelInfo;
                    current.profileDelete = deleteInfo;
                    current.profileTextView = infoType;
                    current.profileEditText = userInfo;
                } else {


                    editInfo.setTag(current.profileNumber);
                    saveInfo.setTag(current.profileNumber);
                    cancelInfo.setTag(current.profileNumber);
                    deleteInfo.setTag(current.profileNumber);
                    infoType.setText(current.infoType);

                    userInfo.setEnabled(current.editTextEnabled);

                    userInfo.getBackground().clearColorFilter();

                    editInfo.setVisibility(current.editVis);
                    saveInfo.setVisibility(current.saveVis);
                    cancelInfo.setVisibility(current.cancelVis);
                    deleteInfo.setVisibility(current.deleteVis);

                    current.profileEdit = editInfo;
                    current.profileSave = saveInfo;
                    current.profileCancel = cancelInfo;
                    current.profileDelete = deleteInfo;
                    current.profileTextView = infoType;
                    current.profileEditText = userInfo;
                    editMap.put(current.profileNumber, editInfo);
                    saveMap.put(current.profileNumber, saveInfo);
                    cancelMap.put(current.profileNumber, cancelInfo);
                    deleteMap.put(current.profileNumber, deleteInfo);
                    textMap.put(current.profileNumber, userInfo);
                    adapter.notifyDataSetChanged();
                }

                infoType.setText(current.infoType);

                infoType.setTextSize(bodySize + 2 + fontChange);
                userInfo.setTextSize(bodySize + 2 + fontChange);


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

            // displayExceptionMessage("Element number: " + profilePos);

            ImageView currEditInfo = editMap.get(profilePos);
            ImageView currSaveInfo = saveMap.get(profilePos);
            ImageView currCancelInfo = cancelMap.get(profilePos);
            ImageView currDeleteInfo = deleteMap.get(profilePos);
            EditText currEditText = textMap.get(profilePos);

            currEditText.removeTextChangedListener(textWatcher);

            current.editTextEnabled = false;
            currEditText.setEnabled(current.editTextEnabled);
            currEditText.setText(current.previousUserInfo);
            current.userInfo = current.previousUserInfo;
            currEditText.setBackgroundColor(0x0106000d); //R.color.transparent


            current.editVis = View.VISIBLE;
            current.deleteVis = View.VISIBLE;
            current.saveVis = View.GONE;
            current.cancelVis = View.GONE;

            currEditInfo.setVisibility(current.editVis);
            currSaveInfo.setVisibility(current.saveVis);
            currCancelInfo.setVisibility(current.cancelVis);
            currDeleteInfo.setVisibility(current.deleteVis);

            current.profileEdit = currEditInfo;
            current.profileSave = currSaveInfo;
            current.profileCancel = currCancelInfo;
            current.profileDelete = currDeleteInfo;
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

                // displayExceptionMessage("Element number: " + profilePos);

                ImageView currSaveInfo = saveMap.get(profilePos);
                ImageView currCancelInfo = cancelMap.get(profilePos);
                ImageView currDeleteInfo = deleteMap.get(profilePos);
                EditText currEditText = textMap.get(profilePos);

                current.editTextEnabled = true;
                currEditText.setEnabled(current.editTextEnabled);
                current.previousUserInfo = current.userInfo;
                //currEditText.setBackgroundColor(0x01060000); //R.color.darker_grey
                //currEditText.setBackgroundColor(0x00E0E000); //R.color.darker_grey


                currEditText.addTextChangedListener(textWatcher);

                current.editVis = View.GONE;
                current.deleteVis = View.GONE;
                current.saveVis = View.VISIBLE;
                current.cancelVis = View.VISIBLE;

                currEditInfo.setVisibility(current.editVis);
                currSaveInfo.setVisibility(current.saveVis);
                currCancelInfo.setVisibility(current.cancelVis);
                currDeleteInfo.setVisibility(current.deleteVis);

                current.profileEdit = currEditInfo;
                current.profileSave = currSaveInfo;
                current.profileCancel = currCancelInfo;
                current.profileDelete = currDeleteInfo;
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

        // displayExceptionMessage("Element number: " + profilePos);

        ImageView currEditInfo = editMap.get(profilePos);
        ImageView currCancelInfo = cancelMap.get(profilePos);
        ImageView currDeleteInfo = deleteMap.get(profilePos);
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
        current.deleteVis = View.VISIBLE;
        current.saveVis = View.GONE;
        current.cancelVis = View.GONE;

        currEditInfo.setVisibility(current.editVis);
        currSaveInfo.setVisibility(current.saveVis);
        currCancelInfo.setVisibility(current.cancelVis);
        currDeleteInfo.setVisibility(current.deleteVis);

        current.profileEdit = currEditInfo;
        current.profileSave = currSaveInfo;
        current.profileCancel = currCancelInfo;
        current.profileDelete = currDeleteInfo;
        current.profileEditText = currEditText;
        adapter.notifyDataSetChanged();
    }

    public void cancelInfo(View v) {
        ImageView currCancelInfo = (ImageView) v;

        int profilePos = Integer.parseInt((currCancelInfo.getTag()).toString());
        ProfileElement current = itemList.get(profilePos);

        editingNum = -1;

        // displayExceptionMessage("Element number: " + profilePos);

        ImageView currEditInfo = editMap.get(profilePos);
        ImageView currSaveInfo = saveMap.get(profilePos);
        ImageView currDeleteInfo = deleteMap.get(profilePos);
        EditText currEditText = textMap.get(profilePos);

        currEditText.removeTextChangedListener(textWatcher);

        current.editTextEnabled = false;
        currEditText.setEnabled(current.editTextEnabled);
        currEditText.setText(current.previousUserInfo);
        current.userInfo = current.previousUserInfo;
        currEditText.setBackgroundColor(0x0106000d); //R.color.transparent

        current.editVis = View.VISIBLE;
        current.deleteVis = View.VISIBLE;
        current.saveVis = View.GONE;
        current.cancelVis = View.GONE;

        currEditInfo.setVisibility(current.editVis);
        currSaveInfo.setVisibility(current.saveVis);
        currCancelInfo.setVisibility(current.cancelVis);
        currDeleteInfo.setVisibility(current.deleteVis);

        current.profileEdit = currEditInfo;
        current.profileSave = currSaveInfo;
        current.profileCancel = currCancelInfo;
        current.profileDelete = currDeleteInfo;
        current.profileEditText = currEditText;
        adapter.notifyDataSetChanged();

    }


    public void deleteInfo(View v) {
        if (!SettingsActivity.isLocked) {

            if (editingNum != -1) {
                displayExceptionMessage("Currently Editing an Item");
            } else {
                ImageView currDeleteInfo = (ImageView) v;

                int profilePos = Integer.parseInt((currDeleteInfo.getTag()).toString());

                deletePopUp(profilePos);
            }
        } else {
            displayExceptionMessage("Customization is Locked");
        }

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
            // displayExceptionMessage("Size: " + fields.length);
            if (fields.length < 1 || sharedPref.getString(fields[0], "") == null || sharedPref.getString(fields[0], "").equals("")) {
                itemList.add(new ProfileElement("Name", "", 0));
                itemList.add(new ProfileElement("Gender", "", 1));
                itemList.add(new ProfileElement("Age", "", 2));
                itemList.add(new ProfileElement("Phone Number", "", 3));
                itemList.add(new ProfileElement("Home Address", "", 4));
            } else {
                for (int i = 0; i < fields.length; i++) {
                    String info = sharedPref.getString(fields[i], "");
                    itemList.add(new ProfileElement(fields[i], info, i));
                }
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

        final int bodySize = sharedPref.getInt("BodyFontSize", 0);
        final int fontChange = sharedPref.getInt("FontSizeChange", 0);

        dialog.setTitle("Create New Profile Field");
        dialog.setCancelable(true);

        Button createField = (Button) dialog.findViewById(R.id.createFieldButton);
        Button cancelField = (Button) dialog.findViewById(R.id.cancelFieldButton);

        createField.setTextSize(bodySize + fontChange);
        cancelField.setTextSize(bodySize + fontChange);

        final EditText newField = (EditText) dialog.findViewById(R.id.fieldEditText);
        newField.setTextSize(bodySize + 2 + fontChange);
        final EditText newInfo = (EditText) dialog.findViewById(R.id.infoEditText);
        newInfo.setTextSize(bodySize + 2 + fontChange);

        createField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void deletePopUp(int itemIndex) {

        final int index = itemIndex;

        ProfileElement currentElement = itemList.get(itemIndex);
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Do you want to delete " + currentElement.infoType + "?");
        //helpBuilder.setMessage("This is a Simple Pop Up");
        helpBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                ProfileElement current = itemList.get(index);
                // displayExceptionMessage("Element number: " + profilePos);

                ImageView currEditInfo = editMap.get(index);
                ImageView currSaveInfo = saveMap.get(index);
                ImageView currCancelInfo = cancelMap.get(index);
                ImageView currDeleteInfo = deleteMap.get(index);
                EditText currEditText = textMap.get(index);

                current.profileEdit = currEditInfo;
                current.profileSave = currSaveInfo;
                current.profileCancel = currCancelInfo;
                current.profileDelete = currDeleteInfo;
                current.profileEditText = currEditText;
                adapter.notifyDataSetChanged();

                int currInd = index;
                ProfileElement removed = itemList.remove(index);
                int len = itemList.size();
                while (currInd < len) {
                    ProfileElement currElem = itemList.get(currInd);
                    int prevInd = currElem.profileNumber;
                    currElem.profileNumber = currInd;

                    editMap.put(currInd, editMap.get(prevInd));
                    editMap.get(currInd).setTag(currInd);
                    saveMap.put(currInd, saveMap.get(prevInd));
                    saveMap.get(currInd).setTag(currInd);
                    cancelMap.put(currInd, cancelMap.get(prevInd));
                    cancelMap.get(currInd).setTag(currInd);
                    deleteMap.put(currInd, deleteMap.get(prevInd));
                    deleteMap.get(currInd).setTag(currInd);


                    currInd++;
                }

                profileElementCounter = profileElementCounter - 1;

                // notify gridview of data changed
                adapter.notifyDataSetChanged();
            }
        });

        helpBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.dismiss();
            }
        });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();

//        helpDialog.getButton(Dialog.BUTTON_NEGATIVE).setTextSize(bodySize + fontChange);
//        helpDialog.getButton(Dialog.BUTTON_POSITIVE).setTextSize(bodySize + fontChange);
    }
}

