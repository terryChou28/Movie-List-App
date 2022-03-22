package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMovie extends JFrame implements ActionListener {
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 950;
    private static final String addString = "Add";
    private static final String removeString = "Remove";

    private JList list;
    private JList list2;
    private DefaultListModel listModel;
    private DefaultListModel listModel2;
    private JButton button;
    private JButton addButton;
    private JButton button3;
    private JButton removeButton;
    private JTextField movieName;
    private JLabel label;
    private JTextField title;
    private JTextField boxOffice;
    private JTextField rating;
    private JScrollPane listScrollPane;
    private Movie movie;
    private MovieList movies;


    public AddMovie() {
        setTitle("Movie List Application");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        getContentPane().setBackground(new Color(12, 34, 56));
        setLayout(null);

        addButtons();

        listModel = new DefaultListModel();
        listModel2 = new DefaultListModel();

        label = new JLabel("Title: ");
        label.setBounds(450, 200, 200, 100);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("MV Boli", Font.PLAIN, 36));
        title = new JTextField();
        title.setBounds(450, 305, 100, 40);
        this.add(title);
        this.add(label);

        JLabel label1 = new JLabel("Box Office: ");
        label1.setBounds(650, 200, 400, 100);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("MV Boli", Font.PLAIN, 36));
        boxOffice = new JTextField();
        boxOffice.setBounds(650, 305, 200, 40);
        this.add(boxOffice);
        this.add(label1);

        JLabel label2 = new JLabel("Rating: ");
        label2.setBounds(950, 200, 200, 100);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("MV Boli", Font.PLAIN, 36));
        rating = new JTextField();
        rating.setBounds(950, 305, 100, 40);
        this.add(rating);
        this.add(label2);

//        listModel.addElement("Title: ");
//        listModel2.addElement("1");

        listModel.addElement(title.getText());
        listModel2.addElement(boxOffice.getText());

        //Create the list and put it in a scroll pane.
        list = new JList<String>(listModel);
        list2 = new JList<String>(listModel2);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.setSelectedIndex(0);
//        list.addListSelectionListener();
        list.setVisibleRowCount(5);
        list2.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        JScrollPane scrollPane = new JScrollPane(list2);
        listScrollPane.createVerticalScrollBar();
        scrollPane.createVerticalScrollBar();

        addButton = new JButton("Add Movie");
        addButton.setBounds(500, 600, 200, 100);
        addButton.setFocusable(false);
        this.add(addButton);
        addButton.addActionListener(this);
//        AddListener addListener = new AddListener(addButton);
//        addButton.setActionCommand("Add");
//        addButton.addActionListener(addListener);
        addButton.setEnabled(true);

        removeButton = new JButton("Remove");
        removeButton.setActionCommand("Remove");
//        removeButton.addActionListener(new Add.FireListener());

        this.pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();

    }

    private void addButtons() {
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
        if (e.getSource() == addButton) {
            listModel.addElement(title.getText());
            listModel2.addElement(boxOffice.getText());
            movie = new Movie(title.getText(), Integer.parseInt(boxOffice.getText()), 0);
            movies = new MovieList();
            movies.addMovie(movie);
            JOptionPane.showMessageDialog(null, title.getText() + " has been added!",
                    "Movie", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == button3) {
            JFrame view = new View(list, list2);
            view.setVisible(true);
            System.out.println(list.toString());

//            JPanel panel = new JPanel();
//            panel.setBackground(Color.red);
//            panel.setBounds(300, 0, 250, 250);
//            panel.setLayout(new BorderLayout());
//            JLabel label = new JLabel(list.getToolTipText());
//            panel.add(label);
//            this.add(panel);
//            listScrollPane.setBackground(Color.red);
//            listScrollPane.setBounds(300, 0, 250, 250);
//            this.add(listScrollPane, BorderLayout.CENTER);
        }

    }

    public JList getList() {
        return list;
    }

    public JList getList2() {
        return list2;
    }

    public MovieList getMovies() {
        return movies;
    }

    //This listener is shared by the text field and the add button.
//    class AddListener implements ActionListener, DocumentListener {
//        private boolean alreadyEnabled = false;
//
//        private JButton button;
//
//        public AddListener(JButton button) {
//            this.button = button;
//        }

//        //Required by ActionListener.
//        public void actionPerformed(ActionEvent e) {
//            String name = title.getText();
//
//            //User didn't type in a unique name...
//            if (name.equals("") || alreadyInList(name)) {
//                Toolkit.getDefaultToolkit().beep();
//                movieName.requestFocusInWindow();
//                movieName.selectAll();
//                return;
//            }
//
//            int index = list.getSelectedIndex(); //get selected index
//            if (index == -1) { //no selection, so insert at beginning
//                index = 0;
//            } else {           //add after the selected item
//                index++;
//            }
//
//            listModel.insertElementAt(title.getText(), index);
//            //If we just wanted to add to the end, we'd do this:
//            //listModel.addElement(employeeName.getText());

//            //Reset the text field.
//            movieName.requestFocusInWindow();
//            movieName.setText("");
//
//            //Select the new item and make it visible.
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);
//        }
//
//        //This method tests for string equality. For example,
//        //you might want to ignore white space and capitalization.
//        protected boolean alreadyInList(String name) {
//            return listModel.contains(name);
//        }
//
//        //Required by DocumentListener.
//        public void insertUpdate(DocumentEvent e) {
//            enableButton();
//        }
//
//        //Required by DocumentListener.
//        public void removeUpdate(DocumentEvent e) {
//            handleEmptyTextField(e);
//        }
//
//        //Required by DocumentListener.
//        public void changedUpdate(DocumentEvent e) {
//            if (!handleEmptyTextField(e)) {
//                enableButton();
//            }
//        }
//
//        private void enableButton() {
//            if (!alreadyEnabled) {
//                button.setEnabled(true);
//            }
//        }
//
//        private boolean handleEmptyTextField(DocumentEvent e) {
//            if (e.getDocument().getLength() <= 0) {
//                button.setEnabled(false);
//                alreadyEnabled = false;
//                return true;
//            }
//            return false;
//        }
//
//        //This method is required by ListSelectionListener.
//        public void valueChanged(ListSelectionEvent e) {
//            if (e.getValueIsAdjusting() == false) {
//
//                if (list.getSelectedIndex() == -1) {
//                    //No selection, disable remove button.
//                    removeButton.setEnabled(false);
//
//                } else {
//                    //Selection, enable the remove button.
//                    removeButton.setEnabled(true);
//                }
//            }
//        }
//    }
}