package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;
import chrgames.decision.components.Stage;

public class StageActivity extends AppCompatActivity {

    private Stage stage;

    private int blockNumber = 0;


    // UIViews
    private TextView textViewMainBox;
    private TextView textViewChoice1;
    private TextView textViewChoice2;
    private TextView textViewChoice3;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        // Getting extra
        Intent intent = getIntent();
        stage = Plot.getInstance().getStageById(intent.getStringExtra(Dispatcher.NEXT_STAGE_ID));

        imageView = findViewById(R.id.imageView);
        textViewMainBox = findViewById(R.id.textViewMainBox);
        textViewChoice1 = findViewById(R.id.textViewChoice1);
        textViewChoice2 = findViewById(R.id.textViewChoice2);
        textViewChoice3 = findViewById(R.id.textViewChoice3);

        // Set image on background
        int imageID = getResources().getIdentifier(stage.getImage(), "drawable", getPackageName());
        imageView.setImageResource(imageID);

        setVisibility();

        // TODO Add Listeners


    }

    private void setVisibility() {
        if (stage.getType() == Stage.Type.SIMPLE) {
            textViewChoice1.setVisibility(View.GONE);
            textViewChoice2.setVisibility(View.GONE);
            textViewChoice3.setVisibility(View.GONE);
        } else {
            if (stage.getChoices().size() == 2) {
                textViewChoice1.setVisibility(View.VISIBLE);
                textViewChoice2.setVisibility(View.VISIBLE);
                textViewChoice3.setVisibility(View.GONE);
                textViewChoice1.setText(stage.getChoices().get(0));
                textViewChoice1.setText(stage.getChoices().get(1));
            } else if (stage.getChoices().size() == 3){
                textViewChoice1.setVisibility(View.VISIBLE);
                textViewChoice2.setVisibility(View.VISIBLE);
                textViewChoice3.setVisibility(View.VISIBLE);
                textViewChoice1.setText(stage.getChoices().get(0));
                textViewChoice1.setText(stage.getChoices().get(1));
                textViewChoice1.setText(stage.getChoices().get(2));
            }
        }
    }

}


