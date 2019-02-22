package chrgames.decision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
    }

    public void onClickRestart(View view) {
        Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show();
    }
}
