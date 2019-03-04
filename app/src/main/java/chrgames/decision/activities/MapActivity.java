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

public class MapActivity extends AppCompatActivity {

    private Stage stage;

    private Context context;

    // UIViews
    private TextView textViewMapChoice1;
    private TextView textViewMapChoice2;
    private TextView textViewMapChoice3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        context = this;

        Intent intent = getIntent();
        stage = Plot.getInstance().getStageById(intent.getStringExtra(Dispatcher.NEXT_STAGE_ID_CODE));

        ImageView imageView = findViewById(R.id.imageView2);
        textViewMapChoice1 = findViewById(R.id.textViewMapChoice1);
        textViewMapChoice2 = findViewById(R.id.textViewMapChoice2);
        textViewMapChoice3 = findViewById(R.id.textViewMapChoice3);

        int imageID = getResources().getIdentifier(stage.getImage(), "drawable", getPackageName());
        imageView.setImageResource(imageID);

        textViewMapChoice1.setVisibility(View.VISIBLE);
        textViewMapChoice2.setVisibility(View.VISIBLE);

        textViewMapChoice1.setText(stage.getChoices().get(0));
        textViewMapChoice2.setText(stage.getChoices().get(1));

        if (stage.getChoices().size() == 3) {
            textViewMapChoice3.setVisibility(View.VISIBLE);
            textViewMapChoice3.setText(stage.getChoices().get(2));
        } else {
            textViewMapChoice3.setVisibility(View.GONE);
        }

        setListeners();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {

        textViewMapChoice1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    Dispatcher.send(context, stage.getNextIDforChoices().get(0));
                }
                return true;
            }
        });

        textViewMapChoice2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    Dispatcher.send(context, stage.getNextIDforChoices().get(1));
                }
                return true;
            }
        });

        if (stage.getChoices().size() == 3) {
            textViewMapChoice3.setOnTouchListener(new View.OnTouchListener() {
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

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("pausedStageID", stage.getID());
        startActivity(intent);
    }
}
