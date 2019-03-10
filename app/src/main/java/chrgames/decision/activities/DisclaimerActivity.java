package chrgames.decision.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Locale;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;
import chrgames.decision.components.Settings;

public class DisclaimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Settings.updateLocal(this);
        setContentView(R.layout.activity_disclaimer);

        // Load plot.xml
        Plot.getInstance().loadPLotFromXMl(this);

        // Load scenario
        Plot.getInstance().loadScenarioFromXML(this);

        // Getting last stage from memory
        String storedLastStage = Settings.getStoredLastStage();
        Plot.getInstance().setLastStage(storedLastStage);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            Dispatcher.start(this);
            finish();
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
