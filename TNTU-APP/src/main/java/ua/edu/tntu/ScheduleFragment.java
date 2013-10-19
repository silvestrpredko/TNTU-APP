package ua.edu.tntu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import ua.edu.tntu.schedule.ScheduleExpandableListAdapter;
import ua.edu.tntu.schedule.ScheduleGroup;

public class ScheduleFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    private SparseArray<ScheduleGroup> groups = new SparseArray<ScheduleGroup>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment_schedule, container, false);
        ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.listView111);
        listView.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
        listView.setOnChildClickListener(this);
        createData();
        ScheduleExpandableListAdapter adapter = new ScheduleExpandableListAdapter(this.getActivity(), groups);
        listView.setAdapter(adapter);
        return rootView;
    }

    public void createData() {

        ScheduleGroup scheduleGroup = new ScheduleGroup("Перший курс");
        scheduleGroup.children.add("СБ-11");
        scheduleGroup.children.add("СІ-11");
        scheduleGroup.children.add("СН-11");
        scheduleGroup.children.add("СН-12");
        scheduleGroup.children.add("СП-11");
        groups.append(0, scheduleGroup);

        scheduleGroup = new ScheduleGroup("Другий курс");

        scheduleGroup.children.add("СБ-21");
        scheduleGroup.children.add("СІ-21");
        scheduleGroup.children.add("СН-21");
        scheduleGroup.children.add("СП-21");
        groups.append(1, scheduleGroup);

        scheduleGroup = new ScheduleGroup("Третій курс");

        scheduleGroup.children.add("СІс-31");
        scheduleGroup.children.add("СІ-31");
        scheduleGroup.children.add("СН-31");
        scheduleGroup.children.add("СНс-31");
        scheduleGroup.children.add("СП-31");
        scheduleGroup.children.add("СПс-31");
        groups.append(2, scheduleGroup);

        scheduleGroup = new ScheduleGroup("Четвертий курс");

        scheduleGroup.children.add("СІ-41");
        scheduleGroup.children.add("СІ-42");
        scheduleGroup.children.add("СН-41");
        scheduleGroup.children.add("СНс-41");
        scheduleGroup.children.add("СНс-42");
        scheduleGroup.children.add("СП-41");
        groups.append(3, scheduleGroup);

        scheduleGroup = new ScheduleGroup("П'ятий курс");

        scheduleGroup.children.add("СІ-51");
        scheduleGroup.children.add("СІ-52");
        scheduleGroup.children.add("СІм-51");
        scheduleGroup.children.add("СН-51");
        scheduleGroup.children.add("СНм-51");
        scheduleGroup.children.add("СП-51");
        scheduleGroup.children.add("СПм-51");
        groups.append(4, scheduleGroup);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        //v.setClickable(true);
        v.setBackgroundColor(Color.BLUE);
        return false;
    }
}

