package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class AddEmergencyActivity extends AppCompatActivity {

    public EditText emergencyTitle;
    public ImageButton emergencyImage;
    private String currentImage;
    private Button browseButton;

    private static int RESULT_LOAD_IMAGE = 1;

    int subtitleSize;
    int bodySize;
    int fontChange;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        int theme = sharedPref.getInt("colorTheme", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency);

        // To have a placeholder image, uncomment these lines and retrieve it
        // EmergencyElement addEmergency = ListOfEmergenciesActivity.scenarioList.get(ListOfEmergenciesActivity.scenarioList.size() - 1);
        emergencyTitle = (EditText) findViewById(R.id.emergencyTitle);
        emergencyImage = (ImageButton) findViewById(R.id.emergencyImage);
        browseButton = (Button) findViewById(R.id.browseButton);

        // emergencyImage.setBackground( new BitmapDrawable(getResources(), addEmergency.getImage()) );

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        LinearLayout.LayoutParams llParamsRect = new LinearLayout.LayoutParams(width*3/4, width/8);
        llParamsRect.gravity = Gravity.CENTER_HORIZONTAL;
        browseButton.setLayoutParams(llParamsRect);

        LinearLayout.LayoutParams llParamsSquare = new LinearLayout.LayoutParams(width*3/4, width*3/4);
        llParamsSquare.gravity = Gravity.CENTER_HORIZONTAL;
        emergencyImage.setLayoutParams(llParamsSquare);

        setTextSizes();
    }

    public void saveChanges(View v) {
        String title = emergencyTitle.getText().toString();
        EmergencyElement emergency = new EmergencyElement(title,
                currentImage,
                ListOfEmergenciesActivity.scenarioList.size());
        if (title.isEmpty()) {
            displayExceptionMessage("Please provide a title");
        } else {
            emergency.setTitle(title);
            if ((BitmapDrawable)emergencyImage.getDrawable() != null){
                emergency.setImage(((BitmapDrawable) emergencyImage.getDrawable()).getBitmap());
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                //change ScenarioNames
                String allScenarios = sharedPref.getString("ScenarioNames", null);
                allScenarios.concat(title);
                editor.putString("ScenarioNames", allScenarios);

                //add new title/image
                editor.putString(title, currentImage);

                editor.commit();

                // update scenarioList
                int curr = 0;
                int len = ListOfEmergenciesActivity.scenarioList.size();
                ListOfEmergenciesActivity.scenarioList.add(len, emergency);
                while (curr < len) {
                    ListOfEmergenciesActivity.scenarioList.get(curr).setEmergencyNumber(curr);
                    curr++;
                }

                // notify gridview of data changed
                ListOfEmergenciesActivity.adapter.notifyDataSetChanged();
                finish();
            } else {
                displayExceptionMessage("Please provide an image");
            }
        }



    }

    public void cancelChanges(View v) {
        finish();
    }

    public void changeImage(View v) {
        toastMessage("Changing Image");

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri photoUri = data.getData();
            // Do something with the photo based on Uri
            //https://guides.codepath.com/android/Accessing-the-Camera-and-Stored-Media#accessing-stored-media
            try {
                Bitmap selectedImg = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                // Load the selected image into a preview
                emergencyImage.setImageBitmap(selectedImg);

                // Save the image into app data
                String root = getFilesDir().getAbsolutePath();
                File myDir = new File(root + "/saved_images");
                File file = new File (myDir, getSaltString());
                if (file.exists ()) file.delete ();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    selectedImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                    currentImage = file.getAbsolutePath();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                toastMessage("Choose a different image");
            }
            // scale this later
        }


    }

    public void toastMessage(String msg) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    private void setTextSizes() {
        Button createButton = (Button) findViewById(R.id.saveEdit);
        Button cancelButton = (Button) findViewById(R.id.cancelEdit);

        subtitleSize = sharedPref.getInt("SubtitleFontSize", 0);
        bodySize = sharedPref.getInt("BodyFontSize", 0);
        fontChange = sharedPref.getInt("FontSizeChange", 0);

        emergencyTitle.setTextSize(subtitleSize + fontChange);
        browseButton.setTextSize(bodySize + fontChange);
        createButton.setTextSize(bodySize + fontChange);
        cancelButton.setTextSize(bodySize + fontChange);
    }

    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
