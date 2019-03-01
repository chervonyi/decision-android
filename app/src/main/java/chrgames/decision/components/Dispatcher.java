package chrgames.decision.components;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import chrgames.decision.activities.BlackScreenActivity;
import chrgames.decision.activities.ChapterActivity;
import chrgames.decision.activities.EndGameActivity;
import chrgames.decision.activities.MapActivity;
import chrgames.decision.activities.StageActivity;

public class Dispatcher {
    private static final String TAG = "MY_TEST";

    public static final String NEXT_STAGE_ID_CODE = "idOfNextStage";


    public static void send(Context context, String nextID) {

        Intent intent;

        if (nextID.equals("END")) {
            intent = new Intent(context, EndGameActivity.class);
            context.startActivity(intent);
            return;
        }

        Stage nextStage = Plot.getInstance().getStageById(nextID);


        Log.d(TAG, "onTouchEvent: go to " + nextID);

        switch (nextStage.getType()) {
            case CHAPTER:
                Log.d(TAG, "send: ChapterActivity");
                intent = new Intent(context, ChapterActivity.class);
                break;

            case SIMPLE:
            case CHOICE:
                Log.d(TAG, "send: StageActivity");
                intent = new Intent(context, StageActivity.class);
                break;

            case BLACK:
                Log.d(TAG, "send: BlackScreenActivity");
                intent = new Intent(context, BlackScreenActivity.class);
                break;

            case MAP:
                Log.d(TAG, "send: MapActivity");
                intent = new Intent(context, MapActivity.class);
                break;

            default:
                Log.d(TAG, "send: default");
                intent = new Intent(context, StageActivity.class);
        }

        intent.putExtra(NEXT_STAGE_ID_CODE, nextID);
        context.startActivity(intent);
    }

    public static void start(Context context) {
        send(context, Plot.getInstance().getLastStage());
    }
}
