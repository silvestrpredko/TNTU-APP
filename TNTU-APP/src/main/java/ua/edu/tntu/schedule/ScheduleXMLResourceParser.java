package ua.edu.tntu.schedule;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import ua.edu.tntu.R;

/**
 * Created by silvestr on 12/3/13.
 */
public class ScheduleXMLResourceParser {

    private Context context;
    private static String TAG = "myLogs";
    private static final String Sp = " СІ-21\n";
    private static final String subSp = " 2\n";

    private String nameLecture;
    private String nameDay;
    private String nameLocation;
    private String nameStsrtTime;
    private String nameTimeEnd;

    private String name;
    private String nameWeek;
    private String nameTag;
    private String temp;
    private ArrayList<ScheduleBlok> scheduleBlokContainer;

    private boolean[] switchWeek = new boolean[2];

    // constructor for  to get the context object from where you are using this plist parsing
    public ScheduleXMLResourceParser(Context context) {

        this.context = context;
        switchWeek[0] = false;
        switchWeek[1] = false;
    }

    public ArrayList<ScheduleBlok> getSchedule() {

        // specifying the  your plist file.And Xml ResourceParser is an event type parser for more details Read android source
        XmlResourceParser parser = this.context.getResources()
                .getXml(R.xml.schedule);
        boolean tempGroup = false;
        boolean tempSubGroup = false;
        boolean tempWeek = false;
        boolean cancleWeek_1 = false;
        boolean cancleWeek_2 = false;

        boolean switchGroup = false;
        boolean switchSubGroup = false;
        boolean closeSwitchSubGroup = false;

        ScheduleBlok item;
        scheduleBlokContainer = new ArrayList<ScheduleBlok>();

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
                    }
                    if (parser.getName().equals("day") && switchSubGroup) {
                        nameTag = parser.getName();
                    }
                    if (parser.getName().equals("lecture") && switchSubGroup) {
                        nameTag = parser.getName();
                    }
                    if (parser.getName().equals("startTime") && switchSubGroup) {
                        nameTag = parser.getName();
                    }
                    if (parser.getName().equals("endTime") && switchSubGroup) {
                        nameTag = parser.getName();
                    }
                    if (parser.getName().equals("location") && switchSubGroup) {
                        nameTag = parser.getName();
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
                        scheduleBlokContainer.add(new ScheduleBlok());
                        tempWeek = false;
                    }
                    if (switchGroup && switchSubGroup) {
                        if (switchWeek[0]) {
                            if (cancleWeek_1 == true) {
                                item = new ScheduleBlok();
                                if (nameTag.equals("day") && switchSubGroup) {
                                    item.setNameOfDay(parser.getText());
                                    scheduleBlokContainer.add(item);
                                } else {
                                    if (nameTag.equals("lecture") && switchSubGroup) {
                                        nameLecture = parser.getText();
                                    }
                                    if (nameTag.equals("startTime") && switchSubGroup) {
                                        nameStsrtTime = parser.getText();
                                    }
                                    if (nameTag.equals("endTime") && switchSubGroup) {
                                        nameTimeEnd = parser.getText();
                                    }
                                    if (nameTag.equals("location") && switchSubGroup) {
                                        nameLocation = parser.getText();
                                        item.setLocation(nameLocation);
                                        item.setLecture(nameLecture);
                                        item.setTimeBegin(nameStsrtTime);
                                        item.setTimeEnd(nameTimeEnd);
                                        scheduleBlokContainer.add(item);
                                    }
                                }
                            }
                            cancleWeek_1 = true;
                        }
                        if (switchWeek[1]) {
                            if (cancleWeek_2 == true) {
                                item = new ScheduleBlok();
                                if (nameTag.equals("day") && switchSubGroup) {
                                    item.setNameOfDay(parser.getText());
                                    scheduleBlokContainer.add(item);
                                } else {
                                    if (nameTag.equals("lecture") && switchSubGroup) {
                                        nameLecture = parser.getText();
                                    }
                                    if (nameTag.equals("startTime") && switchSubGroup) {
                                        nameStsrtTime = parser.getText();
                                    }
                                    if (nameTag.equals("endTime") && switchSubGroup) {
                                        nameTimeEnd = parser.getText();
                                    }
                                    if (nameTag.equals("location") && switchSubGroup) {
                                        nameLocation = parser.getText();
                                        item.setLocation(nameLocation);
                                        item.setLecture(nameLecture);
                                        item.setTimeBegin(nameStsrtTime);
                                        item.setTimeEnd(nameTimeEnd);
                                        scheduleBlokContainer.add(item);
                                    }
                                }
                            }
                            cancleWeek_2 = true;
                        }
                    }
                }
                if (event == XmlResourceParser.END_TAG && closeSwitchSubGroup) {
                    if (parser.getName().equals("subgroup")) {
                        return scheduleBlokContainer;
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
        return scheduleBlokContainer;
    }
}
