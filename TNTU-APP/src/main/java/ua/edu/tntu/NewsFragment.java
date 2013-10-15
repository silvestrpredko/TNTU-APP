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

    public static final String[] titles = new String[]{"На базі ТНТУ відбувся І етап" +
            " Всеукраїнської студентської олімпіади з програмування у Тернопільській області",
            "Програми подвійних магістерських дипломів університету з мережею вищих " +
                    "шкіл Франції «n+i»",
            "Візит делегації Мережі Вищих Інженерних Шкіл Франції «n+i»",
            "День відкритих дверей на кафедрі програмної інженерії",
            "Всеукраїнська студентська командна олімпіада з програмування"};

    public static final Integer[] images = {R.drawable.orange, R.drawable.straw, R.drawable.mixed, R.drawable.banana, R.drawable.olympiada};

    ListView listView;
    List<NewsRowItem> rowItems;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment_news, container, false);

        rowItems = new ArrayList<NewsRowItem>();

        for (int i = 0; i < titles.length; i++) {
            NewsRowItem item = new NewsRowItem(images[i], titles[i]);
            rowItems.add(item);
        }

        assert rootView != null;
        listView = (ListView) rootView.findViewById(R.id.list);
        NewsListAdapter adapter = new NewsListAdapter(this.getActivity(),
                R.layout.news_listrow_details, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
