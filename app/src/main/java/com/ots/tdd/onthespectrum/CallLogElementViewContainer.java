package com.ots.tdd.onthespectrum;

import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ethan on 10/29/2017.
 */

/**
 * Container holding information related to the view each entry in the call log
 * used for the CallLogActivity's ListView
 */
public class CallLogElementViewContainer {
    TextView callLogDate;
    TextView callLogTime;
    TextView callLogScenario;

    static ArrayList<CallLogElementViewContainer> callLogElements= new ArrayList<CallLogElementViewContainer>();

    /**
     * Constructor taking in TextViews containing the entry's date, time, and scenario
     *
     * @param  callLogDate a TextView showing date information
     * @param  callLogTime a TextView showing time information
     * @param  callLogScenario a TextView showing scenario information
     */
    public CallLogElementViewContainer(TextView callLogDate, TextView callLogTime, TextView callLogScenario) {
        this.callLogDate = callLogDate;
        this.callLogTime = callLogTime;
        this.callLogScenario = callLogScenario;
    }

    /**
     * Gets the TextView containing the date information of the call log entry
     *
     * @param clevc CallLogElementViewContainer which contains the desired TextView
     * @return callLogDate a TextView showing date information
     */
    public static TextView getDate(CallLogElementViewContainer clevc) {
        return clevc.callLogDate;
    }

    /**
     * Gets the TextView containing the time information of the call log entry
     *
     * @param clevc CallLogElementViewContainer which contains the desired TextView
     * @return callLogDate a TextView showing time information
     */
    public static TextView getTime(CallLogElementViewContainer clevc) {
        return clevc.callLogTime;
    }

    /**
     * Gets the TextView containing the scenario information of the call log entry
     *
     * @param clevc CallLogElementViewContainer which contains the desired TextView
     * @return callLogDate a TextView showing scenario information
     */
    public static TextView getScenario(CallLogElementViewContainer clevc) {
        return clevc.callLogScenario;
    }

    /**
     * Adds a CallLogElementViewContainer to the static ArrayList
     *
     * @param clevc CallLogElementViewContainer which is to be added to the ArrayList
     */
    public static void addCLEVCToArray(CallLogElementViewContainer clevc) {
        callLogElements.add(clevc);
    }

    /**
     * Returns the size of the static Arraylist of CallLogELementViewContainers
     *
     * @return size the size of the static ArrayList
     */
    public static int getArraySize() {
        return callLogElements.size();
    }

    /**
     * Indexes into the static ArrayList of CallLogElementViewContainers
     *
     * @param i index of the CallLogElementViewContainer to be retrieved
     * @return CLEVC the indexed CallLogElementViewContainer
     */
    public static CallLogElementViewContainer get(int i) {
        if (i < getArraySize()) {
            return callLogElements.get(i);
        } else {
            return null;
        }
    }
}
