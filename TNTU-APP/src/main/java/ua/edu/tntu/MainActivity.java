package ua.edu.tntu;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import ua.edu.tntu.navigation.AppSectionsPagerAdapter;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager viewPager;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        AppSectionsPagerAdapter sectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#34495e")));

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.

        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.news).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.calendar).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setIcon(R.drawable.info).setTabListener(this));

//        actionBar.addTab(actionBar.newTab().setText("News").setTabListener(this));
//        actionBar.addTab(actionBar.newTab().setText("Schedule").setTabListener(this));
//        actionBar.addTab(actionBar.newTab().setText("Info").setTabListener(this));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                openOptionsMenu();
                Toast.makeText(this, "You Push Back", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        viewPager.setCurrentItem(tab.getPosition());

        assert getActionBar() != null;

        switch (tab.getPosition()) {

            case 0:
                getActionBar().setTitle("News");
                break;

            case 1:
                getActionBar().setTitle("Schedule");
                break;

            case 2:
                getActionBar().setTitle("Info");
                break;
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
}
