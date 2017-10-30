package com.ots.tdd.onthespectrum;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import org.w3c.dom.Text;

import java.sql.SQLOutput;

import static android.support.constraint.R.id.parent;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "Settings Activity";
    int fontChange;
    boolean locked = false;
    boolean wantTo = false;
    String password = "";
    String currentHint = "";
    TextView settingsText;
    TextView appearanceText;
    TextView profileLockText;
    TextView fontSizeText;
    TextView adjustColorText;

    public int titleText = 30;
    public int subtitleTextSize = 18;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsText = (TextView) findViewById(R.id.Settings);
        appearanceText = (TextView) findViewById(R.id.appearance);
        profileLockText = (TextView) findViewById(R.id.profileLockText);
        fontSizeText = (TextView) findViewById(R.id.FontSizeText);
        adjustColorText = (TextView) findViewById(R.id.AdjustColorText);

        //font size
        //default is fontChange of 0 unless specified in SharedPreferences
        RadioGroup fontRadios = (RadioGroup) findViewById(R.id.fontSizeChoice);

        sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        int fontSharedPref = sharedPref.getInt("FontSizeChange", Integer.MAX_VALUE);
        if (fontSharedPref == Integer.MAX_VALUE) {
            fontChange = 0;
            ((RadioButton)fontRadios.getChildAt(1)).setChecked(true);
        } else {
            fontChange = fontSharedPref;
            if (fontChange < 0) {
                ((RadioButton)fontRadios.getChildAt(0)).setChecked(true);
            } else if (fontChange == 0) {
                ((RadioButton)fontRadios.getChildAt(1)).setChecked(true);
            } else {
                ((RadioButton)fontRadios.getChildAt(2)).setChecked(true);
            }
        }
        setFontSizes();

        //switch stuff for profile lock
        final Switch switched = (Switch) findViewById(R.id.lock);
        switched.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                switched.toggle();
                if (isChecked) {

                    Log.i(TAG, "checked");
                    wantTo = true;
                    EditText passwordEditText = (EditText) findViewById(R.id.password);
                    if (password.equals("")) {
                        passwordEditText.setHint("Enter new password and press OK");
                    } else {
                        passwordEditText.setHint("Enter password to change");
                    }

                } else {
                    Log.i(TAG, "unchecked");
                    wantTo = false;
//                    locked = false;
                    EditText passwordEditText = (EditText) findViewById(R.id.password);
                    passwordEditText.setHint("Enter password to change");
                }

                switched.setChecked(locked); //added to prevent it from doing it unnecessary
            }
        });

        //okay button
        Button button = (Button) findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                EditText passwordEditText = (EditText) findViewById(R.id.password);
                if (password.equals("")) { // meaning password not yet set
//                    System.out.println("setting password");
                    Log.i(TAG, "setting password");
                    password = passwordEditText.getText().toString();
                    locked = true;

                } else {
                    if (passwordEditText.getText().toString().equals(password)) {
                        Log.i(TAG, "password correct");
                        locked = wantTo;

                    } else { //wrong password don't set
                        Log.i(TAG, "password incorrect");
                        locked = !wantTo;
                    }

                    //then
//                    if (locked) { //= true
//                    }
                }
                //then
                System.out.println("wantTo: " + wantTo + " switch: " + switched.isChecked());
                switched.setChecked(locked);
                System.out.println("locked: " + locked + " switch: " + switched.isChecked());
                passwordEditText.setText("");
                passwordEditText.setHint("");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("FontSizeChange", fontChange);
        editor.commit();
    }

    public void onOkayPress() {
        System.out.println("okay pressed");

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        String fontString = parent.getItemAtPosition(pos).toString();

        int fontSize = Integer.parseInt(fontString);
        System.out.println(fontSize);
        TextView a = (TextView) findViewById(R.id.AdjustColorText);
        a.setTextSize(fontSize);
        a = (TextView) findViewById(R.id.FontSizeText);
        a.setTextSize(fontSize);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?t
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        if (checked) {
            switch(view.getId()) {
                case R.id.smallRadio:
                    fontChange = -2;
                    break;
                case R.id.mediumRadio:
                    fontChange = 0;
                    break;
                case R.id.largeRadio:
                    fontChange = 4;
                    break;
            }

            setFontSizes();
        }

    }

    private void setFontSizes() {
        settingsText.setTextSize(titleText + fontChange);
        appearanceText.setTextSize(titleText + fontChange);

        profileLockText.setTextSize(subtitleTextSize + fontChange);
        fontSizeText.setTextSize(subtitleTextSize + fontChange);
        adjustColorText.setTextSize(subtitleTextSize + fontChange);
    }
}
