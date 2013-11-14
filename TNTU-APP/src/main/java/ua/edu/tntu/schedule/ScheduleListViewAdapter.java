package ua.edu.tntu.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ua.edu.tntu.R;

public class ScheduleListViewAdapter extends ArrayAdapter<Schedule> {

    private final Context context;
    private final ArrayList<Schedule> item;

    public ScheduleListViewAdapter(Context context, ArrayList<Schedule> item) {
        super(context, R.layout.item_class, R.layout.item_day, item);
        this.context = context;
        this.item = item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (item.get(position).getNameOfDay() != null) {
            View rowViewDay = inflater.inflate(R.layout.item_day, parent, false);
            TextView textViewDay = (TextView) rowViewDay.findViewById(R.id.textViewDay);
            textViewDay.setText(item.get(position).getNameOfDay());
            return rowViewDay;
        } else {
            View rowView = inflater.inflate(R.layout.item_class, parent, false);
            TextView textViewTimeBegin = (TextView) rowView.findViewById(R.id.textViewTimeBegin);
            textViewTimeBegin.setText(item.get(position).getTimeBegin());
            TextView textViewTimeEnd = (TextView) rowView.findViewById(R.id.textViewTimeEnd);
            textViewTimeEnd.setText(item.get(position).getTimeEnd());
            TextView textViewPara = (TextView) rowView.findViewById(R.id.textViewPara);
            textViewPara.setText(item.get(position).getPara());
            return rowView;
        }
    }
}