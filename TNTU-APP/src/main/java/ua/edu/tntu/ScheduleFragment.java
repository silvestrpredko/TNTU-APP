package ua.edu.tntu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import ua.edu.tntu.schedule.ExpandableListAdapter;
import ua.edu.tntu.schedule.Group;

public class ScheduleFragment extends Fragment implements ExpandableListView.OnChildClickListener {

    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fregment_schedule, container, false);
        ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.listView111);
        listView.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
        listView.setOnChildClickListener(this);
        createData();
        ExpandableListAdapter adapter = new ExpandableListAdapter(this.getActivity(), groups);
        listView.setAdapter(adapter);
        return rootView;
    }

    public void createData() {

        Group group = new Group("Перший курс");
        group.children.add("СБ-11");
        group.children.add("СІ-11");
        group.children.add("СН-11");
        group.children.add("СН-12");
        group.children.add("СП-11");
        groups.append(0, group);

        group = new Group("Другий курс");

        group.children.add("СБ-21");
        group.children.add("СІ-21");
        group.children.add("СН-21");
        group.children.add("СП-21");
        groups.append(1, group);

        group = new Group("Третій курс");

        group.children.add("СІс-31");
        group.children.add("СІ-31");
        group.children.add("СН-31");
        group.children.add("СНс-31");
        group.children.add("СП-31");
        group.children.add("СПс-31");
        groups.append(2, group);

        group = new Group("Четвертий курс");

        group.children.add("СІ-41");
        group.children.add("СІ-42");
        group.children.add("СН-41");
        group.children.add("СНс-41");
        group.children.add("СНс-42");
        group.children.add("СП-41");
        groups.append(3, group);

        group = new Group("П'ятий курс");

        group.children.add("СІ-51");
        group.children.add("СІ-52");
        group.children.add("СІм-51");
        group.children.add("СН-51");
        group.children.add("СНм-51");
        group.children.add("СП-51");
        group.children.add("СПм-51");
        groups.append(4, group);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        //v.setClickable(true);
        v.setBackgroundColor(Color.RED);
        return false;
    }
}

