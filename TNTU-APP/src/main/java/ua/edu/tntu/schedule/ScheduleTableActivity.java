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
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import ua.edu.tntu.R;
import ua.edu.tntu.ScheduleFragment;

public class ScheduleTableActivity extends FragmentActivity {

    private WeeksPagerAdapter mWeeksPagerAdapter;

    int position;

    private ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeks);

        // mWeeksPagerAdapter = new WeeksPagerAdapter(getSupportFragmentManager());

        // Set up action bar.
        final ActionBar actionBar = getActionBar();

        actionBar.setTitle("Schedule");

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        actionBar.setCustomView(R.layout.switch_to_select_subgroup_menu);

        actionBar.setDisplayShowCustomEnabled(true);

        actionBar.setDisplayShowTitleEnabled(true);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME);


        Switch sw = (Switch) actionBar.getCustomView().findViewById(R.id.switchForActionBar);
        mWeeksPagerAdapter = new WeeksPagerAdapter(getSupportFragmentManager(), true, false);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mWeeksPagerAdapter);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Toast.makeText(ScheduleTableActivity.this, "is a checked", Toast.LENGTH_SHORT).show();


                    mWeeksPagerAdapter = new WeeksPagerAdapter(getSupportFragmentManager(), true, false);

                    position = mViewPager.getCurrentItem();

                    mViewPager = (ViewPager) findViewById(R.id.pager);
                    mViewPager.setAdapter(mWeeksPagerAdapter);
                    mViewPager.setCurrentItem(position);
                } else {
                    Toast.makeText(ScheduleTableActivity.this, "is not checked", Toast.LENGTH_SHORT).show();
                    mWeeksPagerAdapter = new WeeksPagerAdapter(getSupportFragmentManager(), false, true);
                    position = mViewPager.getCurrentItem();

                    mViewPager = (ViewPager) findViewById(R.id.pager);
                    mViewPager.setAdapter(mWeeksPagerAdapter);
                    mViewPager.setCurrentItem(position);

                }

            }
        });


//        // Set up the ViewPager, attaching the adapter.
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(mWeeksPagerAdapter);
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

    public class WeeksPagerAdapter extends FragmentStatePagerAdapter {

        public static final int NUMBER_OF_WEEKS = 2;

        private boolean firstWeek;
        private boolean secondWeek;

        public WeeksPagerAdapter(FragmentManager fm, boolean firstWeek, boolean secondWeek) {
            super(fm);
            this.firstWeek = firstWeek;
            this.secondWeek = secondWeek;
        }

        @Override
        public Fragment getItem(int i) {
//            Bundle args = new Bundle();
//            args.putInt(WeekObjectFragment.ARG_OBJECT, i + 1);
//            fragment.setArguments(args);
            if (i == 0) {
                return new ScheduleWeekTableFragment(firstWeek);
            } else {
                return new ScheduleWeekTableFragment(secondWeek);
            }
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
}
