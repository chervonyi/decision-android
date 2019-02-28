package chrgames.decision.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import chrgames.decision.R;
import chrgames.decision.components.XMLParser;

public class StageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);


        XMLParser xmlParser = new XMLParser();
        xmlParser.readPlot(this.getResources().getXml(R.xml.plot));
    }

}
