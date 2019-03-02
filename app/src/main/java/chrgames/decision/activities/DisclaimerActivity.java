package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.HashMap;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;
import chrgames.decision.components.Settings;
import chrgames.decision.components.Stage;
import chrgames.decision.components.XMLParser;

public class DisclaimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        Settings.getInstance().setContext(this);

        XMLParser xmlParser = new XMLParser();
        Plot.getInstance().setPlot(xmlParser.readPlot(getResources().getXml(R.xml.plot)));

        Toast.makeText(this, "Selected: " + Settings.language, Toast.LENGTH_SHORT).show();

        // TODO: Load appropriate scenario.xml. (Using Settings.language)
        Plot.getInstance().setPlot(
                xmlParser.readScenario(getResources().getXml(R.xml.scenario_uk),
                        Plot.getInstance().getPlot()));

        // Getting last stage from memory
        String storedLastStage = Settings.getStoredLastStage();
        Plot.getInstance().setLastStage(storedLastStage);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            Dispatcher.start(this);
        }

        return super.onTouchEvent(event);
    }
}
