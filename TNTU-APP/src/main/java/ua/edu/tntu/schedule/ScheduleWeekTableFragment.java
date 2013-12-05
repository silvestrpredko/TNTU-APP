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
    private boolean switcher;
    private ScheduleXMLResourceParser scheduleParser;
    private ArrayList<ScheduleBlok> scheduleBlokContainer;
    private static String TAG = "myLogs";

    public ScheduleWeekTableFragment(boolean week) {
        this.week = week;
        switcher = false;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        scheduleParser = new ScheduleXMLResourceParser(this.getActivity().getApplicationContext());

        ArrayList<ScheduleBlok> temp = scheduleParser.getSchedule();

        ArrayList<ScheduleBlok> name = new ArrayList<ScheduleBlok>();

        if (this.week == false) {
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getLecture().equals("empty") && temp.get(i).getNameOfDay().equals("empty")) {
                    switcher = true;
                    i++;
                }
                if (switcher == true) {
                    name.add(temp.get(i));
                }
            }
        } else {
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getNameOfDay().equals("empty") && temp.get(i).getLecture().equals("empty")) {
                    break;
                }
                name.add(temp.get(i));
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_schedule_table, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.schedule_item_list_view);
        ScheduleListViewAdapter scheduleListViewAdapter = new ScheduleListViewAdapter(this.getActivity(), name);
        listView.setAdapter(scheduleListViewAdapter);

        return rootView;
    }
}