package com.example.horaire.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.horaire.AutoScheduledUsersToShifts;

public class CallService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentAttributions = new Intent(context, AutoScheduledUsersToShifts.class);
        context.startService(intentAttributions);
    }
}
