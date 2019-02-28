package chrgames.decision.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import chrgames.decision.R;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
    }

    public void onClickSelectLanguage(View view) {

        switch (view.getId()) {
            case R.id.buttonLanguageEN:
                Toast.makeText(this, "SELECTED ENGLISH", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonLanguageRU:
                Toast.makeText(this, "ВЫБРАН РУССКИЙ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonLanguageUK:
                Toast.makeText(this, "ОБРАНО УКРАЇНСЬКУ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
