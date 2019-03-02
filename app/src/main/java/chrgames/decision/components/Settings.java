package chrgames.decision.components;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private final static Settings instance = new Settings();

    public enum Languages {
        ENGLISH,
        RUSSIAN,
        UKRAINIAN
    }

    private static SharedPreferences pref;
    private static final String preferencesName = "settings_preferences";
    private static final String lastStageName = "last_stage";
    private static final String languageName = "language";

    public void setContext(Context context) {
        pref = context.getSharedPreferences(Settings.preferencesName, Context.MODE_PRIVATE);
    }

    static void saveLastStage(String lastStageID) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(Settings.lastStageName);
        editor.putString(Settings.lastStageName, lastStageID);
        editor.apply();
    }

    public static String getStoredLastStage() {
        return pref.getString(Settings.lastStageName, Plot.getInstance().getTheFirstStageEver());
    }

    public static Settings getInstance() {
        return instance;
    }
}
