package br.ufrn.imd.marcelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
        // Palavras, prefixo, quantidade

        if(args.length < 2) {
            System.out.println("Faltam argumentos\nUso: palavras prefixo [quantidade]");
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

        TrieTree wordsTree = generateTrie(wordsFile);
        if(wordsTree == null) {
            System.out.println("Arvore danulad");
            return;
        }

        wordsTree.autocomplete(prefix, n);
    }

    public static TrieTree generateTrie(String wordsFile) {
        BufferedReader reader;
        TrieTree tree;

        try {
            reader = new BufferedReader(new FileReader(wordsFile));
            tree = new TrieTree();

            /*if(reader.lines().count() < 1) {
                System.out.println("Erro: Arquivo vazio");
                reader.close();
                return null;
            }*/

            String word = null;
            //reader.reset();
            while((word = reader.readLine()) != null) {
                tree.insert(word);
            }

            reader.close();
            return tree;
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return null;
    }
}
