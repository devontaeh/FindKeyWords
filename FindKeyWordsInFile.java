
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.WrongMethodTypeException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.AbstractMap;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
// import java.util.Scanner;
import java.util.Map.Entry;

import javax.print.DocFlavor.STRING;

public class FindKeyWordsInFile {

    public static AVLTree<String, Integer> computeWordFrequencies(String[] wordStrings) {

        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();

        for (String word : wordStrings) {

            Integer count = wordFrequencies.get(word);

            if (count == null) {
                count = 0;
            }
            count++;

            wordFrequencies.put(word, count);

            // if (wordFrequencies.get(word) != null) {
            // int frequency = wordFrequencies.get(word) + 1;
            // wordFrequencies.put(word, frequency);
            // } else {
            // wordFrequencies.put(word, 1);
            // }
        }
        return wordFrequencies;

    }

    public static AVLTree<String, Integer> frequentWords(String[] wordStrings, ArrayList<String> list,
            PriorityQueue<Map.Entry<Integer, String>> queue, int k) {

        AVLTree<String, Integer> tree = new AVLTree<>();
        AVLTree<String, Integer> tree2 = new AVLTree<>();

        for (String word : wordStrings) {
            // System.out.println(word);

            tree.put(word, null);

        }

        tree.filterCommons(tree.root, queue, k);

        System.out.println(queue);

        while (queue.peek() != null) {
            Entry<Integer, String> head = queue.poll();

            tree2.put(head.getValue(), head.getKey());
            System.out.println(head + "\n");

        }
        tree2.inOrderTraversal();
        return tree2;
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
            System.err.println("Usage: java FindKeyWordsInFile k file.txt MostFrequentEnglishWords.txt");
            System.exit(1);
        }

        Path filePath = Path.of("E:\\school\\4thyear\\CS2210\\A3\\file1.txt");

        int k = Integer.parseInt(args[0]);
        String inputFileFileName = new String(Files.readAllBytes(Paths.get(args[1])));
        String englishWordsFileName = new String(Files.readAllBytes(Paths.get(args[2])));

        inputFileFileName = inputFileFileName.toLowerCase().replaceAll("[^a-z'-]", " ").replaceAll(" +", " ");
        String[] words = inputFileFileName.split(" ");

        String[] englishWordsS = englishWordsFileName.split("\\r?\\n");

        // System.out.println("the word is " + word);

        // }

        // System.out.println(inputFileFileName);

        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        // AVLTree<String, Integer> englishWords = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        PriorityQueue<Map.Entry<Integer, String>> mostFrequent = new PriorityQueue<>(
                Map.Entry.comparingByKey(Comparator.reverseOrder()));
        Hashtable<String, Integer> frequencyVal = new Hashtable<>();
        ArrayList<String> list = new ArrayList<>();

        try {

            // count frequencies

            // inputFileFileName = inputFileFileName.;
            // System.out.println(inputFileFileName);

            // Part 1
            // function name => computeWordFrequencies

            wordFrequencies = computeWordFrequencies(words);
            wordFrequencies.inOrderTraversal();

            mostFrequent = wordFrequencies.findKMostFrequentWords(wordFrequencies.root, mostFrequent);

            AVLTree<String, Integer> englishWords = frequentWords(englishWordsS, list, mostFrequent, k);

            englishWords.inOrderTraversal();

            // System.out.println(mostFrequent);

            // list = englishWords.filterCommons(englishWords.root, list, mostFrequent, k);

            for (String word : list) {
                System.out.println(word);
            }

            // Part 2
            // function name => findKMostFrequentWords

            // Part 3
            // function name => filterCommonEnglishWords

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}