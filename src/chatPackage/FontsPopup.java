package chatPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FontsPopup extends JDialog {

    private static final long serialVersionUID = 1L;

    // the RouteDirectionsGUI that this JDialog was opened from
    private UserDialog parent;

    // a control contained in this
    private JList<String> fonts;

    /**
     * Creates a new GeoSegmentsDialog JDialog.
     * @effects Creates a new GeoSegmentsDialog JDialog with owner-frame
     * 			owner and parent pnlParent
     */
    public FontsPopup(Frame owner, UserDialog pnlParent) {
        // create a modal JDialog with the an owner Frame (a modal window
        // in one that doesn't allow other windows to be active at the
        // same time).
        super(owner, "Please choose a Font", true);

        this.parent = pnlParent;


        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButton btnAdd = new JButton("Choose");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                parent.font = (fonts.getSelectedValue());
                parent.update();
            }
        });

        this.setPreferredSize(new Dimension(600,350));

        fonts = new JList<String>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrlSegments = new JScrollPane(fonts);
        scrlSegments.setPreferredSize(new Dimension(500, 200));

        JLabel lblSegments = new JLabel("Available Fonts:");
        lblSegments.setLabelFor(fonts);

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