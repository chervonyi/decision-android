package chrgames.decision.components;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

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

    private final Language LANGUAGE_BY_DEFAULT = Language.ENGLISH;

    private static Language language;


    public void setContext(Context context) {
        if (pref == null) {
            pref = context.getSharedPreferences(Settings.preferencesName, Context.MODE_PRIVATE);
        }

        int langIndex = pref.getInt(Settings.languageName, -1);

        // Check if language had been saved before
        if (langIndex != -1) {

            // Set language from phone memory
            language = Language.values()[langIndex];

        } else {
            // Language had not found in phone memory
            // Looking for selected language in phone's settings.
            switch (Locale.getDefault().getISO3Language()) {
                case "eng": language = Language.ENGLISH; break;

                case "rus": language = Language.RUSSIAN; break;

                case "ukr": language = Language.UKRAINIAN; break;

                default: language = LANGUAGE_BY_DEFAULT;
            }
        }

    }

    public static void updateLocal(Context context) {
        String lang = "en";
        switch (language) {
            case RUSSIAN: lang = "ru"; break;
            case UKRAINIAN: lang = "uk"; break;
        }

        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public static void saveNewLanguage(Language newLanguage) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(Settings.languageName);
        editor.putInt(Settings.languageName, newLanguage.ordinal());
        editor.apply();

        Settings.language = newLanguage;
    }

    public static Language getLanguage() {
        return language;
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
