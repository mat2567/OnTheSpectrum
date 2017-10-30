package com.ots.tdd.onthespectrum;

import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ethan on 10/29/2017.
 */

public class CallLogElementViewContainer {
    TextView callLogDate;
    TextView callLogTime;
    TextView callLogScenario;

    static ArrayList<CallLogElementViewContainer> callLogElements= new ArrayList<CallLogElementViewContainer>();

    public CallLogElementViewContainer(TextView callLogDate, TextView callLogTime, TextView callLogScenario) {
        this.callLogDate = callLogDate;
        this.callLogTime = callLogTime;
        this.callLogScenario = callLogScenario;
    }

    public static TextView getDate(CallLogElementViewContainer clevc) {
        return clevc.callLogDate;
    }

    public static TextView getTime(CallLogElementViewContainer clevc) {
        return clevc.callLogTime;
    }

    public static TextView getScenario(CallLogElementViewContainer clevc) {
        return clevc.callLogScenario;
    }

    public static void addCLEVCToArray(CallLogElementViewContainer clevc) {
        callLogElements.add(clevc);
    }

    public static int getArraySize() {
        return callLogElements.size();
    }

    public static CallLogElementViewContainer get(int i) {
        if (i < getArraySize()) {
            return callLogElements.get(i);
        } else {
            return null;
        }
    }
}
