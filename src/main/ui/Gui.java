package ui;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 900;

    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    public Gui() {
        desktop = new JDesktopPane();

        controlPanel = new JInternalFrame("Menu", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setBounds(0, 0, 600, 1500);
        controlPanel.setSize(300, 1300);

        setContentPane(desktop);
        setTitle("Movie List Application");
        setSize(WIDTH, HEIGHT);

        getContentPane().setBackground(new Color(12, 34, 56));

        addButtonPanel();
        addMenu();

        JPanel jpn = new JPanel(new GridLayout(6, 1, 3, 3));
        addButtons(jpn);

        ImageIcon icon = new ImageIcon("./data/Batman.jpg");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(400, 600, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        JLabel jib = new JLabel();
        jib.setBounds(580, 100, 400, 600);
        jib.setIcon(newIcon);
        jpn.add(jib);
        desktop.add(jib);
        
        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    private void addButtons(JPanel jpn) {
        JButton button = new JButton("Add Movie");
        JButton button1 = new JButton("Remove Movie");
        JButton button2 = new JButton("Rate Movie");
        JButton button3 = new JButton("View List");
        JButton button4 = new JButton("Save");
        JButton button5 = new JButton("Load");
        jpn.add(button);
        jpn.add(button);
        jpn.add(button1);
        jpn.add(button2);
        jpn.add(button3);
        jpn.add(button4);
        jpn.add(button5);
        button.setSize(200, 100);
        button1.setSize(200, 100);
        button2.setSize(200, 100);
        button3.setSize(200, 100);
        button4.setSize(200, 100);
        button5.setSize(200, 100);
        button.setBounds(200, 100, 100, 50);
        desktop.add(jpn, BorderLayout.CENTER);
    }

    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));

        controlPanel.add(panel, BorderLayout.WEST);
    }

    /**
     * Adds menu bar.
     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu movieMenu = new JMenu("Title");
        movieMenu.setMnemonic('S');

        menuBar.add(movieMenu);

        JMenu toWatchMenu = new JMenu("Box Office");
        toWatchMenu.setMnemonic('C');

        menuBar.add(toWatchMenu);

        JMenu watchedMenu = new JMenu("Rating");
        watchedMenu.setMnemonic('y');

        menuBar.add(watchedMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Adds an item with given handler to the given menu
     * @param theMenu  menu to which new item is added
     * @param action   handler for new menu item
     * @param accelerator    keystroke accelerator for this menu item
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
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
