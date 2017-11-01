package com.ots.tdd.onthespectrum;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.sql.SQLOutput;

import static android.support.constraint.R.id.parent;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "Settings Activity";
    int fontSize = 12;
    boolean locked = false;
    boolean wantTo = false;
    String currentHint = "";

    Switch lock = null;
    EditText passwordEditText = null;
    Button lockButton = null;
    static boolean isLocked = false;
    String password = "";


    /**
     * Loads the information for password/profile lock and sets up the lock, font size, and colors.
     * @param  savedInstanceState Bundle
     * @see android.app.Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lock = (Switch) findViewById(R.id.lock);
        passwordEditText = (EditText) findViewById(R.id.password);
        lockButton = (Button) findViewById(R.id.lockButton);
        lock.setEnabled(false);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        String prevPassword = sharedPref.getString("Lock", null);
        if (null == prevPassword) {
            updateSharedPreferences("");
            password = "";
            lockButton.setText("Lock");
            lock.setChecked(false);
            isLocked = false;
        } else {
            if (prevPassword.equals("")) {
                password = "";
                lockButton.setText("Lock");
                lock.setChecked(false);
                isLocked = false;
            } else {
                password = prevPassword;
                lockButton.setText("Unlock");
                lock.setChecked(true);
                isLocked = true;
            }
        }

        //spinner stuff for font size
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fontSizeAttempt, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int pos, long id){
                String fontString = parent.getItemAtPosition(pos).toString();
                int fontSize = Integer.parseInt(fontString);
                System.out.println(fontSize);
                TextView a = (TextView) findViewById(R.id.AdjustColorText);
                a.setTextSize(fontSize);
                a =(TextView) findViewById(R.id.FontSizeText);
                a.setTextSize(fontSize);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        //lock button
        lockButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (password.equals("")) { // meaning password not yet set
                    if (passwordEditText.getText().toString().equals("")) {
                        displayExceptionMessage("Provide a Password");
                    } else {
                        password = passwordEditText.getText().toString();
                        isLocked = true;
                        lockButton.setText("Unlock");
                        passwordEditText.setText("");
                        updateSharedPreferences(password);
                        displayExceptionMessage("Customization Locked");
                    }
                } else {
                    if (passwordEditText.getText().toString().equals(password)) {
                        isLocked = false;
                        lockButton.setText("Lock");
                        passwordEditText.setText("");
                        updateSharedPreferences("");
                        password = "";

                        displayExceptionMessage("Customization Unlocked");
                    } else { //wrong password don't set
                        passwordEditText.setText("");
                        isLocked = true;

                        displayExceptionMessage("Incorrect Password");
                    }

                }
                lock.setChecked(isLocked);
            }
        });
    }


    /**
     * Updates the password saved on the device in SharedPreferences
     *
     * @param  password String of the newly saved password
     */
    public void updateSharedPreferences(String password) {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Lock", password);
        editor.commit();
    }


    /**
     * Displays the profile lock information change as a toast message ot the user.
     *
     * @param  msg String with information about the profile lock
     */
    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
