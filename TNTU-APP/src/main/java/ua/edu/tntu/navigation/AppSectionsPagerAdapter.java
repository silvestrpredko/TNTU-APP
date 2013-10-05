package ua.edu.tntu.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.edu.tntu.NewsFragment;
import ua.edu.tntu.ScheduleFragment;

public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

    public static final int NUMBER_OF_TABS = 2;

    public AppSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new NewsFragment();

            case 1:
                return new ScheduleFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "News";

            case 1:
                return "Schedule";

            default:
                return "UNKNOWN";
        }
    }
}
