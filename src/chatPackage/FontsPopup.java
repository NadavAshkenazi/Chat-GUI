package chatPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;

/**
 * a class for a popup font list for choosing
 */
public class FontsPopup extends JDialog {

    private static final long serialVersionUID = 1L;

    private ChatGui parent;

    private JList<String> fonts;

    /**
     * creates a new FontsPopup
     * @param owner - Frame that owns this JDialog
     * @param pnlParent -  JPanel to notify after action
     */
    public FontsPopup(Frame owner, ChatGui pnlParent) {
        super(owner, "Please choose a Font", true);
        this.parent = pnlParent;


        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // listen to font choosing and notify parent
        JButton btnAdd = new JButton("Choose");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parent.changeFont(fonts.getSelectedValue());
            }
        });

        this.setPreferredSize(new Dimension(600,350));

        fonts = new JList<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrlSegments = new JScrollPane(fonts);
        scrlSegments.setPreferredSize(new Dimension(500, 200));

        JLabel lblSegments = new JLabel("Available Fonts:");
        lblSegments.setLabelFor(fonts);


        //set up greed view
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,20);
        gridbag.setConstraints(lblSegments, c);
        this.add(lblSegments);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 4;
        c.insets = new Insets(0,20,20,20);
        gridbag.setConstraints(scrlSegments, c);
        this.add(scrlSegments);

        c.fill = GridBagConstraints.NONE;

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,20);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        gridbag.setConstraints(btnAdd, c);
        this.add(btnAdd);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,20);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        gridbag.setConstraints(btnCancel, c);
        this.add(btnCancel);

    }
}