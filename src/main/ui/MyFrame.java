package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a window to display the main graphical user interface
public class MyFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 950;

    private JButton button;
    private JButton button1;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private AddMovie addMovie;
    private JsonWriter writer;
    private JsonReader reader;
    private MovieList movieList;

    // EFFECTS: constructs JFrame for the application
    public MyFrame() {

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

        setButtons();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets buttons to appropriate positions
    private void setButtons() {
        button = new JButton("Add Movie");
        button1 = new JButton("Remove Movie");
        button1.addActionListener(this);
        JButton button2 = new JButton("Rate Movie");
        button3 = new JButton("View List");
        button3.addActionListener(this);
        button4 = new JButton("Save");
        button4.addActionListener(this);
        button5 = new JButton("Load");
        button5.addActionListener(this);
        button.setBounds(0, 0, 300, 150);
        button1.setBounds(0, 150, 300, 150);
        button2.setBounds(0, 300, 300, 150);
        button3.setBounds(0, 450, 300, 150);
        button4.setBounds(0, 600, 300, 150);
        button5.setBounds(0, 750, 300, 150);
        button.addActionListener(this);
        addButton(button, button1, button2, button3, button4, button5);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to the frame
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
    // EFFECTS: centers the application window
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // Effects: performs actions depending on which button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            addMovie.setVisible(true);
            addMovie.addEnabled(true);
        }

        if (e.getSource() == button1) {
            addMovie.setVisible(true);
            addMovie.addEnabled(false);
            addMovie.removeEnabled();
        }

        if (e.getSource() == button3) {
            JFrame frame = new View(addMovie.getList(), addMovie.getList2(), addMovie.getList3());
            frame.setVisible(true);
        }

        if (e.getSource() == button4) {
            save();
        }

        if (e.getSource() == button5) {
            JOptionPane.showMessageDialog(null, "Your list has been loaded!",
                    "Movie", JOptionPane.INFORMATION_MESSAGE);

            reader = new JsonReader("./data/GUI.json");

            convertLists();
        }
    }

    // EFFECTS: saves the movie list to file
    private void save() {
        JOptionPane.showMessageDialog(null, "Your list has been saved!",
                "Movie", JOptionPane.INFORMATION_MESSAGE);

        writer = new JsonWriter("./data/GUI.json");
        try {
            writeFile();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // EFFECTS: converts movie lists to JLists
    private void convertLists() {
        try {
            movieList = reader.read();

            DefaultListModel<String> model = new DefaultListModel<>();
            DefaultListModel<String> model1 = new DefaultListModel<>();
            DefaultListModel<String> model2 = new DefaultListModel<>();

            addElements(model, model1, model2);

            JList<String> movieJList = new JList<>(model);
            JList<String> movieJList1 = new JList<>(model1);
            JList<String> movieJList2 = new JList<>(model2);

            JFrame view = new View2(movieJList, movieJList1, movieJList2);
            view.setVisible(true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // EFFECTS: writes the list to file
    private void writeFile() throws FileNotFoundException {
        writer.open();
        writer.write(addMovie.getMovies());
        writer.close();
    }

    // EFFECTS: adds elements to JLists
    private void addElements(DefaultListModel<String> model, DefaultListModel<String> model1,
                             DefaultListModel<String> model2) {
        model.addElement("Title: ");
        model1.addElement("Box Office: ");
        model2.addElement("Rotten Tomatoes Rating: ");

        for (Movie m : movieList.getMovieList()) {
            model.addElement(m.getTitle());
            model1.addElement(Integer.toString(m.getBoxOffice()));
            model2.addElement(Integer.toString(m.getRottenTomatoesRating()));
        }
    }
}
