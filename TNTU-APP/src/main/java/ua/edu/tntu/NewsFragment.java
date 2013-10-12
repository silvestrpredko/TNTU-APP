package ua.edu.tntu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements
        AdapterView.OnItemClickListener {

    public static final String[] titles = new String[]{"Strawberry",
            "Banana", "Orange", "Mixed"};

    public static final String[] descriptions = new String[]{
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant", "Citrus Fruit",
            "Mixed Fruits"};

    public static final Integer[] images = {R.drawable.straw,
            R.drawable.banana, R.drawable.orange, R.drawable.mixed};

    ListView listView;
    List<NewsRowItem> rowItems;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment_news, container, false);

        Activity a = getActivity();
        if (a == null) {
            System.out.println("NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {
            System.out.println("OK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

        rowItems = new ArrayList<NewsRowItem>();

        for (int i = 0; i < titles.length; i++) {
            NewsRowItem item = new NewsRowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }
//
//        listView = (ListView) getActivity().findViewById(R.id.list);
//        NewsListAdapter adapter = new NewsListAdapter(getActivity(),
//                R.layout.news_listrow_details, rowItems);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
