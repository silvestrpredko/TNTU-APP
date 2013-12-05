package ua.edu.tntu.info;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InfoXMLPullParser {

    List<InfoRowItem> infoRowItems;
    private InfoRowItem infoRowItem;
    private String text;

    public InfoXMLPullParser() {
        infoRowItems = new ArrayList<InfoRowItem>();
    }

    public List<InfoRowItem> parse(InputStream inputStream) throws XmlPullParserException, IOException {

        XmlPullParserFactory factory;
        XmlPullParser parser;

        int readLimit = 32 * 1024;
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
                            // create a new instance of infoRowItem
                            infoRowItem = new InfoRowItem();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            // add infoRowItem object to list
                            infoRowItems.add(infoRowItem);
                        } else if (tagName.equalsIgnoreCase("title")) {
                            infoRowItem.setTitle(text);
                        } else if (tagName.equalsIgnoreCase("id")) {
                            infoRowItem.setId(Integer.parseInt(text));
                        } else if (tagName.equalsIgnoreCase("article")) {
                            infoRowItem.setArticle(text);
                        } else if (tagName.equalsIgnoreCase("img")) {
                            infoRowItem.setImage(text);
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

        return infoRowItems;
    }
}
