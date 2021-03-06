package ua.edu.tntu.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import ua.edu.tntu.NewsFragment;
import ua.edu.tntu.R;
import ua.edu.tntu.TouchImageView;

public class FullScreenImageActivity extends Activity {

    String imgURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screan_image);

        Intent intent = getIntent();

        imgURL = intent.getStringExtra(NewsArticleActivity.IMG_URL);

        DisplayImageOptions displayImageOptions;
        ImageLoadingListener animateFirstListener;

        displayImageOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_stub).
                showImageOnFail(R.drawable.ic_stub).
                cacheInMemory(true).cacheOnDisc(true).build();

        animateFirstListener = new AnimateFirstDisplayListener();

        TouchImageView touch = (TouchImageView) findViewById(R.id.touchImage);

        ImageLoader imageLoader;
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        imageLoader.displayImage(imgURL, touch, displayImageOptions, animateFirstListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, NewsFragment.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
