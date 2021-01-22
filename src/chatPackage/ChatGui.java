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
    private Map<User, JFrame> dialogs;
    JFrame frame;
    JPanel buttonsPanel = new JPanel();
    JPanel chatBoxes = new JPanel();
    FontsPopup fontsPopup;


    public ChatGui(JFrame frame) {
        chat = new Chat();
        dialogs = new HashMap<>();


        this.frame = frame;
        this.frame.setVisible(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();

        fontsPopup = new FontsPopup(frame, this, chat);
        fontsPopup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fontsPopup.pack();


        JButton normalButton = new JButton("Normal");
        normalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chat.setBold(Font.PLAIN);
                chat.changeFont("Arial");
                chat.notifyObservers();
            }
        });

        JButton boldButton = new JButton("Bold");
        boldButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chat.setBold((chat.isBold() + 1) % 2);
                chat.notifyObservers();
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
        User user = new User(dialogs.size(),userName);
        UserDialog dialog = new UserDialog(user, chat, frame);
        chatBoxes.add(dialog);
        dialogs.put(user,frame);
        chat.addObserver(dialog);
        frame.pack();
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