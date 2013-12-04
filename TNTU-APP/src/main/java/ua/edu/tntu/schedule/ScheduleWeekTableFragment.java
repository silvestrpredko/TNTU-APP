package ua.edu.tntu.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ua.edu.tntu.R;

/**
 * Created by Silvestr on 16.11.13.
 */
public class ScheduleWeekTableFragment extends Fragment {

    private boolean week;
    private ScheduleXMLResourceParser scheduleParser;

    public ScheduleWeekTableFragment(boolean week) {
        this.week = week;
        scheduleParser = new ScheduleXMLResourceParser(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        scheduleParser.getSchedule();


        ArrayList<ScheduleBlok> name = new ArrayList<ScheduleBlok>();

        if (this.week == false) {
            String[] beginTime = new String[]{"8:00", "9:30", "11:10", "12:30"};
            String[] endTime = new String[]{"9:20", "10:50", "12:20", "13:10"};
            String[] para = new String[]{"Matan", "Fizika", "Geometry", "egeneering"};
            String[] day = new String[]{"Monday", "Tuesday"};
            ScheduleBlok item = new ScheduleBlok();
            item.setNameOfDay("Monday");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new ScheduleBlok();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
            item = new ScheduleBlok();
            item.setNameOfDay("Tuesday");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new ScheduleBlok();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
            item = new ScheduleBlok();
            item.setNameOfDay("Wednesday");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new ScheduleBlok();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }

        } else {
            String[] beginTime = new String[]{"8:00", "9:30", "11:10", "12:30"};
            String[] endTime = new String[]{"9:20", "10:50", "12:20", "13:10"};
            String[] para = new String[]{"Matan", "Fizika", "Geometry", "egeneering"};
            String[] day = new String[]{"Monday", "Tuesday"};
            ScheduleBlok item = new ScheduleBlok();
            item.setNameOfDay("Dima");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new ScheduleBlok();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
            item = new ScheduleBlok();
            item.setNameOfDay("Silvestr");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new ScheduleBlok();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
            item = new ScheduleBlok();
            item.setNameOfDay("Pasha");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new ScheduleBlok();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_schedule_table, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.schedule_item_list_view);
        ScheduleListViewAdapter scheduleListViewAdapter = new ScheduleListViewAdapter(this.getActivity(), name);
        listView.setAdapter(scheduleListViewAdapter);

        return rootView;
    }
}