package chrgames.decision.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;
import chrgames.decision.components.Stage;

public class StageActivity extends AppCompatActivity {

    private Stage stage;
    private Context context;
    private int blockNumber = 0;

    // UIViews
    private TextView textViewMainBox;
    private TextView textViewChoice1;
    private TextView textViewChoice2;
    private TextView textViewChoice3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        context = this;

        // Getting extra
        Intent intent = getIntent();
        stage = Plot.getInstance().getStageById(intent.getStringExtra(Dispatcher.NEXT_STAGE_ID_CODE));

        ImageView imageView = findViewById(R.id.imageView);
        textViewMainBox = findViewById(R.id.textViewMainBox);
        textViewChoice1 = findViewById(R.id.textViewChoice1);
        textViewChoice2 = findViewById(R.id.textViewChoice2);
        textViewChoice3 = findViewById(R.id.textViewChoice3);

        // Set image on background
        int imageID = getResources().getIdentifier(stage.getImage(), "drawable", getPackageName());
        imageView.setImageResource(imageID);

        updateButtonSettings();

        setListeners();

        // Display text at mainTextBox
        changeBox();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        if (stage.getType() == Stage.Type.CHOICE) {
            textViewChoice1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_UP) {
                        Dispatcher.send(context, stage.getNextIDforChoices().get(0));
                    }

                    return true;
                }
            });

            textViewChoice2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_UP) {
                        Dispatcher.send(context, stage.getNextIDforChoices().get(1));
                    }
                    return true;
                }
            });

            if (stage.getChoices().size() == 3) {
                textViewChoice3.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent e) {
                        if (e.getAction() == MotionEvent.ACTION_UP) {
                            Dispatcher.send(context, stage.getNextIDforChoices().get(2));
                        }

                        return true;
                    }
                });

            }
        }

        textViewMainBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    if (blockNumber == stage.getText().size()) {
                        if (stage.getType() == Stage.Type.SIMPLE) {
                            Dispatcher.send(context, stage.getNextID());
                        }
                        return true;
                    }

                    changeBox();
                }

                return true;
            }
        });
    }

    private void changeBox() {
        textViewMainBox.setText(stage.getText().get(blockNumber++));

        if (stage.getType() == Stage.Type.CHOICE && blockNumber == stage.getText().size()) {
            textViewChoice1.setVisibility(View.VISIBLE);
            textViewChoice2.setVisibility(View.VISIBLE);

            if (stage.getChoices().size() == 3) {
                textViewChoice3.setVisibility(View.VISIBLE);
            }
        }
    }

    private void updateButtonSettings() {
        textViewChoice1.setVisibility(View.GONE);
        textViewChoice2.setVisibility(View.GONE);
        textViewChoice3.setVisibility(View.GONE);

        if (stage.getType() == Stage.Type.CHOICE) {
            textViewChoice1.setText(stage.getChoices().get(0));
            textViewChoice2.setText(stage.getChoices().get(1));

            if (stage.getChoices().size() == 3){
                textViewChoice3.setText(stage.getChoices().get(2));
            }
        }
    }

    public void onClickSettings(View view) {
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
    }
}


