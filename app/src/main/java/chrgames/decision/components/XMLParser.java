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


                    ArrayList<String> nextTags = readWhile("next", "stage");

                    if (nextTags.size() == 1) {
                        stage = new Stage(id, type, nextTags.get(0));
                    } else if (nextTags.size() > 1) {
                        stage = new Stage(id, type, nextTags);
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


    public HashMap<String, Stage> readScenario(XmlResourceParser xmlFile, HashMap<String, Stage> plot) {
        eventType = -1;
        xml = xmlFile;

        while (eventType != XmlResourceParser.END_DOCUMENT) {

            try {
                if (eventType == XmlResourceParser.START_TAG &&
                        xml.getName().equalsIgnoreCase("stage")) {

                    String type = xml.getAttributeValue(0);
                    String id = getNextTag("id");
                    Stage stage = plot.get(id);
                    switch (type) {
                        case "chapter":
                            stage.setNumberOfChapter(getNextTag("number"));
                            stage.setTitle(getNextTag("title"));
                            break;

                        case "black":
                            stage.setText(readWhile("block", "text"));
                            break;

                        case "simple":
                            stage.setText(readWhile("block", "text"));
                            stage.setImage(getNextTag("image"));
                            break;

                        case "choice":
                            stage.setText(readWhile("block", "text"));

                        case "map":
                            stage.setImage(getNextTag("image"));
                            stage.setChoices(readWhile("choice", "stage"));
                            break;
                    }

                    // Update stage
                    plot.put(id, stage);
                }

                eventType = xml.next();
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }

        }

        return plot;
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

    /**
     * Read BLOCKs while it is not ENDBLOCK
     * @param block inner blocks
     * @param endBlock "exit" block
     * @return array of BLOCKs
     */
    private ArrayList<String> readWhile(String block, String endBlock) throws IOException, XmlPullParserException {
        ArrayList<String> vault = new ArrayList<>();

        while (eventType != XmlResourceParser.END_DOCUMENT) {

            if (eventType == XmlResourceParser.END_TAG &&
                    xml.getName().equalsIgnoreCase(endBlock)) {
                break;
            }

            if (eventType == XmlResourceParser.START_TAG &&
                    xml.getName().equalsIgnoreCase(block)) {
                xml.next();
                vault.add(xml.getText());
            }

            eventType = xml.next();
        }

        return vault;
    }
}
