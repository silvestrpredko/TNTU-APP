package ua.edu.tntu.news;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.List;

import ua.edu.tntu.R;

public class NewsListAdapter extends ArrayAdapter<NewsRowItem> {

    private Context context;
    DisplayImageOptions displayImageOptions;
    private ImageLoadingListener animateFirstListener;
    ImageLoader imageLoader;

    public NewsListAdapter(Context context, int resourceId,
                           List<NewsRowItem> items) {
        super(context, resourceId, items);
        this.context = context;

        displayImageOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_stub).
                showImageOnFail(R.drawable.ic_stub).
                cacheInMemory(true).cacheOnDisc(true).build();

        animateFirstListener = new AnimateFirstDisplayListener();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        NewsRowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.news_listrow_details, null);
            holder = new ViewHolder();
            assert convertView != null;
            holder.txtTitle = (TextView) convertView.findViewById(R.id.newsListItemTitle);
            holder.imageView = (ImageView) convertView.findViewById(R.id.newsListItemImage);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getTitle());

        imageLoader.displayImage(rowItem.getImageSmall(), holder.imageView,
                displayImageOptions, animateFirstListener);

        return convertView;
    }
}