package ui;

import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;

public class MyFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 950;

    private JDesktopPane deskTop;
    private JInternalFrame internalFrame;
    private JButton button;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private AddMovie addMovie;
    private JLabel label;
    private JList list;
    private JScrollPane listScrollPane;
    private JsonWriter writer;

    public MyFrame() {
        deskTop = new JDesktopPane();

        setTitle("Movie List Application");
        setSize(WIDTH, HEIGHT);

        getContentPane().setBackground(new Color(12, 34, 56));
        setLayout(null);

        ImageIcon icon = new ImageIcon("./data/Batman.jpg");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(400, 600, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        JLabel jib = new JLabel();
        jib.setBounds(580, 100, 400, 600);
        jib.setIcon(newIcon);
        this.add(jib);

        addMovie = new AddMovie();

        addButtons();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    private void addButtons() {
        button = new JButton("Add Movie");
        JButton button1 = new JButton("Remove Movie");
        JButton button2 = new JButton("Rate Movie");
        button3 = new JButton("View List");
        button3.addActionListener(this);
        button4 = new JButton("Save");
        button4.addActionListener(this);
        button5 = new JButton("Load");
        button.setBounds(0, 0, 300, 150);
        button1.setBounds(0, 150, 300, 150);
        button2.setBounds(0, 300, 300, 150);
        button3.setBounds(0, 450, 300, 150);
        button4.setBounds(0, 600, 300, 150);
        button5.setBounds(0, 750, 300, 150);
        button.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
//            addMovie = new AddMovie();
            //Create and set up the window.

            addMovie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Create and set up the content pane.
//            JComponent newContentPane = new Add();
////            newContentPane.setBackground(new Color(12, 34, 56));
//////            newContentPane.setLayout(null);
////            newContentPane.setPreferredSize(new Dimension(1200, 900));
////            newContentPane.setBounds(300, 0, 1200, 950);
//            newContentPane.setOpaque(true); //content panes must be opaque
//            frame.setContentPane(newContentPane);

            //Display the window.
//            frame.pack();
//            frame.setVisible(true);
            addMovie.setVisible(true);
        }

        if (e.getSource() == button3) {
            JFrame frame = new View(addMovie.getList(), addMovie.getList2());
            frame.setVisible(true);
//            addMovie = new AddMovie();
//            list = addMovie.getList();
//
//            listScrollPane = new JScrollPane(list);
//            listScrollPane.setBounds(300, 0, 1200, 950);
//            frame.add(listScrollPane, BorderLayout.CENTER);
//            frame.pack();
//            frame.setVisible(true);
        }

        if (e.getSource() == button4) {
            JOptionPane.showMessageDialog(null, "Your list has been saved!",
                    "Movie", JOptionPane.INFORMATION_MESSAGE);

            writer = new JsonWriter("./data/GUI.json");
            try {
                writer.open();
                writer.write(addMovie.getMovies());
                writer.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
