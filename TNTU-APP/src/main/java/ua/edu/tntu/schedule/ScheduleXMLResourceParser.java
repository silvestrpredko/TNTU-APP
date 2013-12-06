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
    private String groupName;
    private String subGroup;
    private boolean switchSubGroup;

    private String nameLecture;
    private String nameDay;
    private String nameLocation;
    private String nameStsrtTime;
    private String nameTimeEnd;

    private String nameTextTag;
    private String nameWeek;
    private String nameStartTag;
    private String temp;
    private ArrayList<ScheduleBlok> scheduleBlokContainer;

    private boolean[] switchWeek = new boolean[2];

    // constructor for  to get the context object from where you are using this plist parsing
    public ScheduleXMLResourceParser(Context context, String groupName, boolean switchSubGroup) {
        this.context = context;
        this.groupName = groupName;
        this.switchSubGroup = switchSubGroup;
        if (switchSubGroup) {
            subGroup = " 1\n";
        } else {
            subGroup = " 2\n";
        }
        this.groupName = " " + this.groupName + "\n";
        switchWeek[0] = false;
        switchWeek[1] = false;
    }

    public ArrayList<ScheduleBlok> getSchedule() {

        // specifying the  your plist file.And Xml ResourceParser is an event type parser for more details Read android source
        XmlResourceParser parser = this.context.getResources()
                .getXml(R.xml.schedule);
        boolean flagTempGroup = false;
        boolean flagTempSubGroup = false;
        boolean flagTempWeek = false;
        boolean flagCancleWeek_1 = false;
        boolean flagCancleWeek_2 = false;
        boolean flagSwitchGroup = false;
        boolean flagSwitchSubGroup = false;
        boolean flagCloseSwitchSubGroup = false;

        ScheduleBlok item;
        scheduleBlokContainer = new ArrayList<ScheduleBlok>();

        int event;
        try {
            event = parser.getEventType();

            while (event != parser.END_DOCUMENT) {

                if (event == XmlResourceParser.START_TAG) {
                    if (parser.getName().equals("group")) {
                        flagTempGroup = true;
                    }
                    if (parser.getName().equals("subgroup") && flagSwitchGroup) {
                        flagTempSubGroup = true;
                    }
                    if (parser.getName().equals("week") && flagSwitchSubGroup) {
                        flagTempWeek = true;
                    }
                    if (parser.getName().equals("day") && flagSwitchSubGroup) {
                        nameStartTag = parser.getName();
                    }
                    if (parser.getName().equals("lecture") && flagSwitchSubGroup) {
                        nameStartTag = parser.getName();
                    }
                    if (parser.getName().equals("startTime") && flagSwitchSubGroup) {
                        nameStartTag = parser.getName();
                    }
                    if (parser.getName().equals("endTime") && flagSwitchSubGroup) {
                        nameStartTag = parser.getName();
                    }
                    if (parser.getName().equals("location") && flagSwitchSubGroup) {
                        nameStartTag = parser.getName();
                    }
                }

                if (event == XmlResourceParser.TEXT) {
                    nameTextTag = parser.getText();
                    if (flagTempGroup && nameTextTag.equals(groupName)) {
                        flagSwitchGroup = true;
                    }
                    if (flagTempSubGroup && nameTextTag.equals(subGroup)) {
                        flagSwitchSubGroup = true;
                        flagCloseSwitchSubGroup = true;
                    }

                    if (flagTempWeek && nameTextTag.equals(" first\n")) {
                        nameWeek = nameTextTag;
                        switchWeek[0] = true;
                        flagTempWeek = false;
                    }
                    if (flagTempWeek && nameTextTag.equals(" second\n")) {
                        nameWeek = nameTextTag;
                        switchWeek[1] = true;
                        scheduleBlokContainer.add(new ScheduleBlok());
                        flagTempWeek = false;
                    }
                    if (flagSwitchGroup && flagSwitchSubGroup) {
                        if (switchWeek[0]) {
                            if (flagCancleWeek_1 == true) {
                                item = new ScheduleBlok();
                                if (nameStartTag.equals("day") && flagSwitchSubGroup) {
                                    item.setNameOfDay(parser.getText());
                                    scheduleBlokContainer.add(item);
                                } else {
                                    if (nameStartTag.equals("lecture") && flagSwitchSubGroup) {
                                        nameLecture = parser.getText();
                                    }
                                    if (nameStartTag.equals("startTime") && flagSwitchSubGroup) {
                                        nameStsrtTime = parser.getText();
                                    }
                                    if (nameStartTag.equals("endTime") && flagSwitchSubGroup) {
                                        nameTimeEnd = parser.getText();
                                    }
                                    if (nameStartTag.equals("location") && flagSwitchSubGroup) {
                                        nameLocation = parser.getText();
                                        item.setLocation(nameLocation);
                                        item.setLecture(nameLecture);
                                        item.setTimeBegin(nameStsrtTime);
                                        item.setTimeEnd(nameTimeEnd);
                                        scheduleBlokContainer.add(item);
                                    }
                                }
                            }
                            flagCancleWeek_1 = true;
                        }
                        if (switchWeek[1]) {
                            if (flagCancleWeek_2 == true) {
                                item = new ScheduleBlok();
                                if (nameStartTag.equals("day") && flagSwitchSubGroup) {
                                    item.setNameOfDay(parser.getText());
                                    scheduleBlokContainer.add(item);
                                } else {
                                    if (nameStartTag.equals("lecture") && flagSwitchSubGroup) {
                                        nameLecture = parser.getText();
                                    }
                                    if (nameStartTag.equals("startTime") && flagSwitchSubGroup) {
                                        nameStsrtTime = parser.getText();
                                    }
                                    if (nameStartTag.equals("endTime") && flagSwitchSubGroup) {
                                        nameTimeEnd = parser.getText();
                                    }
                                    if (nameStartTag.equals("location") && flagSwitchSubGroup) {
                                        nameLocation = parser.getText();
                                        item.setLocation(nameLocation);
                                        item.setLecture(nameLecture);
                                        item.setTimeBegin(nameStsrtTime);
                                        item.setTimeEnd(nameTimeEnd);
                                        scheduleBlokContainer.add(item);
                                    }
                                }
                            }
                            flagCancleWeek_2 = true;
                        }
                    }
                }
                if (event == XmlResourceParser.END_TAG && flagCloseSwitchSubGroup) {
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
