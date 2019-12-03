package br.ufrn.imd.marcelo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;

public class AutocompleteGUI implements DocumentListener, ListSelectionListener {
    private TrieTree wordsTree;
    private Vector<String> suggestions;
    private boolean isFileSelexted;

    private JPanel panel;
    private JMenuBar menuBar;
    private JTextField textField;
    private JList suggestionList;
    private JLabel selectedFileName;
    private final String noFileText = "Nenhum arquivo de palavras selecionado.";

    public AutocompleteGUI() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        wordsTree = null;
        suggestions = new Vector<>();
        isFileSelexted = false;

        // Campo de texto
        textField = new JTextField();
        textField.setMaximumSize(new Dimension(500,20));
        textField.getDocument().addDocumentListener(this);
        //textField.setEnabled(false);

        // Lista de sugestões
        suggestionList = new JList(suggestions);
        suggestionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestionList.setLayoutOrientation(JList.VERTICAL);
        suggestionList.addListSelectionListener(this);
        suggestionList.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(suggestionList);
        listScroller.setPreferredSize(new Dimension(500,200));
        //listScroller.setEnabled(false);

        // Barra de menu
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Arquivo");
        JMenu helpMenu = new JMenu("Ajuda");

        JMenuItem openItem = new JMenuItem("Carregar palavras");
        JMenuItem exitItem = new JMenuItem("Sair");
        JMenuItem helpItem = new JMenuItem("Ajuda");

        fileMenu.add(openItem);
        fileMenu.add(exitItem);
        helpMenu.add(helpItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        selectedFileName = new JLabel(noFileText, JLabel.LEADING);

        panel.add(textField);
        panel.add(listScroller);
        panel.add(selectedFileName);
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {

    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {

    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {

    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }

    public void loadWords() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                wordsTree = TrieTree.generateTrie(fileChooser.getSelectedFile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showGUI() {
        JFrame frame = new JFrame("AutocompleteGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);

        JComponent newContentPane = panel;
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }
}
