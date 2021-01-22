package chatPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * A JPanel GUI for representing a Chat.
 * holds multiple chat boxes for different users
 */
public class ChatGui extends JPanel{

    // Abs. Function
    // 	represents a Chat control interface.
    // 	all buttons are under buttonsPanel.
    //	all chatboxes are under chatBoxes.
    //
    // Rep. Invariant:
    //  no 2 users have the same ID - checked cause userIDs are saved in a set
    //  no attribute is null
    //  usersAmount >= 0

    private Chat chat;
    JFrame frame;
    JPanel buttonsPanel = new JPanel();
    JPanel chatBoxes = new JPanel();
    FontsPopup fontsPopup;
    int usersAmount;
    Set<Integer> userIDs;

    void checkRep(){
        assert (chat != null);
        assert (buttonsPanel != null);
        assert (chatBoxes != null);
        assert (fontsPopup != null);
        assert (fontsPopup != userIDs);
        assert (usersAmount >= 0);
    }

    /**
     * creates a new ChatGui
     * @param frame - frame that owns this JPanel
     */
    public ChatGui(JFrame frame) {
        chat = new Chat();
        usersAmount = 0;
        userIDs = new HashSet<>();

        this.frame = frame;
        this.frame.setVisible(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();

        fontsPopup = new FontsPopup(frame, this);
        fontsPopup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fontsPopup.pack();

        // listen to "Normal" button
        JButton normalButton = new JButton("Normal");
        normalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBold(Font.PLAIN);
                changeFont("Arial");
            }
        });

        // listen to "Bold" button
        JButton boldButton = new JButton("Bold");
        boldButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chat.setBold((chat.isBold() + 1) % 2);
            }
        });

        // listen to "Choose Font" button
        JButton fontButton = new JButton("Choose Font");
        fontButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fontsPopup.setVisible(true);
            }
        });

        buttonsPanel.add(normalButton);
        buttonsPanel.add(boldButton);
        buttonsPanel.add(fontButton);

        chatBoxes.setLayout(new GridLayout(3,3));
        this.add(chatBoxes);
        this.add(buttonsPanel);
        checkRep();
    }

    /**
     * changes text font
     * @param fontName - requested font
     * @modifies - chat
     * @return
     */
    protected boolean validateChat(Chat chat){
        checkRep();
        return (this.chat.equals(chat));
    }
    /**
     * changes text font
     * @param fontName - requested font
     * @modifies - chat
     */
    public void changeFont(String fontName){
        checkRep();
        chat.changeFont(fontName);
        checkRep();
    }

    /**
     * changes text font
     * @param isBold
     * @modifies - chat
     */
    public void setBold(int isBold){
        checkRep();
        chat.setBold(isBold);
        checkRep();
    }

    /**
     * adds a new user chat box for agile usage
     * @param userName
     */
    public void addUserToChat(String userName){
        checkRep();
        User user = new User(usersAmount,userName);
        userIDs.add(usersAmount);
        UserDialog dialog = new UserDialog(user, chat, this);
        chatBoxes.add(dialog);
        chat.addObserver(dialog);
        frame.pack();
        usersAmount++;
        checkRep();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat Control Panel");
        Container contentPane = frame.getContentPane();
        ChatGui gui = new ChatGui(frame);
        contentPane.add(gui);
        gui.addUserToChat("Nadav");
        gui.addUserToChat("Shlomi");
        gui.addUserToChat("Alon");
        gui.addUserToChat("Ofek");
        gui.addUserToChat("Benjamin");
        gui.addUserToChat("Dor");

    }
}