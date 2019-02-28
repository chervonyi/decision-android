package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import chrgames.decision.R;

public class ChapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            //Toast.makeText(this, "PRESSED", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, BlackScreenActivity.class));
        }

        return super.onTouchEvent(event);
    }
}
