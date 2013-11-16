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
 * Created by Ышдмуыек on 16.11.13.
 */
public class ScheduleWeekTableFragment extends Fragment {

    private boolean group;

    public ScheduleWeekTableFragment(boolean group) {
        this.group = group;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ArrayList<Schedule> name = new ArrayList<Schedule>();

        if (this.group == false) {
            String[] beginTime = new String[]{"8:00", "9:30", "11:10", "12:30"};
            String[] endTime = new String[]{"9:20", "10:50", "12:20", "13:10"};
            String[] para = new String[]{"Matan", "Fizika", "Geometry", "egeneering"};
            String[] day = new String[]{"Monday", "Tuesday"};
            Schedule item = new Schedule();
            item.setNameOfDay("Monday");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new Schedule();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
            item = new Schedule();
            item.setNameOfDay("Tuesday");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new Schedule();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
            item = new Schedule();
            item.setNameOfDay("Wednesday");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new Schedule();
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
            Schedule item = new Schedule();
            item.setNameOfDay("Dima");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new Schedule();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
            item = new Schedule();
            item.setNameOfDay("Silvestr");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new Schedule();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
            item = new Schedule();
            item.setNameOfDay("Pasha");
            name.add(item);
            for (int i = 0; i < 4; i++) {
                item = new Schedule();
                item.setTimeBegin(beginTime[i]);
                item.setTimeEnd(endTime[i]);
                item.setPara(para[i]);
                name.add(item);
            }
        }

/*        ArrayList<Schedule> name = new ArrayList<Schedule>();
        String[] beginTime = new String[]{"8:00", "9:30", "11:10", "12:30"};
        String[] endTime = new String[]{"9:20", "10:50", "12:20", "13:10"};
        String[] para = new String[]{"Matan", "Fizika", "Geometry", "egeneering"};
        String[] day = new String[]{"Monday", "Tuesday"};
        Schedule item = new Schedule();
        item.setNameOfDay("Monday");
        name.add(item);
        for (int i = 0; i < 4; i++) {
            item = new Schedule();
            item.setTimeBegin(beginTime[i]);
            item.setTimeEnd(endTime[i]);
            item.setPara(para[i]);
            name.add(item);
        }
        item = new Schedule();
        item.setNameOfDay("Tuesday");
        name.add(item);
        for (int i = 0; i < 4; i++) {
            item = new Schedule();
            item.setTimeBegin(beginTime[i]);
            item.setTimeEnd(endTime[i]);
            item.setPara(para[i]);
            name.add(item);
        }
        item = new Schedule();
        item.setNameOfDay("Wednesday");
        name.add(item);
        for (int i = 0; i < 4; i++) {
            item = new Schedule();
            item.setTimeBegin(beginTime[i]);
            item.setTimeEnd(endTime[i]);
            item.setPara(para[i]);
            name.add(item);
        }*/

        View rootView = inflater.inflate(R.layout.fragment_schedule_table, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.schedule_item_list_view);
        ScheduleListViewAdapter scheduleListViewAdapter = new ScheduleListViewAdapter(this.getActivity(), name);
        listView.setAdapter(scheduleListViewAdapter);

        return rootView;
    }

//   @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.select_subgroup_menu, menu);
//        //menu.findItem(R.id.switcher).getActionView();
//        //Switch sw = (Switch)menu.findItem(R.id.switcher);
//
//
//        MenuItem switchItem = menu.findItem(R.id.switcher);
//        RelativeLayout relativeLayout = (RelativeLayout) MenuItemCompat.getActionView(switchItem);
//        Switch switcher = (Switch) relativeLayout.findViewById(R.id.switchForActionBar);
//        //MenuItem searchItem =  menu.findItem(R.id.switcher);
//        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b == true) {
//                    Toast.makeText(getActivity().getApplicationContext(),"is a checked",Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity().getApplicationContext(),"is not checked",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        super.onCreateOptionsMenu(menu, inflater);
//    }

}