package ua.edu.tntu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ua.edu.tntu.info.InfoArticleActivity;
import ua.edu.tntu.info.InfoListAdapter;
import ua.edu.tntu.info.InfoRowItem;
import ua.edu.tntu.info.InfoXMLPullParser;

public class InfoFragment extends Fragment implements
        AdapterView.OnItemClickListener {

    public final static String INFO_ARTICLE_TITLE = "ua.edu.tntu.info.TITLE_INFO";
    public final static String INFO_IMG_URL = "ua.edu.tntu.info.IMG";
    public final static String INFO_ARTICLE_TEXT = "ua.edu.tntu.info.ARTICLE";

    private boolean alreadyParsed = false;
    private List<InfoRowItem> items;

    String filename = "info.xml";
    InputStream inputStream;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment_info, container, false);

        try {
            inputStream = getActivity().getAssets().open(filename);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("", "ERROR OPENING FILE " + filename);
        }

        assert rootView != null;
        ListView listView = (ListView) rootView.findViewById(R.id.infoListView);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.
                    ThreadPolicy.Builder().
                    permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            InfoXMLPullParser parser = new InfoXMLPullParser();

            if (!alreadyParsed) {
                items = parser.parse(inputStream);
                Log.d("PARSER", "IT'S OK!!!");
                alreadyParsed = true; // don't parse again!
            }

            InfoListAdapter adapter = new InfoListAdapter(this.getActivity(),
                    R.layout.info_listrow_details, items);

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

        Intent intent = new Intent(this.getActivity(), InfoArticleActivity.class);

        InfoRowItem rowItem = (InfoRowItem) parent.getItemAtPosition(position);

        String title = rowItem.getTitle();
        String imgURL = rowItem.getImage();
        String article = rowItem.getArticle();

        intent.putExtra(INFO_ARTICLE_TITLE, title);
        intent.putExtra(INFO_IMG_URL, imgURL);
        intent.putExtra(INFO_ARTICLE_TEXT, article);

        (this.getActivity()).startActivity(intent);
    }
}
