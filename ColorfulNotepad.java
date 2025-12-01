
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ColorfulNotepad extends JFrame implements ActionListener {
    JTextArea textArea;
    JFileChooser fileChooser;
    JMenuItem newItem, openItem, saveItem, exitItem, cutItem, copyItem, pasteItem;

    public ColorfulNotepad() {
        setTitle("TEMARI Notepad");
        setSize(1400, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setFont(new Font("Consolas", Font.PLAIN, 26));
        textArea.setBackground(new Color(204, 85, 0));
        textArea.setForeground(new Color(0, 0, 139));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 102, 153));

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        fileMenu.setForeground(Color.WHITE);
        editMenu.setForeground(Color.WHITE);

        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");

        Color itemBg = new Color(128, 0, 128);
        JMenuItem[] items = {newItem, openItem, saveItem, exitItem, cutItem, copyItem, pasteItem};
        for (JMenuItem item : items) {
            item.setBackground(itemBg);
            item.setForeground(Color.WHITE);
            item.addActionListener(this);
        }

        fileMenu.add(newItem); fileMenu.add(openItem); fileMenu.add(saveItem); fileMenu.add(exitItem);
        editMenu.add(cutItem); editMenu.add(copyItem); editMenu.add(pasteItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem) {
            textArea.setText("");
        } else if (e.getSource() == openItem) {
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.read(reader, null);
                    reader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "File could not be opened.");
                }
            }
        } else if (e.getSource() == saveItem) {
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
                    textArea.write(writer);
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "File could not be saved.");
                }
            }
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        } else if (e.getSource() == cutItem) {
            textArea.cut();
        } else if (e.getSource() == copyItem) {
            textArea.copy();
        } else if (e.getSource() == pasteItem) {
            textArea.paste();
        }
    }

    public static void main(String[] args) {
        new ColorfulNotepad();
    }
}
