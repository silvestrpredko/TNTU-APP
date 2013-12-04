package ua.edu.tntu.schedule;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import ua.edu.tntu.R;

/**
 * Created by silvestr on 12/3/13.
 */
public class ScheduleXMLResourceParser {

    private Context context;
    private static final String TAG = "myLogs";
    private static final String Sp = " СЕ-21\n";
    private static final String subSp = " 2\n";
    private String name;
    private String nameWeek;

    private boolean[] switchWeek = new boolean[2];

    // constructor for  to get the context object from where you are using this plist parsing
    public ScheduleXMLResourceParser(Context context) {

        context = context;
        switchWeek[0] = false;
        switchWeek[1] = false;
    }

    public void getSchedule() {

        // specifying the  your plist file.And Xml ResourceParser is an event type parser for more details Read android source
        XmlResourceParser parser = context.getResources()
                .getXml(R.xml.schedule);
        boolean tempGroup = false;
        boolean tempSubGroup = false;
        boolean tempWeek = false;


        boolean switchGroup = false;
        boolean switchSubGroup = false;
        boolean closeSwitchSubGroup = false;

        int event;
        try {
            event = parser.getEventType();

            // repeting the loop at the end of the doccument

            while (event != parser.END_DOCUMENT) {

                if (event == XmlResourceParser.START_TAG) {
                    if (parser.getName().equals("group")) {
                        tempGroup = true;
                    }
                    if (parser.getName().equals("subgroup") && switchGroup) {
                        tempSubGroup = true;
                    }
                    if (parser.getName().equals("week") && switchSubGroup) {
                        tempWeek = true;
                        Log.d(TAG, "Name: " + parser.getName());
                    }
                }


                if (event == XmlResourceParser.TEXT) {
                    name = parser.getText();
                    if (tempGroup && name.equals(Sp)) {
                        switchGroup = true;
                    }
                    if (tempSubGroup && name.equals(subSp)) {
                        switchSubGroup = true;
                        closeSwitchSubGroup = true;
                    }

                    if (tempWeek && name.equals(" first\n")) {
                        nameWeek = name;
                        switchWeek[0] = true;
                        tempWeek = false;
                    }
                    if (tempWeek && name.equals(" second\n")) {
                        nameWeek = name;
                        switchWeek[1] = true;
                        tempWeek = false;
                    }
                    if (switchGroup && switchSubGroup) {
                        if (switchWeek[0]) {
                            Log.d(TAG, "Text: " + parser.getText());
                        }
                        if (switchWeek[1]) {
                            Log.d(TAG, "Text: " + parser.getText());
                        }
                    }
                }

                if (event == XmlResourceParser.END_TAG && closeSwitchSubGroup) {
                    if (parser.getName().equals("subgroup")) {
                        break;
                    }
                    if (parser.getName().equals("week")) {
                        if (nameWeek.equals(" first\n")) {
                            switchWeek[0] = false;
                        }
                        if (nameWeek.equals(" second\n")) {
                            switchWeek[1] = false;
                        }
                    }
                }
                event = parser.next();

            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
