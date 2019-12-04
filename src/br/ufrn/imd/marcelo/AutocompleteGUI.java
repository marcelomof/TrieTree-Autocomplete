package br.ufrn.imd.marcelo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class AutocompleteGUI implements DocumentListener, ListSelectionListener {
    private TrieTree wordsTree;
    private Vector<String> suggestions;
    private boolean isFileSelected;

    private JPanel panel;
    private JMenuBar menuBar;
    private JTextField textField;
    private JList suggestionList;
    private JLabel selectedFileLabel;
    private final String noFileText = "Nenhum arquivo de palavras selecionado.";

    public AutocompleteGUI() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        wordsTree = null;
        suggestions = new Vector<>();
        isFileSelected = false;

        // Campo de texto
        textField = new JTextField();
        textField.setMaximumSize(new Dimension(500,20));
        textField.getDocument().addDocumentListener(this);

        // Lista de sugestões
        suggestionList = new JList(suggestions);
        suggestionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestionList.setLayoutOrientation(JList.VERTICAL);
        suggestionList.addListSelectionListener(this);
        suggestionList.setSelectedIndex(-1);
        suggestionList.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(suggestionList);
        listScroller.setPreferredSize(new Dimension(500,200));

        // Barra de menu
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Arquivo");
        JMenu helpMenu = new JMenu("Ajuda");

        JMenuItem openItem = new JMenuItem(new openItemAction());
        JMenuItem exitItem = new JMenuItem(new exitItemAction());
        JMenuItem helpItem = new JMenuItem(new helpItemAction());

        fileMenu.add(openItem);
        fileMenu.add(exitItem);
        helpMenu.add(helpItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        selectedFileLabel = new JLabel(noFileText, JLabel.LEADING);

        panel.add(textField);
        panel.add(listScroller);
        panel.add(selectedFileLabel);
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        textModified();
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        textModified();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        textModified();
    }

    private void textModified() {
        if(isFileSelected) {
            if(textField.getDocument().getLength() > 0) {
                suggestions = wordsTree.autocomplete(textField.getText(), 10);
                if(suggestions != null) {
                    suggestionList.setListData(suggestions);
                    return;
                }
                suggestions = new Vector<>();
            }
            suggestions.clear();
            suggestionList.setListData(suggestions);
            suggestionList.setSelectedIndex(-1);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(suggestionList.getSelectedIndex() > -1) {
            String itemText = suggestionList.getSelectedValue().toString();
            textField.setText(itemText);
        }
    }

    public void loadWords() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                wordsTree = TrieTree.generateTrie(fileChooser.getSelectedFile());
                if(wordsTree == null) {
                    selectedFileLabel.setText("Erro ao ler arquivo");
                    isFileSelected = false;
                    return;
                }
                selectedFileLabel.setText("Lendo palavras de " + fileChooser.getSelectedFile().getName());
                isFileSelected = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class openItemAction extends AbstractAction {
        public openItemAction() {
            super("Carregar palavras");
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            loadWords();
        }
    }

    class exitItemAction extends AbstractAction {
        public exitItemAction() {
            super("Sair");
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    class helpItemAction extends AbstractAction {
        public helpItemAction() {
            super("Ajuda");
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane.showMessageDialog(null,"1. Carregue um arquivo com palavras\n"
            + "2. Escreve na caixa de texto para aparecerem sugestões\n"
            + "3. Selecione alguma sugestão para autocompletar");
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
