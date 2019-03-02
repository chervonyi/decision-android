package chrgames.decision.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import chrgames.decision.R;
import chrgames.decision.components.Settings;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        Settings.getInstance().setContext(this);

        int languageID = Settings.getStoredLanguage();

        if (languageID != -1) {
            Settings.language = Settings.Language.values()[languageID];

            Intent intent = new Intent(this, DisclaimerActivity.class);
            startActivity(intent);
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

        Intent intent = new Intent(this, DisclaimerActivity.class);
        startActivity(intent);
    }
}
