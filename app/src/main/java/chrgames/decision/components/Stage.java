package chrgames.decision.components;

import android.util.Log;

public class Stage {
    private final String TAG = "MY_TEST";

    public Stage() {}

    public Stage(String id, String type, String next1) {
        Log.d(TAG, "Created Stage with: \n" + id + "\n" + type + "\n" + next1 + "\n\n");
    }

    public Stage(String id, String type, String next1, String next2) {
        Log.d(TAG, "Created Stage with: \n" + id + "\n" + type + "\n" + next1 + "\n" + next2 + "\n\n");
    }

    public Stage(String id, String type, String next1, String next2, String next3) {
        Log.d(TAG, "Created Stage with: \n" + id + "\n" + type + "\n" + next1 + "\n" + next2 + "\n" + next3 +"\n\n");
    }
}
