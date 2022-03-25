package ui;

import javax.swing.*;
import java.awt.*;

// Represents a window to view the movie list
public class View extends JFrame {

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 950;

    private JButton button;
    private JButton button3;
    private JList<String> list;
    private JList<String> list2;
    private JList<String> list3;

    // EFFECTS: constructs a frame to view the list
    public View(JList<String> list, JList<String> list2, JList<String> list3) {
        setTitle("Movie List Application");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBounds(0, 0, WIDTH, HEIGHT);
        getContentPane().setBackground(new Color(12, 34, 56));
        setLayout(null);

        this.list = list;
        this.list2 = list2;
        this.list3 = list3;

        JLabel label = new JLabel();
        getContentPane().add(list);

        addPane(this.list, this.list2, this.list3);

        ImageIcon icon = new ImageIcon("./data/Batman.jpg");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        label.setBounds(400, 400, 300, 400);
        label.setIcon(newIcon);
        this.add(label);

        setButtons();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        centreOnScreen();
    }

    // MODIFIES: this
    // EFFECTS: adds panes to the frame
    private void addPane(JList<String> list, JList<String> list2, JList<String> list3) {
        JScrollPane listScrollPane = new JScrollPane(list);
        JScrollPane scrollPane = new JScrollPane(list2);
        JScrollPane scrollPane1 = new JScrollPane(list3);
        listScrollPane.setBounds(300, 0, 300, 450);
        scrollPane1.setBounds(900, 0, 300, 450);
        scrollPane.setBounds(600, 0, 300, 450);
        listScrollPane.createVerticalScrollBar();
        this.add(listScrollPane, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(scrollPane1, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: sets the buttons to appropriate text and positions
    private void setButtons() {
        button = new JButton("Add Movie");
        JButton button1 = new JButton("Remove Movie");
        JButton button2 = new JButton("Rate Movie");
        button3 = new JButton("View List");
//            button3.addActionListener(this);
        JButton button4 = new JButton("Save");
        JButton button5 = new JButton("Load");
        button.setBounds(0, 0, 300, 150);
        button1.setBounds(0, 150, 300, 150);
        button2.setBounds(0, 300, 300, 150);
        button3.setBounds(0, 450, 300, 150);
        button4.setBounds(0, 600, 300, 150);
        button5.setBounds(0, 750, 300, 150);
//            button.addActionListener(this);
        addButtons(button, button1, button2, button3, button4, button5);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to the frame
    private void addButtons(JButton button, JButton button1, JButton button2,
                            JButton button3, JButton button4, JButton button5) {
        this.add(button);
        this.add(button);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        button.setFocusable(false);
        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);
    }

    /**
     * Helper to centre main application window on desktop
     */
    // EFFECTS: centers the application window
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }
}
