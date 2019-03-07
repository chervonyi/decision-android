package chrgames.decision.components;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Stage {

    public enum Type {
        SIMPLE,
        CHOICE,
        CHAPTER,
        BLACK,
        MAP
    }

    private final String TAG = "MY_TEST";

    // States
    private String ID;
    private Type type;
    private String image;

    private ArrayList<String> text;
    private ArrayList<String> choices;

    private String nextID;
    private ArrayList<String> nextIDforChoices;

    private String numberOfChapter;
    private String title;


    // Constructors

    public Stage() {}

    public Stage(String id, String type, String next) {
        this.ID = id;
        setType(type);
        this.nextID = next;
    }

    public Stage(String id, String type, ArrayList<String> nextIDs) {
        this.ID = id;
        setType(type);
        this.nextIDforChoices = nextIDs;
    }

    private void setType(String type) {
        switch (type) {
            case "simple": this.type = Type.SIMPLE; break;
            case "chapter": this.type = Type.CHAPTER; break;
            case "choice": this.type = Type.CHOICE; break;
            case "map": this.type = Type.MAP; break;
            case "black": this.type = Type.BLACK; break;
        }
    }

    public boolean isNextStageIsChapter() {
        if (type == Type.SIMPLE) {
            if (nextID.equals("END")) {
                // Next stage - EndGameActivity.class
                return false;
            }

            return Plot.getInstance().getStageById(nextID).type == Type.CHAPTER;
        }

        return false;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public void setNumberOfChapter(String numberOfChapter) {
        this.numberOfChapter = numberOfChapter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getID() {
        return ID;
    }

    public Type getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public String getNextID() {
        return nextID;
    }

    public ArrayList<String> getNextIDforChoices() {
        return nextIDforChoices;
    }

    public String getNumberOfChapter() {
        return numberOfChapter;
    }

    public String getTitle() {
        return title;
    }
}
