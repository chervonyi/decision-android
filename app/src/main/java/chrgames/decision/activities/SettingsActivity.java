package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;

public class SettingsActivity extends AppCompatActivity {

    private String pauesdStageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        pauesdStageID = intent.getStringExtra("pausedStageID");
    }

    public void onClickResume(View view) {
        Dispatcher.send(this, pauesdStageID);
    }

    public void onClickRestart(View view) {
        Plot.getInstance().restartGame();
        Dispatcher.start(this);
    }

    public void onClickChangeLanguage(View view) {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra("pausedStageID", pauesdStageID);
        startActivity(intent);
    }
}
