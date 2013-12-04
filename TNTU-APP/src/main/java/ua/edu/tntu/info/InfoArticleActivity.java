package ua.edu.tntu.info;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import ua.edu.tntu.FullScreenImageActivity;
import ua.edu.tntu.InfoFragment;
import ua.edu.tntu.NewsFragment;
import ua.edu.tntu.R;
import ua.edu.tntu.news.AnimateFirstDisplayListener;

public class InfoArticleActivity extends Activity implements View.OnClickListener {

    private static final String IMG_URL = "ua.edu.tntu.info.IMG_URL";
    private String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_article);

        Intent intent = getIntent();

        String title = intent.getStringExtra(InfoFragment.INFO_ARTICLE_TITLE);
        imageURL = intent.getStringExtra(InfoFragment.INFO_IMG_URL);
        String article = intent.getStringExtra(InfoFragment.INFO_ARTICLE_TEXT);

        TextView titleTextView = (TextView) findViewById(R.id.infoTitleTextView);
        titleTextView.setText(title);

        TextView articleTextView = (TextView) findViewById(R.id.infoArticleTextView);
        articleTextView.setText(Html.fromHtml(article));

        DisplayImageOptions displayImageOptions;
        ImageLoadingListener animateFirstListener;

        displayImageOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_stub).
                showImageOnFail(R.drawable.ic_stub).
                cacheInMemory(true).cacheOnDisc(true).build();

        animateFirstListener = new AnimateFirstDisplayListener();

        ImageView imageView = (ImageView) findViewById(R.id.infoArticleImageView);
        imageView.setOnClickListener(this);

        ImageLoader imageLoader;
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        imageLoader.displayImage(imageURL, imageView, displayImageOptions, animateFirstListener);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, FullScreenImageActivity.class);

        String fullScreenImageURL = imageURL;
        intent.putExtra(IMG_URL, fullScreenImageURL);

        startActivity(intent);
    }
}
