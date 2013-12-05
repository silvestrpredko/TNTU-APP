package ua.edu.tntu.news;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NewsXMLPullParser {
    List<NewsRowItem> newsItems;
    private NewsRowItem newsItem;
    private String text;

    public NewsXMLPullParser() {
        newsItems = new ArrayList<NewsRowItem>();
    }

    public List<NewsRowItem> parse(File file) throws XmlPullParserException, IOException {

        XmlPullParserFactory factory;
        XmlPullParser parser;

        int readLimit = 32 * 1024;
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file), readLimit);
        inputStream.mark(readLimit);

        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = parser.getName();

                switch (eventType) {

                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            // create a new instance of newsItem
                            newsItem = new NewsRowItem();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            // add newsItem object to list
                            newsItems.add(newsItem);
                        } else if (tagName.equalsIgnoreCase("title")) {
                            newsItem.setTitle(text);
                        } else if (tagName.equalsIgnoreCase("id")) {
                            newsItem.setId(Integer.parseInt(text));
                        } else if (tagName.equalsIgnoreCase("article")) {
                            newsItem.setArticle(text);
                        } else if (tagName.equalsIgnoreCase("imgSmall")) {
                            newsItem.setImageSmall(text);
                        } else if (tagName.equalsIgnoreCase("imgBig")) {
                            newsItem.setImageBig(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsItems;
    }
}
