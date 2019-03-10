package chrgames.decision.components;

import android.content.Context;
import android.content.res.XmlResourceParser;

import java.util.HashMap;

import chrgames.decision.R;

public class Plot {
    // Fields
    private static final Plot ourInstance = new Plot();
    private HashMap<String, Stage> vault;
    private String lastStage;
    private final String theFirstStageEver = "AA000";

    // Getters
    public static Plot getInstance() {
        return ourInstance;
    }

    public HashMap<String, Stage> getPlot() { return vault; }

    public Stage getStageById(String idOfStage) {
        lastStage = idOfStage;
        Settings.saveLastStage(idOfStage);
        return vault.get(idOfStage);
    }

    public String getLastStage() {
        return lastStage == null ? theFirstStageEver : lastStage;
    }

    public String getTheFirstStageEver() { return theFirstStageEver; }

    // Setters
    public void setPlot(HashMap<String, Stage> plot) {
        this.vault = plot;
    }

    public void setLastStage(String stageID) {
        lastStage = stageID;
    }

    // Other methods
    public void restartGame() {
        lastStage = theFirstStageEver;
        Settings.saveLastStage(theFirstStageEver);
    }

    public void loadPLotFromXMl(Context context) {
        XMLParser xmlParser = new XMLParser();
        vault = xmlParser.readPlot(context.getResources().getXml(R.xml.plot));
    }

    public void loadScenarioFromXML(Context context) {
        XMLParser xmlParser = new XMLParser();
        XmlResourceParser xmlFile;

        // TODO: Change UK to EN, RU when it will be imported
        switch (Settings.getLanguage()) {
            case ENGLISH: xmlFile = context.getResources().getXml(R.xml.scenario_en); break;
            case RUSSIAN: xmlFile = context.getResources().getXml(R.xml.scenario_ru); break;
            case UKRAINIAN: xmlFile = context.getResources().getXml(R.xml.scenario_uk); break;
            default: xmlFile = context.getResources().getXml(R.xml.scenario_uk); break;
        }

        vault = xmlParser.readScenario(xmlFile, vault);
    }
}
