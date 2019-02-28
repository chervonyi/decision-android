package chrgames.decision.components;

import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class XMLParser {

    private XmlResourceParser xml;
    private final String TAG = "MY_TEST";
    private int eventType;

    public HashMap<String, Stage> readPlot(XmlResourceParser xmlFile) {

        eventType = -1;
        xml =  xmlFile;
        Stage stage = new Stage();
        HashMap<String, Stage> vault = new HashMap<>();

        while(eventType != XmlResourceParser.END_DOCUMENT) {

            try {
                if (eventType == XmlResourceParser.START_TAG &&
                        xml.getName().equalsIgnoreCase("stage")) {

                    String type = xml.getAttributeValue(0);
                    String id = getNextTag("id");


                    ArrayList<String> nextTags = new ArrayList<>();

                    while (eventType != XmlResourceParser.END_DOCUMENT) {

                        if (eventType == XmlResourceParser.END_TAG &&
                                xml.getName().equalsIgnoreCase("stage")) {
                            break;
                        }

                        if (eventType == XmlResourceParser.START_TAG &&
                                xml.getName().equalsIgnoreCase("next")) {
                            xml.next();
                            nextTags.add(xml.getText());
                        }

                        eventType = xml.next();
                    }

                    if (nextTags.size() == 1) {
                        stage = new Stage(id, type, nextTags.get(0));
                    } else if (nextTags.size() == 2) {
                        stage = new Stage(id, type, nextTags.get(0), nextTags.get(1));
                    } else if (nextTags.size() == 3) {
                        stage = new Stage(id, type, nextTags.get(0), nextTags.get(1), nextTags.get(2));
                    }

                    vault.put(id, stage);
                }

                eventType = xml.next();
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }

        }

        return vault;
    }


    private String getNextTag(String tagName) throws XmlPullParserException, IOException {
        int eventType = -1;

        while(eventType != XmlResourceParser.END_DOCUMENT) {

            if (eventType == XmlResourceParser.START_TAG && xml.getName().equalsIgnoreCase(tagName)) {
                xml.next();
                String text = xml.getText();
                xml.next();
                return text;
            }

            eventType = xml.next();
        }

        return "DATA_NOT_FOUND";
    }


}
