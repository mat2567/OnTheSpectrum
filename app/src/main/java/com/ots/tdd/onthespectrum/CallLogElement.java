package com.ots.tdd.onthespectrum;

/**
 * Created by Ethan on 10/29/2017.
 */

public class CallLogElement {
    private String scenario;
    private String date;
    private String time;

    public CallLogElement(String date, String time, String scenario) {
        this.scenario = scenario;
        this.date = date;
        this.time = time;
    }

    public String getScenario() {
        return scenario;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
