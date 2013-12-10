package ua.edu.tntu.preferences;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import ua.edu.tntu.R;

/**
 * Created by silvestr on 12/10/13.
 */
public class FragmentPreference extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        findPreference("preference_about_developers");

        android.preference.Preference preferenceAbout = (Preference) findPreference("preference_about_developers");

        assert preferenceAbout != null;
        preferenceAbout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intentAbout = new Intent(getActivity().getApplication(), About.class);
                startActivity(intentAbout);
                return true;
            }
        });


    }
}
