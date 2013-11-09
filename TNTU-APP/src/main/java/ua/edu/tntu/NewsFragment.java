package ua.edu.tntu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import ua.edu.tntu.news.NewsArticleActivity;
import ua.edu.tntu.news.NewsListAdapter;
import ua.edu.tntu.news.NewsRowItem;
import ua.edu.tntu.news.XMLPullParserHandler;

public class NewsFragment extends Fragment implements
        AdapterView.OnItemClickListener {

    public final static String NEWS_ARTICLE_TITLE = "ua.edu.tntu.TITLE";
    public final static String NEWS_IMG_URL_BIG = "ua.edu.tntu.IMG_BIG";
    public final static String NEWS_IMG_URL_SMALL = "ua.edu.tntu.IMG_SMALL";
    public final static String NEWS_ARTICLE_TEXT = "ua.edu.tntu.ARTICLE";

    private static final String XML_URL = "https://www.dropbox.com/s/v69tmu21hufl8kl/news_db.xml?dl=1";

    private ListView listView;
    private List<NewsRowItem> items;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment_news, container, false);

        listView = (ListView) rootView.findViewById(R.id.newsListView);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            XMLPullParserHandler parser = new XMLPullParserHandler();
            items = parser.parse(XML_URL);
            NewsListAdapter adapter = new NewsListAdapter(this.getActivity(),
                    R.layout.news_listrow_details, items);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this.getActivity(), NewsArticleActivity.class);

        NewsRowItem rowItem = (NewsRowItem) parent.getItemAtPosition(position);

        String title = rowItem.getTitle();
        String imgURL = rowItem.getImageBig();
        String article = rowItem.getArticle();

        intent.putExtra(NEWS_ARTICLE_TITLE, title);
        intent.putExtra(NEWS_IMG_URL_BIG, imgURL);
        intent.putExtra(NEWS_ARTICLE_TEXT, article);

        (this.getActivity()).startActivity(intent);
    }
}
