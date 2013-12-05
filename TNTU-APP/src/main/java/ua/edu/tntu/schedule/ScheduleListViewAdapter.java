package ua.edu.tntu.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ua.edu.tntu.R;

public class ScheduleListViewAdapter extends ArrayAdapter<ScheduleBlok> {

    private Context context;
    private ArrayList<ScheduleBlok> scheduleBlokArrayList;
    private static String TAG = "myLogs";

    public ScheduleListViewAdapter(Context context, ArrayList<ScheduleBlok> item) {
        super(context, R.layout.item_class, R.layout.item_day, item);
        this.context = context;
        this.scheduleBlokArrayList = item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        /*for (int i = 0;i < scheduleBlokArrayList.size();i++) {
            Log.d(TAG, "\n////////////////////////////////\n" + "\n"
                    + scheduleBlokArrayList.get(i).getNameOfDay() + "\n" + scheduleBlokArrayList.get(i).getLecture() + "\n"
                    + scheduleBlokArrayList.get(i).getTimeBegin() + "\n" + scheduleBlokArrayList.get(i).getTimeEnd() + "\n"
                    + scheduleBlokArrayList.get(i).getLocation()
                    + "\n///////////////////////////////////");
        }*/
        if (scheduleBlokArrayList.get(position).getNameOfDay() != "empty") {
            View rowViewDay = inflater.inflate(R.layout.item_day, parent, false);
            TextView textViewDay = (TextView) rowViewDay.findViewById(R.id.textViewDay);
            textViewDay.setText(scheduleBlokArrayList.get(position).getNameOfDay());
            return rowViewDay;
        } else {
            View rowView = inflater.inflate(R.layout.item_class, parent, false);
            TextView textViewTimeBegin = (TextView) rowView.findViewById(R.id.textViewTimeBegin);
            textViewTimeBegin.setText(scheduleBlokArrayList.get(position).getTimeBegin());
            TextView textViewTimeEnd = (TextView) rowView.findViewById(R.id.textViewTimeEnd);
            textViewTimeEnd.setText(scheduleBlokArrayList.get(position).getTimeEnd());
            TextView textViewLecture = (TextView) rowView.findViewById(R.id.textViewLecture);
            textViewLecture.setText(scheduleBlokArrayList.get(position).getLecture());
            TextView textViewLocation = (TextView) rowView.findViewById(R.id.textViewLocation);
            textViewLocation.setText(scheduleBlokArrayList.get(position).getLocation());
            return rowView;
        }
    }
}