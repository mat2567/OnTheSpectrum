package com.ots.tdd.onthespectrum.reference.intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ots.tdd.onthespectrum.reference.CallService;

public class Autostart extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, CallService.class);
        context.startService(serviceIntent);
    }
}