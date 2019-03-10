package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import chrgames.decision.R;
import chrgames.decision.components.Dispatcher;
import chrgames.decision.components.Plot;
import chrgames.decision.components.Stage;

public class BlackScreenActivity extends AppCompatActivity {

    private Stage stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_screen);

        Intent intent = getIntent();
        stage = Plot.getInstance().moveToNextStage(intent.getStringExtra(Dispatcher.NEXT_STAGE_ID));

        TextView textViewBlackScreenText = findViewById(R.id.textViewBlackScreenText);
        textViewBlackScreenText.setText(stage.getText().get(0));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            Dispatcher.send(this, stage.getNextID());
            finish();
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
