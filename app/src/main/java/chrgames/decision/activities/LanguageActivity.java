package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

        Settings.getInstance().setContext(this);

        String pausedStageID = getIntent().getStringExtra("pausedStageID");

        if (pausedStageID == null) {
            int languageID = Settings.getStoredLanguage();
            if (languageID != -1) {
                Settings.language = Settings.Language.values()[languageID];

                Intent intent = new Intent(this, DisclaimerActivity.class);
                startActivity(intent);
            }
        } else {
            nextID = pausedStageID;
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

        if (nextID != null) {
            Plot.getInstance().loadScenarioFromXML(this);
            Dispatcher.send(this, nextID);
            return;
        }

        Intent intent = new Intent(this, DisclaimerActivity.class);
        startActivity(intent);
    }
}
