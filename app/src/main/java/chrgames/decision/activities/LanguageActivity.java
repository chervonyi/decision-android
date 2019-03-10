package chrgames.decision.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;
import chrgames.decision.components.Settings;

public class LanguageActivity extends AppCompatActivity {

    private String nextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        nextID = getIntent().getStringExtra("pausedStageID");

        if (nextID == null) {
            // Enter point
            Log.d("MY_TEST", "START POINT");
            MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

            Settings.getInstance().setContext(this);

            Intent intent = new Intent(this, DisclaimerActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void onClickSelectLanguage(View view) {

        // Save new language into phone memory
        String lang = "en";
        switch (view.getId()) {
            case R.id.buttonLanguageEN:
                Settings.saveNewLanguage(Settings.Language.ENGLISH);
                lang = "en";
                break;

            case R.id.buttonLanguageRU:
                Settings.saveNewLanguage(Settings.Language.RUSSIAN);
                lang = "ru";
                break;

            case R.id.buttonLanguageUK:
                Settings.saveNewLanguage(Settings.Language.UKRAINIAN);
                lang = "uk";
                break;
        }

        // TODO: Change this segment of code on static method from Setting.class

        // Update UI Language
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        // Update scenario with appropriate language
        Plot.getInstance().loadScenarioFromXML(this);
        Dispatcher.send(this, nextID);
        finish();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
