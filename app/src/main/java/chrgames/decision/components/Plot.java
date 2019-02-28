package chrgames.decision.components;

import java.util.HashMap;

public class Plot {
    // Fields
    private static final Plot ourInstance = new Plot();
    private HashMap<String, Stage> vault;
    private String lastStage;
    public final String theFirstStageEver = "AA000";

    private Plot() {  }

    // Getters
    public static Plot getInstance() {
        return ourInstance;
    }

    public HashMap<String, Stage> getPlot() { return vault; }

    public Stage getStageById(String idOfStage) {
        lastStage = idOfStage;
        return vault.get(idOfStage);
    }

    public String getLastStage() {
        return lastStage == null ? theFirstStageEver : lastStage;
    }

    // Setters
    public void setPlot(HashMap<String, Stage> plot) {
        this.vault = plot;
    }

    public void loadLastStageFromMemory(String stageID) {
        lastStage = stageID;
    }


}
