package chrgames.decision.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import chrgames.decision.R;
import chrgames.decision.components.Settings;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        // TODO: UNCOMENT THIS LINE Settings.getInstance().setContext(this);
    }

    public void onClickSelectLanguage(View view) {

        switch (view.getId()) {
            case R.id.buttonLanguageEN:
                //Toast.makeText(this, "SELECTED ENGLISH", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonLanguageRU:
                //Toast.makeText(this, "ВЫБРАН РУССКИЙ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonLanguageUK:
                //Toast.makeText(this, "ОБРАНО УКРАЇНСЬКУ", Toast.LENGTH_SHORT).show();
                break;
        }

        Intent intent = new Intent(this, DisclaimerActivity.class);
        startActivity(intent);
    }
}
