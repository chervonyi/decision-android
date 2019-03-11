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
            MobileAds.initialize(this, "ca-app-pub-1247855442494877~9519300949");

            Settings.getInstance().setContext(this);

            Intent intent = new Intent(this, DisclaimerActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void onClickSelectLanguage(View view) {

        switch (view.getId()) {
            case R.id.buttonLanguageEN:
                Settings.saveNewLanguage(Settings.Language.ENGLISH);
                break;

            case R.id.buttonLanguageRU:
                Settings.saveNewLanguage(Settings.Language.RUSSIAN);
                break;

            case R.id.buttonLanguageUK:
                Settings.saveNewLanguage(Settings.Language.UKRAINIAN);
                break;
        }

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
