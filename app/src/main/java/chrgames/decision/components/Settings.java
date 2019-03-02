package chrgames.decision.components;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private final static Settings instance = new Settings();

    public enum Language {
        ENGLISH,
        RUSSIAN,
        UKRAINIAN
    }

    private static SharedPreferences pref;
    private static final String preferencesName = "settings_preferences";
    private static final String lastStageName = "last_stage";
    private static final String languageName = "language";

    public static Language language;

    public void setContext(Context context) {
        if (pref == null) {
            pref = context.getSharedPreferences(Settings.preferencesName, Context.MODE_PRIVATE);
        }
    }

    public static void saveNewLanguage(Language newLanguage) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(Settings.languageName);
        editor.putInt(Settings.languageName, newLanguage.ordinal());
        editor.apply();

        Settings.language = newLanguage;
    }

    public static int getStoredLanguage() {
        return pref.getInt(Settings.languageName, -1);
    }

    public static void saveLastStage(String lastStageID) {
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
