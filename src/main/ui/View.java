package ui;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private static final int WIDTH = 1500;
    private static final int HEIGHT = 950;

    private JDesktopPane deskTop;
    private JInternalFrame internalFrame;
    private JButton button;
    private JButton button3;
    private AddMovie addMovie;
    private JLabel label;
    private JList<String> list;
    private JList<String> list2;
    private JScrollPane listScrollPane;

    public View(JList list, JList list2) {
        deskTop = new JDesktopPane();

        setTitle("Movie List Application");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBounds(0, 0, WIDTH, HEIGHT);

        getContentPane().setBackground(new Color(12, 34, 56));
        setLayout(null);

        this.list = list;
        this.list2 = list2;
        JLabel label = new JLabel(this.list.toString());
        getContentPane().add(list);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
//        list.addListSelectionListener();
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        JScrollPane scrollPane = new JScrollPane(list2);
        listScrollPane.setBounds(300, 0, 300, 450);
        scrollPane.setBounds(600, 0, 300, 450);
        listScrollPane.createVerticalScrollBar();
        this.add(listScrollPane, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.CENTER);

        ImageIcon icon = new ImageIcon("./data/Batman.jpg");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        label.setBounds(400, 400, 300, 400);
        label.setIcon(newIcon);
        this.add(label);

        addButtons();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
    }

    private void addButtons() {
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
        addButton(button, button1, button2, button3, button4, button5);
    }

    private void addButton(JButton button, JButton button1, JButton button2,
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
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }
}
