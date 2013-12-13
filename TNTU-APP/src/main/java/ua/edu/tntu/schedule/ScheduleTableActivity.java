package ua.edu.tntu.schedule;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ua.edu.tntu.R;
import ua.edu.tntu.ScheduleFragment;

public class ScheduleTableActivity extends FragmentActivity {

    private static final String GROUP_NAME = "GROUP_NAME";
    private WeeksPagerAdapter mWeeksPagerAdapter;
    private ViewPager mViewPager;
    private int position;
    private String groupName;
    private SegmentedRadioGroup segmentSubGroup;
    private RadioButton radioButtonForFirstSubGroup;
    private ActionBar actionBar;
    private boolean switchSubGroup;

//    private ScheduleXMLResourceParser scheduleParser;


    public ScheduleTableActivity() {
        this.switchSubGroup = true;
        this.groupName = null;
        this.position = 0;
    }

    public void changeSubGroup(ScheduleXMLResourceParser scheduleParser) {
//        this.scheduleParser = scheduleParser;
        mWeeksPagerAdapter = new WeeksPagerAdapter(getSupportFragmentManager(), scheduleParser);
        position = mViewPager.getCurrentItem();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mWeeksPagerAdapter);
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeks);

        //set group name
        groupName = getIntent().getStringExtra(GROUP_NAME);

        //set action bar
        actionBar = getActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setCustomView(R.layout.segment_to_select_subgroup_menu);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        segmentSubGroup = (SegmentedRadioGroup) actionBar.getCustomView().findViewById(R.id.segment_sub_group);

        radioButtonForFirstSubGroup = (RadioButton) actionBar.getCustomView().findViewById(R.id.firstSubGroup);
        radioButtonForFirstSubGroup.setChecked(true);

        mWeeksPagerAdapter = new WeeksPagerAdapter(getSupportFragmentManager(), new ScheduleXMLResourceParser(getApplicationContext(), groupName, switchSubGroup));
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mWeeksPagerAdapter);


        segmentSubGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.firstSubGroup) {
                    switchSubGroup = true;
                    changeSubGroup(new ScheduleXMLResourceParser(getApplicationContext(), groupName, switchSubGroup));
                } else {
                    switchSubGroup = false;
                    changeSubGroup(new ScheduleXMLResourceParser(getApplicationContext(), groupName, switchSubGroup));
                }
            }
        });
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
}
