package homework4;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Class that represents a chat correspondence
 */
public class Chat {

    // Abs. Function
    // 	represents a Chat between multiple users.
    // 	isBold decides on the boldness of the font.
    //	font is the fontName chosen.
    //
    // Rep. Invariant:
    //  no attribute is null
    //  font is a valid font under java gui preferences



    private List<ChatLine> lines;
    private List<UserDialog> observers;

    private int isBold = Font.PLAIN;
    private String font = "Arial";

    private void checkRep(){
        assert (lines != null);
        assert (observers != null);
        Set<String> validFonts = new HashSet<String>
                (Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        assert (validFonts.contains(font));
    }

    /**
     * creates a new empty chat with no observers
     */
    public Chat(){
        lines =  new ArrayList<>();
        observers = new ArrayList<>();
        checkRep();
    }

    /**
     * @param fontName - new font wanted for chat
     * @requires - font is a String and a valid font name
     */
    public void changeFont(String fontName){
        this.font = fontName;
        notifyObservers();
        checkRep();
    }

    /**
     * @return font name
     */
    public String getFont(){
        checkRep();
        return this.font;
    }

    /**
     * @param isBold - bollean that decides if writing is in bold or not
     * @requieres - isBold == 0 || isBold == 1
     */
    public void setBold(int isBold){
        checkRep();
        this.isBold = isBold;
        notifyObservers();
        checkRep();
    }

    /**
     * @return if writing is in bold or not
     */
    public int isBold(){
        checkRep();
        return this.isBold;
    }

    /**
     * adds a Chat line to the correspondence
     * @param line - a valid Chatline to be added
     */
    public void addLine(ChatLine line){
        checkRep();
        lines.add(new ChatLine(line));
        notifyObservers();
        checkRep();
    }

    /**
     * adds a new dialogBox to observe the chat
     * @param dialog - a UserDialog box
     * @modifies this
     */
    public void addObserver(UserDialog dialog){
        checkRep();
        observers.add(dialog);
        checkRep();
    }

    /**
     *
     * @return and Iterator to iterate over all lines of the chat
     */
    public Iterator<ChatLine> getChatLines() {
        checkRep();
        ArrayList<ChatLine>newLines = new ArrayList<ChatLine>(this.lines);
        checkRep();
        return newLines.iterator();
    }

    /**
     * method that notifies all Dialog Boxes that a change to the correspondence or settings was made
     *@modifies this
     */
    public void notifyObservers(){
        checkRep();
        for (UserDialog dialog : observers){
            dialog.update();
        }
        checkRep();
    }


}

