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

public class ChapterActivity extends AppCompatActivity {

    private Stage currentStage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        // Getting extra from intent
        Intent intent = getIntent();
        currentStage = Plot.getInstance().moveToNextStage(intent.getStringExtra(Dispatcher.NEXT_STAGE_ID));

        // Set content
        TextView textViewChapterNumber = findViewById(R.id.textViewChapterNumber);
        textViewChapterNumber.setText(currentStage.getNumberOfChapter());

        TextView textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(currentStage.getTitle());

        TextView textViewChapterDate = findViewById(R.id.textViewChapterDate);
        textViewChapterDate.setText("");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            Dispatcher.send(this, currentStage.getNextID());
            finish();
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
