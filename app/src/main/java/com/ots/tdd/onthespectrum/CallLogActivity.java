package com.ots.tdd.onthespectrum;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CallLogActivity extends AppCompatActivity {

    ArrayAdapter<CallLogElement> adapter;

    static ArrayList<CallLogElement> callLogList = new ArrayList<>();

    int callLogElementCounter = 0;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getApplicationContext().getSharedPreferences("OnTheSpectrum", Context.MODE_PRIVATE);
        int theme = sharedPref.getInt("colorTheme", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

//        if (callLogList.isEmpty()) {
//            loadInfo();
//        }
        loadInfo();

        final int titleSize = sharedPref.getInt("TitleFontSize", 0);
        final int bodySize = sharedPref.getInt("BodyFontSize", 0);
        final int fontChange = sharedPref.getInt("FontSizeChange", 0);
        TextView title = (TextView) findViewById(R.id.callLogTitle);
        title.setTextSize(titleSize + fontChange);

        /*Backs the ListView. Enables TextViews to be added dynamically */
        adapter=new ArrayAdapter<CallLogElement>(this, R.layout.call_log_item, callLogList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                CallLogElement current = callLogList.get(position);

                // Inflate only once
                if(convertView == null) {
                    convertView = getLayoutInflater()
                            .inflate(R.layout.call_log_item, null, false);
                }

                CallLogElementViewContainer currCLEVC = null;
                currCLEVC = CallLogElementViewContainer.get(position);
                if (currCLEVC == null) {
                    TextView dateLabel = (TextView) convertView.findViewById(R.id.dateLabel);
                    TextView timeLabel = (TextView) convertView.findViewById(R.id.timeLabel);
                    TextView emergencyLabel = (TextView) convertView.findViewById(R.id.emergencyLabel);
                    TextView callLogDate = (TextView) convertView.findViewById(R.id.callLogDate);
                    TextView callLogTime = (TextView) convertView.findViewById(R.id.callLogTime);
                    TextView callLogScenario = (TextView) convertView.findViewById(R.id.callLogScenario);

                    CallLogElementViewContainer newCallLogElement = new CallLogElementViewContainer(
                            callLogDate, callLogTime, callLogScenario);
                    CallLogElementViewContainer.addCLEVCToArray(newCallLogElement);

                    callLogDate.setText(current.getDate());
                    callLogTime.setText(current.getTime());
                    callLogScenario.setText(current.getScenario());

                    dateLabel.setTextSize(bodySize + fontChange);
                    timeLabel.setTextSize(bodySize + fontChange);
                    emergencyLabel.setTextSize(bodySize + fontChange);
                    callLogDate.setTextSize(bodySize + fontChange);
                    callLogTime.setTextSize(bodySize + fontChange);
                    callLogScenario.setTextSize(bodySize + fontChange);
                } else {
                    TextView dateLabel = (TextView) convertView.findViewById(R.id.dateLabel);
                    TextView timeLabel = (TextView) convertView.findViewById(R.id.timeLabel);
                    TextView emergencyLabel = (TextView) convertView.findViewById(R.id.emergencyLabel);
                    TextView callLogDate = (TextView) convertView.findViewById(R.id.callLogDate);
                    TextView callLogTime = (TextView) convertView.findViewById(R.id.callLogTime);
                    TextView callLogScenario = (TextView) convertView.findViewById(R.id.callLogScenario);

                    callLogDate.setText(CallLogElementViewContainer.getDate(currCLEVC).getText());
                    callLogTime.setText(CallLogElementViewContainer.getTime(currCLEVC).getText());
                    callLogScenario.setText(CallLogElementViewContainer.getScenario(currCLEVC).getText());

                    dateLabel.setTextSize(bodySize + fontChange);
                    timeLabel.setTextSize(bodySize + fontChange);
                    emergencyLabel.setTextSize(bodySize + fontChange);
                    callLogDate.setTextSize(bodySize + fontChange);
                    callLogTime.setTextSize(bodySize + fontChange);
                    callLogScenario.setTextSize(bodySize + fontChange);
                }

                return convertView;
            }
        };
        ListView listV=(ListView)findViewById(R.id.callLogListView);
        listV.setAdapter(adapter);

    }

    protected void onPause() {
        super.onPause();
        if (callLogList != null && callLogList.size() > 0) {
            SharedPreferences.Editor editor = sharedPref.edit();
            String callLogEntries = "";
            for (CallLogElement item : callLogList) {
                callLogEntries += item.getDate() + "~" + item.getTime() + "~" + item.getScenario();
                callLogEntries += ";;";
                //editor.putString(item.getTitle(), item.imageMemLocation);
            }
            editor.putString("CallLog", callLogEntries);

            editor.apply();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void loadInfo() {
        callLogList.clear();
        String savedCallLog = sharedPref.getString("CallLog", null);
        if (null == savedCallLog) {
            // This is a default list of test logs. Delete later for empty log.
            /*callLogList.add(new CallLogElement("January 1, 2016", "11:30 AM (Eastern Standard Time)", "Break In"));
            callLogList.add(new CallLogElement("December 12, 2016", "06:12 PM (Eastern Standard Time)", "Fire"));
            callLogList.add(new CallLogElement("March 7, 2017", "01:24 AM (Eastern Standard Time)", "Lost"));*/
        } else {
            String[] calls = savedCallLog.split(";;");
            for (int i = 0; i < calls.length; i++) {
                String[] callInfo = calls[i].split("~");
                //String info = sharedPref.getString(fields[i], "");
                //itemList.add(new ProfileElement(fields[i], info, i));
                callLogList.add(new CallLogElement(callInfo[0], callInfo[1], callInfo[2]));
            }
        }
        callLogElementCounter = callLogList.size();
    }

    public void showSharedPreferences(View v) {
        String savedCallLog = sharedPref.getString("CallLog", null);

        /*String testString = savedCallLog + "~~";
        String[] calls = savedCallLog.split(";;");
        for (int i = 0; i < calls.length; i++) {
            String[] callInfo = calls[i].split("~");
            //String info = sharedPref.getString(fields[i], "");
            //itemList.add(new ProfileElement(fields[i], info, i));
            testString += callInfo[0] + "+" + callInfo[1] + "+" + callInfo[2]  + "=";
        }
        testString = testString + " " + calls.length;

        displayExceptionMessage(testString);*/

        displayExceptionMessage(savedCallLog);
    }

    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
