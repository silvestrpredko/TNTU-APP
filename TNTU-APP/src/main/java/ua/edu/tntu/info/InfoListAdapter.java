package ua.edu.tntu.info;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ua.edu.tntu.R;

public class InfoListAdapter extends ArrayAdapter<String> {

    private Context context;

    public InfoListAdapter(Context context, int resourceId,
                           List<String> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    private class ViewHolder {
        TextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        String rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.info_listrow_details, null);
            holder = new ViewHolder();
            assert convertView != null;
            holder.txtTitle = (TextView) convertView.findViewById(R.id.infoTitle);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem);

        return convertView;
    }
}