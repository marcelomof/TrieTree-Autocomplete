package br.ufrn.imd.marcelo;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        // Palavras, prefixo, quantidade

        if(args.length == 0) {
            AutocompleteGUI gui = new AutocompleteGUI();
            gui.showGUI();
            return;
        }

        else if(args.length < 2) {
            System.out.println("Faltam argumentos\nUso: palavras prefixo [quantidade]");
            System.out.println("Ou rode sem argumentos para utilizar o cliente gráfico");
            return;
        }

        // 0 : palavras
        String wordsFile = args[0];

        // 1 : prefixo
        String prefix = args[1];

        // 2 : [quantidade]
        int n = -1;
        if(args.length > 2) {
            try {
                n = Integer.parseInt(args[2]);

                if(n < 0) {
                    System.out.println("Erro: Quantidade não pode ser negativa");
                    return;
                }

            } catch(NumberFormatException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        TrieTree wordsTree = TrieTree.generateTrie(wordsFile);
        if(wordsTree == null) {
            System.out.println("Arvore danulad");
            return;
        }

        Vector<String> words;
        if(n == -1) {
            words = wordsTree.autocomplete(prefix);
        }
        else {
            words = wordsTree.autocomplete(prefix, n);
        }

        if(words == null) {
            System.out.println("Nenhuma palavra encontrada com o prefixo \'" + prefix + "\'");
            return;
        }
        if(n == -1) {
            System.out.println(words.size() + " palavra(s) encontrada(s):");
        }
        else {
            System.out.println("Mostrando primeiras " + n + " palavras encontradas:");
        }

        for (String word : words) {
            System.out.println(word);
        }
    }
}
