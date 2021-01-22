package chatPackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that represents a chat correspondence
 */
public class Chat {
    private List<ChatLine> lines;
    private List<UserDialog> observers;

    private int isBold = Font.PLAIN;
    private String font = "Arial";

    /**
     * creates a new empty chat with no observers
     */
    public Chat(){
        lines =  new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * @param fontName - new font wanted for chat
     * @requires - font is a String and a valid font name
     */
    public void changeFont(String fontName){
        this.font = fontName;
    }

    /**
     * @return font name
     */
    public String getFont(){
        return this.font;
    }

    /**
     * @param isBold - bollean that decides if writing is in bold or not
     */
    public void setBold(int isBold){
        this.isBold = isBold;
    }

    /**
     * @return if writing is in bold or not
     */
    public int isBold(){
        return this.isBold;
    }

    /**
     * method that notifies all Dialog Boxes that a change to the correspondence or settings was made
     */
    public void notifyObservers(){
        for (UserDialog dialog : observers){
            dialog.update();
        }
    }

    /**
     * adds a Chat line to the correspondence
     * @param line - a valid Chatline to be added
     */
    public void addLine(ChatLine line){
        lines.add(new ChatLine(line));
        notifyObservers();
    }

    /**
     * adds a new dialogBox to observe the chat
     * @param dialog - a UserDialog box
     */
    public void addObserver(UserDialog dialog){
        observers.add(dialog);
    }
    public Iterator<ChatLine> getChatLines() {
        ArrayList<ChatLine>newLines = new ArrayList<ChatLine>(this.lines);
        return newLines.iterator();
    }


}

