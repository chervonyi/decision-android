package chrgames.decision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class BlackScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_screen);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            //Toast.makeText(this, "PRESSED", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MapActivity.class));
        }

        return super.onTouchEvent(event);
    }
}
