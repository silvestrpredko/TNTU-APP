package ua.edu.tntu.schedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ua.edu.tntu.R;

public class SelectSubgroupActivity extends Activity implements View.OnClickListener {

    public static final String SUB_GROUP_NAME = "com.hfomn.expandedlistexample.SUB_NAME";

    Button firstSub;
    Button secondSub;
    TextView groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subgroup);

        firstSub = (Button) findViewById(R.id.button);
        secondSub = (Button) findViewById(R.id.button2);

        firstSub.setOnClickListener(this);
        secondSub.setOnClickListener(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra(ExpandableListAdapter.GROUP_NAME);

        groupName = (TextView) findViewById(R.id.textView);
        groupName.setText(name);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ScheduleTableActivity.class);
        startActivity(intent);
    }
}
