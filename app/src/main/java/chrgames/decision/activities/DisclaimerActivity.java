package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;

import java.util.HashMap;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;
import chrgames.decision.components.Stage;
import chrgames.decision.components.XMLParser;

public class DisclaimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);


        XMLParser xmlParser = new XMLParser();
        Plot.getInstance().setPlot(xmlParser.readPlot(getResources().getXml(R.xml.plot)));

        Plot.getInstance().setPlot(
                xmlParser.readScenario(getResources().getXml(R.xml.test),
                        Plot.getInstance().getPlot()));


        // TODO: Get language from Memory and upload appropriate scenario
        // TODO: Get lastStage from Memory and set it into Plot.class
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            Dispatcher.start(this);
        }

        return super.onTouchEvent(event);
    }
}
