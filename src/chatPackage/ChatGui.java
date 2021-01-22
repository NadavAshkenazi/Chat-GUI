package chatPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A JPanel GUI for representing a Route. This Route is shown as a list of
 * GeoSegments. In addition, walking directions and driving directions for
 * traversing this route are shown.
 * <p>
 * A figure showing this GUI can be found in homework assignment #1.
 */
public class ChatGui extends JPanel{

    // some of the controls contained in this
    private Chat chat;
    JFrame frame;
    JPanel buttonsPanel = new JPanel();
    JPanel chatBoxes = new JPanel();
    FontsPopup fontsPopup;
    int usersAmount;


    public ChatGui(JFrame frame) {
        chat = new Chat();
        usersAmount = 0;

        this.frame = frame;
        this.frame.setVisible(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();

        fontsPopup = new FontsPopup(frame, this);
        fontsPopup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fontsPopup.pack();


        JButton normalButton = new JButton("Normal");
        normalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBold(Font.PLAIN);
                changeFont("Arial");
            }
        });

        JButton boldButton = new JButton("Bold");
        boldButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chat.setBold((chat.isBold() + 1) % 2);
            }
        });

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
    }

    public void changeFont(String fontName){
        chat.changeFont(fontName);
    }

    public void setBold(int isBold){
        chat.setBold(isBold);
    }

    public void addUserToChat(String userName){
        User user = new User(usersAmount,userName);
        UserDialog dialog = new UserDialog(user, chat);
        chatBoxes.add(dialog);
        chat.addObserver(dialog);
        frame.pack();
        usersAmount++;
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