package ua.edu.tntu.preferences;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import ua.edu.tntu.R;

/**
 * Created by silvestr on 12/10/13.
 */

public class About extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_about);
    }
}
