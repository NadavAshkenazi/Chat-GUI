package chatPackage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A JPanel GUI for representing a Route. This Route is shown as a list of
 * GeoSegments. In addition, walking directions and driving directions for
 * traversing this route are shown.
 * <p>
 * A figure showing this GUI can be found in homework assignment #1.
 */
public class ChatGui{

    // some of the controls contained in this
    private Chat chat;
    private Map<User, JFrame> dialogs;



    public ChatGui() {
        chat = new Chat();
        dialogs = new HashMap<>();
    }

    public void addUserToChat(String userName){
        User user = new User(dialogs.size(),userName);
        JFrame frame = new JFrame(user.getName() + "s Chat:");
        Container contentPane = frame.getContentPane();
        UserDialog dialog = new UserDialog(user, chat, frame);
        contentPane.add(dialog);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        dialogs.put(user,frame);
        chat.addObserver(dialog);
    }


    public static void main(String[] args) {
        ChatGui gui = new ChatGui();

        gui.addUserToChat("Nadav");
        gui.addUserToChat("Shlomi");
        gui.addUserToChat("Alon");
        gui.addUserToChat("Ofek");

    }
}