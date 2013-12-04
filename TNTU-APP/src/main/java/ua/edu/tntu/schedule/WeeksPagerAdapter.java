package ua.edu.tntu.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Silvestr on 17.11.13.
 */
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

