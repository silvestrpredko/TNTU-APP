package ua.edu.tntu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ua.edu.tntu.info.InfoArticleActivity;
import ua.edu.tntu.info.InfoListAdapter;


public class InfoFragment extends Fragment implements
        AdapterView.OnItemClickListener {

    public final static String INFO_ARTICLE_TITLE = "ua.edu.tntu.TITLE_INFO";

    private static final String[] titles = new String[]{"Персонал", "Навчання", "Працевлаштування", "Наукова робота"};

    private ListView listView;
    private List<String> rowItems;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment_info, container, false);

        rowItems = new ArrayList<String>();

        for (int i = 0; i < titles.length; i++) {
            rowItems.add(titles[i]);
        }

        assert rootView != null;
        listView = (ListView) rootView.findViewById(R.id.infoListView);

        InfoListAdapter adapter = new InfoListAdapter(rootView.getContext(),
                R.layout.info_listrow_details, rowItems);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this.getActivity(), InfoArticleActivity.class);

        String title = (String) parent.getItemAtPosition(position);

        intent.putExtra(INFO_ARTICLE_TITLE, title);

        (this.getActivity()).startActivity(intent);
    }
}
