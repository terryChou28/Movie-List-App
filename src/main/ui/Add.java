package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String Add = "Add";
    private static final String Remove = "Remove";
    private JButton removeButton;
    private JTextField movieName;
    private JLabel label;
    private JTextField title;
    private JTextField boxOffice;
    private JTextField rating;

    public Add() {
        super(new BorderLayout());

        setBackground(new Color(12, 34, 56));

        listModel = new DefaultListModel();

        label = new JLabel("Title: ");
        label.setBounds(350, 200, 200, 100);
        title = new JTextField();
        title.setBounds(350, 305, 100, 50);
        this.add(title);
        this.add(label);

        JLabel label1 = new JLabel("Box Office: ");
        label1.setBounds(550, 200, 200, 100);
        boxOffice = new JTextField();
        boxOffice.setBounds(550, 305, 100, 50);
        this.add(boxOffice);
        this.add(label1);

        JLabel label2 = new JLabel("Rating: ");
        label2.setBounds(750, 200, 200, 100);
        rating = new JTextField();
        rating.setBounds(750, 305, 100, 50);
        this.add(rating);
        this.add(label2);

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addButton = new JButton("Add");
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand("Add");
        addButton.addActionListener(addListener);
        addButton.setEnabled(true);

        removeButton = new JButton("Remove");
        removeButton.setActionCommand("Remove");
        removeButton.addActionListener(new FireListener());

        movieName = new JTextField(10);
        movieName.addActionListener(addListener);
        movieName.getDocument().addDocumentListener(addListener);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(movieName);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    class FireListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable removing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //This listener is shared by the text field and the add button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = title.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                movieName.requestFocusInWindow();
                movieName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(title.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            movieName.requestFocusInWindow();
            movieName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        //This method tests for string equality. For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable remove button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the remove button.
                removeButton.setEnabled(true);
            }
        }
    }
}

