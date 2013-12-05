package ua.edu.tntu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.util.ByteArrayBuffer;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ua.edu.tntu.news.NewsArticleActivity;
import ua.edu.tntu.news.NewsListAdapter;
import ua.edu.tntu.news.NewsRowItem;
import ua.edu.tntu.news.NewsXMLPullParser;

public class NewsFragment extends Fragment implements
        AdapterView.OnItemClickListener {

    public final static String NEWS_ARTICLE_TITLE = "ua.edu.tntu.TITLE";
    public final static String NEWS_IMG_URL_BIG = "ua.edu.tntu.IMG_BIG";
    public final static String NEWS_IMG_URL_SMALL = "ua.edu.tntu.IMG_SMALL";
    public final static String NEWS_ARTICLE_TEXT = "ua.edu.tntu.ARTICLE";

    private static final String XML_URL = "https://www.dropbox.com/s/v69tmu21hufl8kl/news_db.xml?dl=1";

    private boolean alreadyParsed = false;

    List<NewsRowItem> newsRowItems;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fragment_news, container, false);

        assert rootView != null;
        ListView listView = (ListView) rootView.findViewById(R.id.newsListView);

        if (!alreadyParsed) {
            DownloadFileFromURL downloading = new DownloadFileFromURL();
            downloading.execute(XML_URL, "news.xml");

            try {
                newsRowItems = downloading.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            alreadyParsed = true;
        }

        NewsListAdapter adapter = new NewsListAdapter(this.getActivity(),
                R.layout.news_listrow_details, newsRowItems);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this.getActivity(), NewsArticleActivity.class);

        NewsRowItem rowItem = (NewsRowItem) parent.getItemAtPosition(position);

        String title = rowItem.getTitle();
        String imgURLBig = rowItem.getImageBig();
        String imgURLSmall = rowItem.getImageSmall();
        String article = rowItem.getArticle();

        intent.putExtra(NEWS_ARTICLE_TITLE, title);
        intent.putExtra(NEWS_IMG_URL_BIG, imgURLBig);
        intent.putExtra(NEWS_ARTICLE_TEXT, article);
        intent.putExtra(NEWS_IMG_URL_SMALL, imgURLSmall);

        (this.getActivity()).startActivity(intent);
    }

    /**
     * Background Async Task to download file
     */
    private class DownloadFileFromURL extends AsyncTask<String, String, List<NewsRowItem>> {

        private List<NewsRowItem> items;

        ProgressDialog progressDialog;

        public String download(String downloadUrl, String fileName) throws IOException {

            File file = null;

            try {
                File dir = new File(getActivity().getExternalCacheDir().getPath());

                URL url = new URL(downloadUrl); //you can write here any link
                file = new File(dir, fileName);

                long startTime = System.currentTimeMillis();
                Log.d("DownloadManager", "download begining");
                Log.d("DownloadManager", "download url:" + url);
                Log.d("DownloadManager", "downloaded file name:" + fileName);

                URLConnection urlConnection = url.openConnection();

                InputStream is = urlConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                ByteArrayBuffer baf = new ByteArrayBuffer(5000);
                int current = 0;
                while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
                }

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(baf.toByteArray());
                fos.flush();
                fos.close();
                Log.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");

            } catch (IOException e) {
                Log.d("DownloadManager", "Error: " + e);
            }

            assert file != null;
            Log.d("FILE ", file.getCanonicalPath());
            return file.getCanonicalPath();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity(), "Почекайте", "Триває завантаження...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected List<NewsRowItem> doInBackground(String... fileInfo) {

            File file = new File(getActivity().getExternalCacheDir() + "/news.xml");

            if (isNetworkAvailable()) {
                try {
                    download(fileInfo[0], fileInfo[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            NewsXMLPullParser parser = new NewsXMLPullParser();

            try {
                items = parser.parse(file);
                Log.d("PARSING", "OK");
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return items;
        }

        @Override
        protected void onPostExecute(List<NewsRowItem> newsRowItems) {
            super.onPostExecute(newsRowItems);
            progressDialog.dismiss();
        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }
}
