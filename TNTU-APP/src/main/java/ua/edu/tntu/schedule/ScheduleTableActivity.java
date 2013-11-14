package ua.edu.tntu.schedule;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ua.edu.tntu.R;
import ua.edu.tntu.ScheduleFragment;

public class ScheduleTableActivity extends FragmentActivity {

    private WeeksPagerAdapter mWeeksPagerAdapter;

    private ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeks);

        mWeeksPagerAdapter = new WeeksPagerAdapter(getSupportFragmentManager());

        // Set up action bar.
        final ActionBar actionBar = getActionBar();

        actionBar.setTitle("Schedule");

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mWeeksPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, ScheduleFragment.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class WeeksPagerAdapter extends FragmentStatePagerAdapter {

        public static final int NUMBER_OF_WEEKS = 2;

        public WeeksPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            //            Bundle args = new Bundle();
//            args.putInt(WeekObjectFragment.ARG_OBJECT, i + 1);
//            fragment.setArguments(args);
            return new ScheduleWeekTableActivity();
        }

        @Override
        public int getCount() {
            return NUMBER_OF_WEEKS;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {

                case 0:
                    return "Перший теждень";

                case 1:
                    return "Другий тиждень";

                default:
                    return null;
            }
        }
    }
//
//    public static class WeekObjectFragment extends Fragment {
//
//        public static final String ARG_OBJECT = "object";
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//
//            View rootView = inflater.inflate(R.layout.fragment_schedule_table, container, false);
//            Bundle args = getArguments();
//            assert rootView != null;
//            ((TextView) rootView.findViewById(R.id.scheduleTextView)).setText(
//                    Integer.toString(args.getInt(ARG_OBJECT)));
//            return rootView;
//        }
//    }

    public static class ScheduleWeekTableActivity extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            ArrayList<Schedule> name = new ArrayList<Schedule>();
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

            View rootView = inflater.inflate(R.layout.fragment_schedule_table, container, false);

            ListView listView = (ListView) rootView.findViewById(R.id.schedule_item_list_view);

            ScheduleListViewAdapter scheduleListViewAdapter = new ScheduleListViewAdapter(this.getActivity(), name);

            listView.setAdapter(scheduleListViewAdapter);
            return rootView;
        }
    }
}
