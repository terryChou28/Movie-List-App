package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMovie extends JFrame implements ActionListener {
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 950;

    private JList list;
    private JList list2;
    private JList list3;
    private DefaultListModel listModel;
    private DefaultListModel listModel2;
    private DefaultListModel listModel3;
    private JButton button;
    private JButton addButton;
    private JButton button3;
    private JButton removeButton;
    private JTextField movieName;
    private JLabel label;
    private JTextField title;
    private JTextField boxOffice;
    private JTextField rating;
    private Movie movie;
    private MovieList movies;

    // EFFECTS: constructs a frame to add movies to a list
    public AddMovie() {
        setTitle("Movie List Application");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().setBackground(new Color(12, 34, 56));
        setLayout(null);

        setButtons();
        movies = new MovieList();

        listModel = new DefaultListModel();
        listModel2 = new DefaultListModel();
        listModel3 = new DefaultListModel();

        label = new JLabel("Title: ");
        JLabel label1 = new JLabel("Box Office: ");
        JLabel label2 = new JLabel("Rotten Tomato Rating: ");

        addLabels(label1, label2);

        addElements();

        //Create the list and put it in a scroll pane.
        list = new JList<String>(listModel);
        list2 = new JList<String>(listModel2);
        list3 = new JList<String>(listModel3);

        addRemove();

        this.pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        centreOnScreen();
    }

    // EFFECTS: adds elements to the list models
    private void addElements() {
        listModel.addElement("Title: ");
        listModel2.addElement("Box Office: ");
        listModel3.addElement("Rotten Tomato Rating: ");
        listModel.addElement(title.getText());
        listModel2.addElement(boxOffice.getText());
        listModel3.addElement(rating.getText());
    }

    // MODIFIES: this
    // EFFECTS: creates add and remove buttons
    private void addRemove() {
        addButton = new JButton("Add Movie");
        addButton.setBounds(500, 600, 200, 100);
        addButton.setFocusable(false);
        addButton.setEnabled(false);
        this.add(addButton);
        addButton.addActionListener(this);
        addButton.setEnabled(false);

        removeButton = new JButton("Remove");
        removeButton.setBounds(1000, 600, 200, 100);
        removeButton.setFocusable(false);
        this.add(removeButton);
        removeButton.addActionListener(this);
        removeButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: adds labels to the frame
    private void addLabels(JLabel label1, JLabel label2) {
        label.setBounds(450, 200, 200, 100);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("MV Boli", Font.PLAIN, 36));
        title = new JTextField();
        title.setBounds(450, 305, 100, 40);
        this.add(title);
        this.add(label);

        label1.setBounds(650, 200, 400, 100);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("MV Boli", Font.PLAIN, 36));
        boxOffice = new JTextField();
        boxOffice.setBounds(650, 305, 200, 40);
        this.add(boxOffice);
        this.add(label1);

        label2.setBounds(950, 200, 500, 100);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("MV Boli", Font.PLAIN, 36));
        rating = new JTextField();
        rating.setBounds(950, 305, 400, 40);
        this.add(rating);
        this.add(label2);
    }

    // MODIFIES: this
    // EFFECTS: sets buttons to appropriate positions and text
    private void setButtons() {
        button = new JButton("Add Movie");
        JButton button1 = new JButton("Remove Movie");
        JButton button2 = new JButton("Rate Movie");
        button3 = new JButton("View List");
        button3.addActionListener(this);
        JButton button4 = new JButton("Save");
        JButton button5 = new JButton("Load");
        button.setBounds(0, 0, 300, 150);
        button1.setBounds(0, 150, 300, 150);
        button2.setBounds(0, 300, 300, 150);
        button3.setBounds(0, 450, 300, 150);
        button4.setBounds(0, 600, 300, 150);
        button5.setBounds(0, 750, 300, 150);
        button.addActionListener(e -> System.out.println("Cool"));
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

    public void addEnabled() {
        addButton.setEnabled(true);
    }

    public void removeEnabled() {
        removeButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            listModel.addElement(title.getText());
            listModel2.addElement(boxOffice.getText());
            listModel3.addElement(rating.getText());
            movie = new Movie(title.getText(), Integer.parseInt(boxOffice.getText()),
                    Integer.parseInt(rating.getText()));

            movies.addMovie(movie);
            JOptionPane.showMessageDialog(null, title.getText() + " has been added!",
                    "Movie", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == button3) {
            JFrame view = new View(list, list2, list3);
            view.setVisible(true);
            System.out.println(list.toString());
        }

        if (e.getSource() == removeButton) {
            listModel.removeElement((title.getText()));
            listModel2.removeElement(boxOffice.getText());
            listModel3.removeElement(rating.getText());

            movies.removeMovie(title.getText());
            JOptionPane.showMessageDialog(null, title.getText() + " has been removed!",
                    "Movie", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public JList getList() {
        return list;
    }

    public JList getList2() {
        return list2;
    }

    public JList getList3() {
        return list3;
    }

    public MovieList getMovies() {
        return movies;
    }
}