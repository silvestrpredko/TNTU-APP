package ua.edu.tntu.schedule;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import ua.edu.tntu.R;

public class ScheduleExpandableListAdapter extends BaseExpandableListAdapter
        implements ExpandableListView.OnChildClickListener {

    private static final String GROUP_NAME = "GROUP_NAME";

    private final SparseArray<ScheduleGroup> groups;
    private LayoutInflater inflater;
    private Activity activity;

    public ScheduleExpandableListAdapter(Activity act, SparseArray<ScheduleGroup> groups) {
        activity = act;
        this.groups = groups;
        inflater = act.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.schedule_listrow_details, null);
        }

        text = (TextView) convertView.findViewById(R.id.scheduleGroupNameTextView);
        text.setText(children);

        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(finalConvertView.getContext(), ScheduleTableActivity.class);
                intent.putExtra(GROUP_NAME, children);
                (finalConvertView.getContext()).startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.schedule_listrow_group, null);
        }

        ScheduleGroup scheduleGroup = (ScheduleGroup) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(scheduleGroup.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
        parent.setItemChecked(index, true);
        return true;
    }
}