package chrgames.decision.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import chrgames.decision.R;

public class DisclaimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            //Toast.makeText(this, "PRESSED", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ChapterActivity.class);
            startActivity(intent);
        }

        return super.onTouchEvent(event);
    }
}
