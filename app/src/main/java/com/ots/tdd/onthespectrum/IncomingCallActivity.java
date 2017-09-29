package com.ots.tdd.onthespectrum;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.ots.tdd.onthespectrum.R;
import com.ots.tdd.onthespectrum.reference.fragment.IncomingCallFragment;
import com.ots.tdd.onthespectrum.reference.intent.BWIntent;

// Don't use this
public class IncomingCallActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            IncomingCallFragment incomingCallFragment = new IncomingCallFragment();
            incomingCallFragment.setFromNumber(intent.getStringExtra(BWIntent.INCOMING_CALL));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, incomingCallFragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
    }
}
