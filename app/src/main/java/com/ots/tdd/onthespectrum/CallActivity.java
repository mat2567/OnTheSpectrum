package com.ots.tdd.onthespectrum;

import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;

import com.ots.tdd.onthespectrum.R;
import com.ots.tdd.onthespectrum.reference.fragment.CallFragment;
import com.ots.tdd.onthespectrum.reference.intent.BWIntent;
import com.ots.tdd.onthespectrum.reference.utils.NotificationHelper;


// Don't use this
public class CallActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);

            broadcastManager.sendBroadcast(getIntent());

            NotificationHelper.clearIncomingCallNotification(this);

            if (getIntent().getAction().equals(BWIntent.DECLINE_CALL)) {
                finish();
                overridePendingTransition(R.animator.animation_none, R.animator.animation_none);
            } else {
                setContentView(R.layout.activity_main);
                CallFragment callFragment = new CallFragment();
                callFragment.setPhoneNumber(getIntent().getStringExtra(BWIntent.PHONE_CALL));
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, callFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onBackPressed() {
    }
}
