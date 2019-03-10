package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;
import chrgames.decision.components.Settings;

public class SettingsActivity extends AppCompatActivity {

    private String pauesdStageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.updateLocal(this);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        pauesdStageID = intent.getStringExtra("pausedStageID");
    }

    public void onClickResume(View view) {
        Dispatcher.send(this, pauesdStageID);
        finish();
    }

    public void onClickRestart(View view) {
        Plot.getInstance().restartGame();
        Dispatcher.start(this);
        finish();
    }

    public void onClickChangeLanguage(View view) {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra("pausedStageID", pauesdStageID);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
