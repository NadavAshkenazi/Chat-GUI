package chatPackage;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 * a class that represents the user interface for a single user
 * allows viewing correspondence and entering text
 */
class UserDialog extends JPanel implements Observer {

    // Abs. Function
    // 	represents a Chat box of a certain user.
    //  chat is a reference to tha main chat
    //  user is the owner

    // Rep. Invariant:
    //  no attribute is Null
    //  chat points to the main chat

    private static final int ENTER = 10;
    private static final long serialVersionUID = 1L;

    private JTextPane textArea;
    private JTextPane textInsertion;

    private Chat chat;
    private User user;
    ChatGui parent;

    private void checkRep(){
        assert (textArea != null);
        assert (textArea != null);
        assert (textArea != null);
        assert (textArea != null);
        assert (textArea != null);
        assert (parent.validateChat(this.chat));
    }
    /**
     * creates a new UserDialog
     * @param user - user that owns this particular panel
     * @param chat - chat to listen to
     */
    public UserDialog(User user, Chat chat,ChatGui parent){
        this.user = user;
        this.chat = chat;
        this.parent = parent;

        textArea = new JTextPane();
        textArea.setEditable(false);
        JScrollPane scrlTextArea = new JScrollPane(textArea);
        scrlTextArea.setPreferredSize(new Dimension(400, 70));
        JLabel lblTextArea = new JLabel(user.getName() + "s Chat:");
        lblTextArea.setLabelFor(textArea);

        textInsertion = new JTextPane();
        textInsertion.setEditable(true);
        textInsertion.setPreferredSize(new Dimension(50, 20));
        JLabel lblTextInsertion = new JLabel("Enter Text:");
        lblTextInsertion.setLabelFor(textInsertion);


        //listen to enter pressing and insert line to chat
        textInsertion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == ENTER) {
                    chat.addLine(new ChatLine(user, textInsertion.getText()));
                    textInsertion.setText("");
                    textInsertion.requestFocusInWindow();
                    textInsertion.setCaretPosition(0);
                    keyEvent.consume();
                }

            }
        });

        // arrange components on grid
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,0,0,0);
        gridbag.setConstraints(lblTextArea, c);
        this.add(lblTextArea);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 5;
        c.insets = new Insets(0,0,0,0);
        gridbag.setConstraints(scrlTextArea, c);
        this.add(scrlTextArea);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,0);
        gridbag.setConstraints(lblTextInsertion, c);
        this.add(lblTextInsertion);

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(0,20,20,0);
        gridbag.setConstraints(textInsertion, c);
        this.add(textInsertion);

        checkRep();
    }

    /**
     * when notified by chat, updates the correspondence
     * @ modifies: textArea
     */
    public void update() {
        checkRep();
        textArea.setText("");
        StyledDocument doc = textArea.getStyledDocument();

        Style owner = textArea.addStyle("owner", null);
        StyleConstants.setForeground(owner, Color.black);

        Style otherUser = textArea.addStyle("otherUser", null);
        StyleConstants.setForeground(otherUser, Color.green);

        textArea.setFont(new Font(chat.getFont(), chat.isBold(), 12));

        Iterator it = chat.getChatLines();
        Style currentStyle;
        while (it.hasNext()){
            ChatLine line = (ChatLine)it.next();
            if (line.identify() == user.getID())
                currentStyle = owner;
            else{
                currentStyle = otherUser;
            }
            try { doc.insertString(doc.getLength(), line.toString(),currentStyle); }
            catch (BadLocationException e){}
        }
        checkRep();
    }
}
