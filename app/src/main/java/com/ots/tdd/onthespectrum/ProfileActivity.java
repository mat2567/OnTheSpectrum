package com.ots.tdd.onthespectrum;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Will we need to update the text fields here each startup?

        // Might want to have global objects instantiated on create
        // Maybe make an array of Editable objects and generic methods for visibility
        EditText nameEditText = (EditText) findViewById(R.id.profileNameEditText);
        TextView nameTextView = (TextView) findViewById(R.id.profileNameTextView);
        ImageView saveNameIcon = (ImageView) findViewById(R.id.profileSaveNameIcon);
        ImageView cancelNameIcon = (ImageView) findViewById(R.id.profileCancelNameIcon);
        ImageView editNameIcon = (ImageView) findViewById(R.id.profileEditNameIcon);
        Button saveNameButton = (Button) findViewById(R.id.profileSaveNameButton);
        Button cancelNameButton = (Button) findViewById(R.id.profileCancelNameButton);
        Button editNameButton = (Button) findViewById(R.id.profileEditNameButton);

        EditText genderEditText = (EditText) findViewById(R.id.profileGenderEditText);
        TextView genderTextView = (TextView) findViewById(R.id.profileGenderTextView);
        ImageView saveGenderIcon = (ImageView) findViewById(R.id.profileSaveGenderIcon);
        ImageView cancelGenderIcon = (ImageView) findViewById(R.id.profileCancelGenderIcon);
        ImageView editGenderIcon = (ImageView) findViewById(R.id.profileEditGenderIcon);
        Button saveGenderButton = (Button) findViewById(R.id.profileSaveGenderButton);
        Button cancelGenderButton = (Button) findViewById(R.id.profileCancelGenderButton);
        Button editGenderButton = (Button) findViewById(R.id.profileEditGenderButton);

        EditText dobEditText = (EditText) findViewById(R.id.profileDOBEditText);
        TextView dobTextView = (TextView) findViewById(R.id.profileDOBTextView);
        ImageView saveDOBIcon = (ImageView) findViewById(R.id.profileSaveDOBIcon);
        ImageView cancelDOBIcon = (ImageView) findViewById(R.id.profileCancelDOBIcon);
        ImageView editDOBIcon = (ImageView) findViewById(R.id.profileEditDOBIcon);
        Button saveDOBButton = (Button) findViewById(R.id.profileSaveDOBButton);
        Button cancelDOBButton = (Button) findViewById(R.id.profileCancelDOBButton);
        Button editDOBButton = (Button) findViewById(R.id.profileEditDOBButton);

        ImageView saveAllIcon = (ImageView) findViewById(R.id.profileSaveAllIcon);
        ImageView cancelAllIcon = (ImageView) findViewById(R.id.profileCancelAllIcon);
        ImageView editAllIcon = (ImageView) findViewById(R.id.profileEditAllIcon);
        Button saveAllButton = (Button) findViewById(R.id.profileSaveAllButton);
        Button cancelAllButton = (Button) findViewById(R.id.profileCancelAllButton);
        Button editAllButton = (Button) findViewById(R.id.profileEditAllButton);

        nameEditText.setVisibility(View.GONE);
        nameTextView.setVisibility(View.VISIBLE);
        saveNameIcon.setVisibility(View.GONE);
        cancelNameIcon.setVisibility(View.GONE);
        editNameIcon.setVisibility(View.VISIBLE);
        saveNameButton.setVisibility(View.GONE);
        cancelNameButton.setVisibility(View.GONE);
        editNameButton.setVisibility(View.VISIBLE);

        genderEditText.setVisibility(View.GONE);
        genderTextView.setVisibility(View.VISIBLE);
        saveGenderIcon.setVisibility(View.GONE);
        cancelGenderIcon.setVisibility(View.GONE);
        editGenderIcon.setVisibility(View.VISIBLE);
        saveGenderButton.setVisibility(View.GONE);
        cancelGenderButton.setVisibility(View.GONE);
        editGenderButton.setVisibility(View.VISIBLE);

        dobEditText.setVisibility(View.GONE);
        dobTextView.setVisibility(View.VISIBLE);
        saveDOBIcon.setVisibility(View.GONE);
        cancelDOBIcon.setVisibility(View.GONE);
        editDOBIcon.setVisibility(View.VISIBLE);
        saveDOBButton.setVisibility(View.GONE);
        cancelDOBButton.setVisibility(View.GONE);
        editDOBButton.setVisibility(View.VISIBLE);

        saveAllIcon.setVisibility(View.GONE);
        cancelAllIcon.setVisibility(View.GONE);
        editAllIcon.setVisibility(View.VISIBLE);
        saveAllButton.setVisibility(View.GONE);
        cancelAllButton.setVisibility(View.GONE);
        editAllButton.setVisibility(View.VISIBLE);

        // Show the action bar and return arrow at the top of the screen
        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }

    public void profileEditName(View v) {

        EditText nameEditText = (EditText) findViewById(R.id.profileNameEditText);
        TextView nameTextView = (TextView) findViewById(R.id.profileNameTextView);
        // This might be redundant, consider removing
        String nameTextViewString = nameTextView.getText().toString();
        nameEditText.setText(nameTextViewString);
        // Can add an input check here
        nameEditText.setVisibility(View.VISIBLE);
        nameTextView.setVisibility(View.GONE);

        ImageView saveNameIcon = (ImageView) findViewById(R.id.profileSaveNameIcon);
        saveNameIcon.setVisibility(View.VISIBLE);
        ImageView cancelNameIcon = (ImageView) findViewById(R.id.profileCancelNameIcon);
        cancelNameIcon.setVisibility(View.VISIBLE);
        ImageView editNameIcon = (ImageView) findViewById(R.id.profileEditNameIcon);
        editNameIcon.setVisibility(View.GONE);

        Button saveNameButton = (Button) findViewById(R.id.profileSaveNameButton);
        saveNameButton.setVisibility(View.VISIBLE);
        Button cancelNameButton = (Button) findViewById(R.id.profileCancelNameButton);
        cancelNameButton.setVisibility(View.VISIBLE);
        Button editNameButton = (Button) findViewById(R.id.profileEditNameButton);
        editNameButton.setVisibility(View.GONE);

        hideEditAllButton();
    }

    public void profileSaveName(View v) {



        EditText nameEditText = (EditText) findViewById(R.id.profileNameEditText);
        TextView nameTextView = (TextView) findViewById(R.id.profileNameTextView);
        String nameEditTextString = nameEditText.getText().toString();
        nameTextView.setText(nameEditTextString);
        // Can add an input check here
        nameEditText.setVisibility(View.GONE);
        nameTextView.setVisibility(View.VISIBLE);

        ImageView saveNameIcon = (ImageView) findViewById(R.id.profileSaveNameIcon);
        saveNameIcon.setVisibility(View.GONE);
        ImageView cancelNameIcon = (ImageView) findViewById(R.id.profileCancelNameIcon);
        cancelNameIcon.setVisibility(View.GONE);
        ImageView editNameIcon = (ImageView) findViewById(R.id.profileEditNameIcon);
        editNameIcon.setVisibility(View.VISIBLE);

        Button saveNameButton = (Button) findViewById(R.id.profileSaveNameButton);
        saveNameButton.setVisibility(View.GONE);
        Button cancelNameButton = (Button) findViewById(R.id.profileCancelNameButton);
        cancelNameButton.setVisibility(View.GONE);
        Button editNameButton = (Button) findViewById(R.id.profileEditNameButton);
        editNameButton.setVisibility(View.VISIBLE);

        showEditAllButton();
    }

    public void profileCancelName(View v) {

        EditText nameEditText = (EditText) findViewById(R.id.profileNameEditText);
        TextView nameTextView = (TextView) findViewById(R.id.profileNameTextView);
        String nameTextViewString = nameTextView.getText().toString();
        nameEditText.setText(nameTextViewString);
        // Can add an input check here
        nameEditText.setVisibility(View.GONE);
        nameTextView.setVisibility(View.VISIBLE);


        ImageView saveNameIcon = (ImageView) findViewById(R.id.profileSaveNameIcon);
        saveNameIcon.setVisibility(View.GONE);
        ImageView cancelNameIcon = (ImageView) findViewById(R.id.profileCancelNameIcon);
        cancelNameIcon.setVisibility(View.GONE);
        ImageView editNameIcon = (ImageView) findViewById(R.id.profileEditNameIcon);
        editNameIcon.setVisibility(View.VISIBLE);


        Button saveNameButton = (Button) findViewById(R.id.profileSaveNameButton);
        saveNameButton.setVisibility(View.GONE);
        Button cancelNameButton = (Button) findViewById(R.id.profileCancelNameButton);
        cancelNameButton.setVisibility(View.GONE);
        Button editNameButton = (Button) findViewById(R.id.profileEditNameButton);
        editNameButton.setVisibility(View.VISIBLE);

        showEditAllButton();
    }

    public void profileEditGender(View v) {

        EditText genderEditText = (EditText) findViewById(R.id.profileGenderEditText);
        TextView genderTextView = (TextView) findViewById(R.id.profileGenderTextView);
        // This might be redundant, consider removing
        String genderTextViewString = genderTextView.getText().toString();
        genderEditText.setText(genderTextViewString);
        // Can add an input check here
        genderEditText.setVisibility(View.VISIBLE);
        genderTextView.setVisibility(View.GONE);

        ImageView saveGenderIcon = (ImageView) findViewById(R.id.profileSaveGenderIcon);
        saveGenderIcon.setVisibility(View.VISIBLE);
        ImageView cancelGenderIcon = (ImageView) findViewById(R.id.profileCancelGenderIcon);
        cancelGenderIcon.setVisibility(View.VISIBLE);
        ImageView editGenderIcon = (ImageView) findViewById(R.id.profileEditGenderIcon);
        editGenderIcon.setVisibility(View.GONE);

        Button saveGenderButton = (Button) findViewById(R.id.profileSaveGenderButton);
        saveGenderButton.setVisibility(View.VISIBLE);
        Button cancelGenderButton = (Button) findViewById(R.id.profileCancelGenderButton);
        cancelGenderButton.setVisibility(View.VISIBLE);
        Button editGenderButton = (Button) findViewById(R.id.profileEditGenderButton);
        editGenderButton.setVisibility(View.GONE);

        hideEditAllButton();
    }

    public void profileSaveGender(View v) {



        EditText genderEditText = (EditText) findViewById(R.id.profileGenderEditText);
        TextView genderTextView = (TextView) findViewById(R.id.profileGenderTextView);
        String genderEditTextString = genderEditText.getText().toString();
        genderTextView.setText(genderEditTextString);
        // Can add an input check here
        genderEditText.setVisibility(View.GONE);
        genderTextView.setVisibility(View.VISIBLE);

        ImageView saveGenderIcon = (ImageView) findViewById(R.id.profileSaveGenderIcon);
        saveGenderIcon.setVisibility(View.GONE);
        ImageView cancelGenderIcon = (ImageView) findViewById(R.id.profileCancelGenderIcon);
        cancelGenderIcon.setVisibility(View.GONE);
        ImageView editGenderIcon = (ImageView) findViewById(R.id.profileEditGenderIcon);
        editGenderIcon.setVisibility(View.VISIBLE);

        Button saveGenderButton = (Button) findViewById(R.id.profileSaveGenderButton);
        saveGenderButton.setVisibility(View.GONE);
        Button cancelGenderButton = (Button) findViewById(R.id.profileCancelGenderButton);
        cancelGenderButton.setVisibility(View.GONE);
        Button editGenderButton = (Button) findViewById(R.id.profileEditGenderButton);
        editGenderButton.setVisibility(View.VISIBLE);

        showEditAllButton();
    }

    public void profileCancelGender(View v) {

        EditText genderEditText = (EditText) findViewById(R.id.profileGenderEditText);
        TextView genderTextView = (TextView) findViewById(R.id.profileGenderTextView);
        String genderTextViewString = genderTextView.getText().toString();
        genderEditText.setText(genderTextViewString);
        // Can add an input check here
        genderEditText.setVisibility(View.GONE);
        genderTextView.setVisibility(View.VISIBLE);


        ImageView saveGenderIcon = (ImageView) findViewById(R.id.profileSaveGenderIcon);
        saveGenderIcon.setVisibility(View.GONE);
        ImageView cancelGenderIcon = (ImageView) findViewById(R.id.profileCancelGenderIcon);
        cancelGenderIcon.setVisibility(View.GONE);
        ImageView editGenderIcon = (ImageView) findViewById(R.id.profileEditGenderIcon);
        editGenderIcon.setVisibility(View.VISIBLE);


        Button saveGenderButton = (Button) findViewById(R.id.profileSaveGenderButton);
        saveGenderButton.setVisibility(View.GONE);
        Button cancelGenderButton = (Button) findViewById(R.id.profileCancelGenderButton);
        cancelGenderButton.setVisibility(View.GONE);
        Button editGenderButton = (Button) findViewById(R.id.profileEditGenderButton);
        editGenderButton.setVisibility(View.VISIBLE);

        showEditAllButton();
    }

    public void profileEditDOB(View v) {

        EditText dobEditText = (EditText) findViewById(R.id.profileDOBEditText);
        TextView dobTextView = (TextView) findViewById(R.id.profileDOBTextView);
        // This might be redundant, consider removing
        String dobTextViewString = dobTextView.getText().toString();
        dobEditText.setText(dobTextViewString);
        // Can add an input check here
        dobEditText.setVisibility(View.VISIBLE);
        dobTextView.setVisibility(View.GONE);

        ImageView saveDOBIcon = (ImageView) findViewById(R.id.profileSaveDOBIcon);
        saveDOBIcon.setVisibility(View.VISIBLE);
        ImageView cancelDOBIcon = (ImageView) findViewById(R.id.profileCancelDOBIcon);
        cancelDOBIcon.setVisibility(View.VISIBLE);
        ImageView editDOBIcon = (ImageView) findViewById(R.id.profileEditDOBIcon);
        editDOBIcon.setVisibility(View.GONE);

        Button saveDOBButton = (Button) findViewById(R.id.profileSaveDOBButton);
        saveDOBButton.setVisibility(View.VISIBLE);
        Button cancelDOBButton = (Button) findViewById(R.id.profileCancelDOBButton);
        cancelDOBButton.setVisibility(View.VISIBLE);
        Button editDOBButton = (Button) findViewById(R.id.profileEditDOBButton);
        editDOBButton.setVisibility(View.GONE);

        hideEditAllButton();
    }

    public void profileSaveDOB(View v) {



        EditText dobEditText = (EditText) findViewById(R.id.profileDOBEditText);
        TextView dobTextView = (TextView) findViewById(R.id.profileDOBTextView);
        String dobEditTextString = dobEditText.getText().toString();
        dobTextView.setText(dobEditTextString);
        // Can add an input check here
        dobEditText.setVisibility(View.GONE);
        dobTextView.setVisibility(View.VISIBLE);

        ImageView saveDOBIcon = (ImageView) findViewById(R.id.profileSaveDOBIcon);
        saveDOBIcon.setVisibility(View.GONE);
        ImageView cancelDOBIcon = (ImageView) findViewById(R.id.profileCancelDOBIcon);
        cancelDOBIcon.setVisibility(View.GONE);
        ImageView editDOBIcon = (ImageView) findViewById(R.id.profileEditDOBIcon);
        editDOBIcon.setVisibility(View.VISIBLE);

        Button saveDOBButton = (Button) findViewById(R.id.profileSaveDOBButton);
        saveDOBButton.setVisibility(View.GONE);
        Button cancelDOBButton = (Button) findViewById(R.id.profileCancelDOBButton);
        cancelDOBButton.setVisibility(View.GONE);
        Button editDOBButton = (Button) findViewById(R.id.profileEditDOBButton);
        editDOBButton.setVisibility(View.VISIBLE);

        showEditAllButton();
    }

    public void profileCancelDOB(View v) {

        EditText dobEditText = (EditText) findViewById(R.id.profileDOBEditText);
        TextView dobTextView = (TextView) findViewById(R.id.profileDOBTextView);
        String dobTextViewString = dobTextView.getText().toString();
        dobEditText.setText(dobTextViewString);
        // Can add an input check here
        dobEditText.setVisibility(View.GONE);
        dobTextView.setVisibility(View.VISIBLE);


        ImageView saveDOBIcon = (ImageView) findViewById(R.id.profileSaveDOBIcon);
        saveDOBIcon.setVisibility(View.GONE);
        ImageView cancelDOBIcon = (ImageView) findViewById(R.id.profileCancelDOBIcon);
        cancelDOBIcon.setVisibility(View.GONE);
        ImageView editDOBIcon = (ImageView) findViewById(R.id.profileEditDOBIcon);
        editDOBIcon.setVisibility(View.VISIBLE);


        Button saveDOBButton = (Button) findViewById(R.id.profileSaveDOBButton);
        saveDOBButton.setVisibility(View.GONE);
        Button cancelDOBButton = (Button) findViewById(R.id.profileCancelDOBButton);
        cancelDOBButton.setVisibility(View.GONE);
        Button editDOBButton = (Button) findViewById(R.id.profileEditDOBButton);
        editDOBButton.setVisibility(View.VISIBLE);

        showEditAllButton();
    }


    public void profileEditAll(View v) {
        EditText nameEditText = (EditText) findViewById(R.id.profileNameEditText);
        TextView nameTextView = (TextView) findViewById(R.id.profileNameTextView);
        // This might be redundant, consider removing
        String nameTextViewString = nameTextView.getText().toString();
        nameEditText.setText(nameTextViewString);
        // Can add an input check here
        nameEditText.setVisibility(View.VISIBLE);
        nameTextView.setVisibility(View.GONE);
        EditText genderEditText = (EditText) findViewById(R.id.profileGenderEditText);
        TextView genderTextView = (TextView) findViewById(R.id.profileGenderTextView);
        // This might be redundant, consider removing
        String genderTextViewString = genderTextView.getText().toString();
        genderEditText.setText(genderTextViewString);
        // Can add an input check here
        genderEditText.setVisibility(View.VISIBLE);
        genderTextView.setVisibility(View.GONE);
        EditText dobEditText = (EditText) findViewById(R.id.profileDOBEditText);
        TextView dobTextView = (TextView) findViewById(R.id.profileDOBTextView);
        // This might be redundant, consider removing
        String dobTextViewString = dobTextView.getText().toString();
        dobEditText.setText(dobTextViewString);
        // Can add an input check here
        dobEditText.setVisibility(View.VISIBLE);
        dobTextView.setVisibility(View.GONE);

        ImageView editNameIcon = (ImageView) findViewById(R.id.profileEditNameIcon);
        editNameIcon.setVisibility(View.GONE);
        ImageView editGenderIcon = (ImageView) findViewById(R.id.profileEditGenderIcon);
        editGenderIcon.setVisibility(View.GONE);
        ImageView editDOBIcon = (ImageView) findViewById(R.id.profileEditDOBIcon);
        editDOBIcon.setVisibility(View.GONE);

        Button editNameButton = (Button) findViewById(R.id.profileEditNameButton);
        editNameButton.setVisibility(View.GONE);
        Button editGenderButton = (Button) findViewById(R.id.profileEditGenderButton);
        editGenderButton.setVisibility(View.GONE);
        Button editDOBButton = (Button) findViewById(R.id.profileEditDOBButton);
        editDOBButton.setVisibility(View.GONE);

        ImageView saveAllIcon = (ImageView) findViewById(R.id.profileSaveAllIcon);
        saveAllIcon.setVisibility(View.VISIBLE);
        ImageView cancelAllIcon = (ImageView) findViewById(R.id.profileCancelAllIcon);
        cancelAllIcon.setVisibility(View.VISIBLE);
        ImageView editAllIcon = (ImageView) findViewById(R.id.profileEditAllIcon);
        editAllIcon.setVisibility(View.INVISIBLE);
        Button saveAllButton = (Button) findViewById(R.id.profileSaveAllButton);
        saveAllButton.setVisibility(View.VISIBLE);
        Button cancelAllButton = (Button) findViewById(R.id.profileCancelAllButton);
        cancelAllButton.setVisibility(View.VISIBLE);
        Button editAllButton = (Button) findViewById(R.id.profileEditAllButton);
        editAllButton.setVisibility(View.GONE);





    }

    public void profileSaveAll(View v) {



        EditText nameEditText = (EditText) findViewById(R.id.profileNameEditText);
        TextView nameTextView = (TextView) findViewById(R.id.profileNameTextView);
        // This might be redundant, consider removing
        String nameEditTextString = nameEditText.getText().toString();
        nameTextView.setText(nameEditTextString);
        // Can add an input check here
        nameEditText.setVisibility(View.GONE);
        nameTextView.setVisibility(View.VISIBLE);
        EditText genderEditText = (EditText) findViewById(R.id.profileGenderEditText);
        TextView genderTextView = (TextView) findViewById(R.id.profileGenderTextView);
        // This might be redundant, consider removing
        String genderEditTextString = genderEditText.getText().toString();
        genderTextView.setText(genderEditTextString);
        // Can add an input check here
        genderEditText.setVisibility(View.GONE);
        genderTextView.setVisibility(View.VISIBLE);
        EditText dobEditText = (EditText) findViewById(R.id.profileDOBEditText);
        TextView dobTextView = (TextView) findViewById(R.id.profileDOBTextView);
        // This might be redundant, consider removing
        String dobEditTextString = dobEditText.getText().toString();
        dobTextView.setText(dobEditTextString);
        // Can add an input check here
        dobEditText.setVisibility(View.GONE);
        dobTextView.setVisibility(View.VISIBLE);


        ImageView editNameIcon = (ImageView) findViewById(R.id.profileEditNameIcon);
        editNameIcon.setVisibility(View.VISIBLE);
        ImageView editGenderIcon = (ImageView) findViewById(R.id.profileEditGenderIcon);
        editGenderIcon.setVisibility(View.VISIBLE);
        ImageView editDOBIcon = (ImageView) findViewById(R.id.profileEditDOBIcon);
        editDOBIcon.setVisibility(View.VISIBLE);

        Button editNameButton = (Button) findViewById(R.id.profileEditNameButton);
        editNameButton.setVisibility(View.VISIBLE);
        Button editGenderButton = (Button) findViewById(R.id.profileEditGenderButton);
        editGenderButton.setVisibility(View.VISIBLE);
        Button editDOBButton = (Button) findViewById(R.id.profileEditDOBButton);
        editDOBButton.setVisibility(View.VISIBLE);



        ImageView saveAllIcon = (ImageView) findViewById(R.id.profileSaveAllIcon);
        saveAllIcon.setVisibility(View.GONE);
        ImageView cancelAllIcon = (ImageView) findViewById(R.id.profileCancelAllIcon);
        cancelAllIcon.setVisibility(View.GONE);
        ImageView editAllIcon = (ImageView) findViewById(R.id.profileEditAllIcon);
        editAllIcon.setVisibility(View.VISIBLE);
        Button saveAllButton = (Button) findViewById(R.id.profileSaveAllButton);
        saveAllButton.setVisibility(View.GONE);
        Button cancelAllButton = (Button) findViewById(R.id.profileCancelAllButton);
        cancelAllButton.setVisibility(View.GONE);
        Button editAllButton = (Button) findViewById(R.id.profileEditAllButton);
        editAllButton.setVisibility(View.VISIBLE);
    }

    public void profileCancelAll(View v) {

        EditText nameEditText = (EditText) findViewById(R.id.profileNameEditText);
        TextView nameTextView = (TextView) findViewById(R.id.profileNameTextView);
        // This might be redundant, consider removing
        String nameTextViewString = nameTextView.getText().toString();
        nameEditText.setText(nameTextViewString);
        // Can add an input check here
        nameEditText.setVisibility(View.GONE);
        nameTextView.setVisibility(View.VISIBLE);
        EditText genderEditText = (EditText) findViewById(R.id.profileGenderEditText);
        TextView genderTextView = (TextView) findViewById(R.id.profileGenderTextView);
        // This might be redundant, consider removing
        String genderTextViewString = genderTextView.getText().toString();
        genderEditText.setText(genderTextViewString);
        // Can add an input check here
        genderEditText.setVisibility(View.GONE);
        genderTextView.setVisibility(View.VISIBLE);
        EditText dobEditText = (EditText) findViewById(R.id.profileDOBEditText);
        TextView dobTextView = (TextView) findViewById(R.id.profileDOBTextView);
        // This might be redundant, consider removing
        String dobTextViewString = dobTextView.getText().toString();
        dobEditText.setText(dobTextViewString);
        // Can add an input check here
        dobEditText.setVisibility(View.GONE);
        dobTextView.setVisibility(View.VISIBLE);

        ImageView editNameIcon = (ImageView) findViewById(R.id.profileEditNameIcon);
        editNameIcon.setVisibility(View.VISIBLE);
        ImageView editGenderIcon = (ImageView) findViewById(R.id.profileEditGenderIcon);
        editGenderIcon.setVisibility(View.VISIBLE);
        ImageView editDOBIcon = (ImageView) findViewById(R.id.profileEditDOBIcon);
        editDOBIcon.setVisibility(View.VISIBLE);

        Button editNameButton = (Button) findViewById(R.id.profileEditNameButton);
        editNameButton.setVisibility(View.VISIBLE);
        Button editGenderButton = (Button) findViewById(R.id.profileEditGenderButton);
        editGenderButton.setVisibility(View.VISIBLE);
        Button editDOBButton = (Button) findViewById(R.id.profileEditDOBButton);
        editDOBButton.setVisibility(View.VISIBLE);



        ImageView saveAllIcon = (ImageView) findViewById(R.id.profileSaveAllIcon);
        saveAllIcon.setVisibility(View.GONE);
        ImageView cancelAllIcon = (ImageView) findViewById(R.id.profileCancelAllIcon);
        cancelAllIcon.setVisibility(View.GONE);
        ImageView editAllIcon = (ImageView) findViewById(R.id.profileEditAllIcon);
        editAllIcon.setVisibility(View.VISIBLE);
        Button saveAllButton = (Button) findViewById(R.id.profileSaveAllButton);
        saveAllButton.setVisibility(View.GONE);
        Button cancelAllButton = (Button) findViewById(R.id.profileCancelAllButton);
        cancelAllButton.setVisibility(View.GONE);
        Button editAllButton = (Button) findViewById(R.id.profileEditAllButton);
        editAllButton.setVisibility(View.VISIBLE);
    }

    public void hideEditAllButton() {
        ImageView editAllIcon = (ImageView) findViewById(R.id.profileEditAllIcon);
        editAllIcon.setVisibility(View.GONE);
        Button editAllButton = (Button) findViewById(R.id.profileEditAllButton);
        editAllButton.setVisibility(View.GONE);
    }

    public void showEditAllButton() {
        ImageView editAllIcon = (ImageView) findViewById(R.id.profileEditAllIcon);
        editAllIcon.setVisibility(View.VISIBLE);
        Button editAllButton = (Button) findViewById(R.id.profileEditAllButton);
        editAllButton.setVisibility(View.VISIBLE);
    }

}
