package ua.edu.tntu.tabBarPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.edu.tntu.FragmentActivityNews;
import ua.edu.tntu.FragmentActivitySchedule;

public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

    public AppSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
            {
                return new FragmentActivityNews();
            }
            case 1:
            {
                return new FragmentActivitySchedule();
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Section " + (position + 1);
    }
}
