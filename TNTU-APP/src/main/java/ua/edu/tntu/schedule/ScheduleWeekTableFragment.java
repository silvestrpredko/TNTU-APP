package ua.edu.tntu.schedule;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

import ua.edu.tntu.R;

/**
 * Created by Silvestr on 16.11.13.
 */
public class ScheduleWeekTableFragment extends Fragment {

    private final String INDEX_FOR_POSITION_FIRST_WEEK = "INDEX_FIRST_POSITION";
    private final String INDEX_FOR_POSITION_SECOND_WEEK = "INDEX_SECOND_POSITION";
    private final String TOP_FOR_POSITION_FIRST_WEEK = "TOP_FIRST_POSITION";
    private final String TOP_FOR_POSITION_SECOND_WEEK = "TOP_SECOND_POSITION";

    private int indexFirstWeek;
    private int indexSecondWeek;
    private int topFirstWeek;
    private int topSecondWeek;

    private String groupName;
    private boolean switchSubGroup;
    private boolean changeWeek;
    private boolean middleOfTheList;

    private ArrayList<ScheduleBlock> scheduleList;
    private ScheduleXMLResourceParser scheduleParser;
    private ArrayList<ScheduleBlock> scheduleTempList;
    private SharedPreferences sharedPreferences;

    private static String TAG = "myLogs";

    public ScheduleWeekTableFragment(ScheduleXMLResourceParser resourceParser, boolean changeWeek) {
        this.groupName = groupName;
        this.switchSubGroup = switchSubGroup;
        this.changeWeek = changeWeek;
        this.middleOfTheList = false;
        this.scheduleParser = resourceParser;
        this.indexFirstWeek = 0;
        this.indexSecondWeek = 0;
        this.topFirstWeek = 0;
        this.topSecondWeek = 0;
    }

    public void saveScrollPositionFirstWeek(int indexFirstWeek, int topFirstWeek) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor positionSaver = sharedPreferences.edit();
        positionSaver.putInt(INDEX_FOR_POSITION_FIRST_WEEK, indexFirstWeek);
        positionSaver.putInt(TOP_FOR_POSITION_FIRST_WEEK, topFirstWeek);
        positionSaver.commit();
    }

    public void saveScrollPositionSecondWeek(int indexSecondWeek, int topSecondWeek) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor positionSaver = sharedPreferences.edit();
        positionSaver.putInt(INDEX_FOR_POSITION_SECOND_WEEK, indexSecondWeek);
        positionSaver.putInt(TOP_FOR_POSITION_SECOND_WEEK, topSecondWeek);
        positionSaver.commit();
    }

    public int[] loadScrollPositionFirstWeek() {
        int[] tempPosition = new int[2];
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        tempPosition[0] = sharedPreferences.getInt(INDEX_FOR_POSITION_FIRST_WEEK, 0);
        tempPosition[1] = sharedPreferences.getInt(TOP_FOR_POSITION_FIRST_WEEK, 0);
        return tempPosition;
    }

    public int[] loadScrollPositionSecondWeek() {
        int[] tempPosition = new int[2];
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        tempPosition[0] = sharedPreferences.getInt(INDEX_FOR_POSITION_SECOND_WEEK, 0);
        tempPosition[1] = sharedPreferences.getInt(TOP_FOR_POSITION_SECOND_WEEK, 0);
        return tempPosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

//        scheduleParser = new ScheduleXMLResourceParser(this.getActivity().getApplicationContext(), this.groupName, this.switchSubGroup);

        scheduleList = scheduleParser.getSchedule();

        scheduleTempList = new ArrayList<ScheduleBlock>();


        if (this.changeWeek) {
            for (int i = 0; i < scheduleList.size(); i++) {
                if (scheduleList.get(i).getLecture() == null && scheduleList.get(i).getNameOfDay() == null) {
                    break;
                } else {
                    scheduleTempList.add(scheduleList.get(i));
                }
            }
        } else {
            for (int i = 0; i < scheduleList.size(); i++) {
                if (scheduleList.get(i).getLecture() == null && scheduleList.get(i).getNameOfDay() == null) {
                    middleOfTheList = true;
                    i++;
                }
                if (middleOfTheList) {
                    scheduleTempList.add(scheduleList.get(i));
                }
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_schedule_table, container, false);
        final ListView listView = (ListView) rootView.findViewById(R.id.schedule_item_list_view);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (changeWeek) {
                    indexFirstWeek = listView.getFirstVisiblePosition();
                    View v = listView.getChildAt(0);
                    topFirstWeek = (v == null) ? 0 : v.getTop();

                } else {
                    indexSecondWeek = listView.getFirstVisiblePosition();
                    View v = listView.getChildAt(0);
                    topSecondWeek = (v == null) ? 0 : v.getTop();
                }
            }
        });

        ScheduleListViewAdapter scheduleListViewAdapter = new ScheduleListViewAdapter(this.getActivity().getApplicationContext(), scheduleTempList);
        listView.setAdapter(scheduleListViewAdapter);

        if (changeWeek) {
            listView.setSelectionFromTop(loadScrollPositionFirstWeek()[0], loadScrollPositionFirstWeek()[1]);
            saveScrollPositionFirstWeek(0, 0);
        } else {
            listView.setSelectionFromTop(loadScrollPositionSecondWeek()[0], loadScrollPositionSecondWeek()[1]);
            saveScrollPositionSecondWeek(0, 0);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (loadScrollPositionFirstWeek()[0] == 0 && loadScrollPositionFirstWeek()[1] == 0) {
            saveScrollPositionFirstWeek(indexFirstWeek, topFirstWeek);
        }
        if (loadScrollPositionSecondWeek()[0] == 0 && loadScrollPositionSecondWeek()[1] == 0) {
            saveScrollPositionSecondWeek(indexSecondWeek, topSecondWeek);
        }
    }
}