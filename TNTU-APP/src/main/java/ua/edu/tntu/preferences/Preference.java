package ua.edu.tntu.preferences;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import ua.edu.tntu.R;

/**
 * Created by silvestr on 12/10/13.
 */
public class Preference extends PreferenceActivity {
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getActionBar();

        actionBar.setIcon(R.drawable.logo);

        getFragmentManager().beginTransaction()
                .add(android.R.id.content, new FragmentPreference()).commit();
    }
}
