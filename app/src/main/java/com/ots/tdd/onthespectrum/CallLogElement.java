package com.ots.tdd.onthespectrum;

/**
 * Created by Ethan on 10/29/2017.
 */

/**
 * Container holding information related to each entry in the call log
 */
public class CallLogElement {
    private String scenario;
    private String date;
    private String time;

    /**
     * Constructor taking in the entry's date, time, and scenario
     *
     * @param  date a formatted String containing date information
     * @param  time a formatted String containing time information
     * @param  scenario a formatted String containing scenario information
     */
    public CallLogElement(String date, String time, String scenario) {
        this.scenario = scenario;
        this.date = date;
        this.time = time;
    }

    /**
     * Gets the scenario information of the call log entry
     *
     * @return scenario a formatted String containing scenario information
     */
    public String getScenario() {
        return scenario;
    }

    /**
     * Gets the date information of the call log entry
     *
     * @return date a formatted String containing date information
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the time information of the call log entry
     *
     * @return time a formatted String containing time information
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the scenario information of the call log entry
     *
     * @param scenario a formatted String containing scenario information
     */
    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    /**
     * Sets the date information of the call log entry
     *
     * @param date a formatted String containing date information
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the time information of the call log entry
     *
     * @param time a formatted String containing time information
     */
    public void setTime(String time) {
        this.time = time;
    }
}
