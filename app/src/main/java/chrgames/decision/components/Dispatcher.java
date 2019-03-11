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

    public static final String NEXT_STAGE_ID = "idOfNextStage";

    public static void send(Context context, String nextID) {

        Intent intent;

        if (nextID.equals("END")) {
            intent = new Intent(context, EndGameActivity.class);
            context.startActivity(intent);
            return;
        }

        Stage nextStage = Plot.getInstance().moveToNextStage(nextID);

        switch (nextStage.getType()) {
            case CHAPTER:
                intent = new Intent(context, ChapterActivity.class);
                break;

            case SIMPLE:
            case CHOICE:
                intent = new Intent(context, StageActivity.class);
                break;

            case BLACK:
                intent = new Intent(context, BlackScreenActivity.class);
                break;

            case MAP:
                intent = new Intent(context, MapActivity.class);
                break;

            default:
                intent = new Intent(context, StageActivity.class);
        }

        intent.putExtra(NEXT_STAGE_ID, nextID);
        context.startActivity(intent);
    }

    public static void start(Context context) {
        send(context, Plot.getInstance().getLastStage());
    }
}
