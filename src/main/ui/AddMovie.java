package ui;

import model.Movie;

import javax.swing.*;
import java.awt.*;

public class AddMovie extends JFrame {
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 950;
    private static final String addString = "Add";
    private static final String removeString = "Remove";

    private JList list;
    private DefaultListModel listModel;
    private JButton button;
    private JButton removeButton;
    private JTextField movieName;
    private JLabel label;
    private JTextField title;
    private JTextField boxOffice;
    private JTextField rating;

    public AddMovie() {
        setTitle("Movie List Application");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        getContentPane().setBackground(new Color(12, 34, 56));
        setLayout(null);

        addButtons();

        listModel = new DefaultListModel();

        label = new JLabel("Title: ");
        label.setBounds(450, 200, 200, 100);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("MV Boli", Font.PLAIN, 36));
        title = new JTextField();
        title.setBounds(450, 305, 100, 50);
        this.add(title);
        this.add(label);

        JLabel label1 = new JLabel("Box Office: ");
        label1.setBounds(650, 200, 400, 100);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("MV Boli", Font.PLAIN, 36));
        boxOffice = new JTextField();
        boxOffice.setBounds(650, 305, 200, 50);
        this.add(boxOffice);
        this.add(label1);

        JLabel label2 = new JLabel("Rating: ");
        label2.setBounds(950, 200, 200, 100);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("MV Boli", Font.PLAIN, 36));
        rating = new JTextField();
        rating.setBounds(950, 305, 100, 50);
        this.add(rating);
        this.add(label2);

//        //Create the list and put it in a scroll pane.
//        list = new JList(listModel);
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.setSelectedIndex(0);
//        list.addListSelectionListener(this);
//        list.setVisibleRowCount(5);
//        JScrollPane listScrollPane = new JScrollPane(list);
//
//        JButton addButton = new JButton("Add");
//        Add.AddListener addListener = new Add.AddListener(addButton);
//        addButton.setActionCommand("Add");
//        addButton.addActionListener(addListener);
//        addButton.setEnabled(true);
//
//        removeButton = new JButton("Remove");
//        removeButton.setActionCommand("Remove");
//        removeButton.addActionListener(new Add.FireListener());

        this.pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    private void addButtons() {
        button = new JButton("Add Movie");
        JButton button1 = new JButton("Remove Movie");
        JButton button2 = new JButton("Rate Movie");
        JButton button3 = new JButton("View List");
        JButton button4 = new JButton("Save");
        JButton button5 = new JButton("Load");
        button.setBounds(0, 0, 300, 150);
        button1.setBounds(0, 150, 300, 150);
        button2.setBounds(0, 300, 300, 150);
        button3.setBounds(0, 450, 300, 150);
        button4.setBounds(0, 600, 300, 150);
        button5.setBounds(0, 750, 300, 150);
        button.addActionListener(e -> System.out.println("Cool"));
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