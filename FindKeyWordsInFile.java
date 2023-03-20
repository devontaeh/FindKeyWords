
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileReader;
import java.io.IOException;
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
// import java.util.Scanner;
import java.util.Map.Entry;

public class FindKeyWordsInFile {

    public static AVLTree<String, Integer> computeWordFrequencies(String[] wordStrings) {

        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();

        for (String word : wordStrings) {
            if (wordFrequencies.get(word) != null) {
                int frequency = wordFrequencies.get(word) + 1;
                wordFrequencies.put(word, frequency);
            } else {
                wordFrequencies.put(word, 1);
            }
        }
        return wordFrequencies;

    }

    public static AVLTree<String, Void> frequentWords(String[] wordStrings) {
        AVLTree<String, Void> tree = new AVLTree<>();
        // AVLTree<String, Void> nonDiscard = new AVLTree<>();
        // ArrayList<Entry<String, Integer>> list = new ArrayList<>();

        for (String word : wordStrings) {
            // System.out.println(word);

            tree.put(word, null);

        }

        return tree;
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

        String[] englishWordsS = englishWordsFileName.split(" ");

        // System.out.println(inputFileFileName);

        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Void> englishWords = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        PriorityQueue<Map.Entry<Integer, String>> mostFrequent = new PriorityQueue<>(
                Map.Entry.comparingByKey(Comparator.reverseOrder()));
        Hashtable<String, Integer> frequencyVal = new Hashtable<>();
        ArrayList<Entry<String, Void>> list = new ArrayList<>();

        try {

            // count frequencies

            // inputFileFileName = inputFileFileName.;
            // System.out.println(inputFileFileName);

            // Part 1
            // function name => computeWordFrequencies

            wordFrequencies = computeWordFrequencies(words);
            wordFrequencies.inOrderTraversal();

            mostFrequent = wordFrequencies.findKMostFrequentWords(wordFrequencies.root, mostFrequent);
            englishWords = frequentWords(englishWordsS);
            englishWords.inOrderTraversal();

            list = englishWords.filterCommons(englishWords.root, mostFrequent, k);

            // System.out.println(mostFrrequent);
            // for (int i = 0; i < k; i++) {
            // Entry<Integer, String> max = mostFrrequent.poll();
            // System.out.println(max + "\n");
            // System.out.println(mostFrrequent);

            // }

            // wordFrequencies = computeWordFrequencies(inputFileFileName);

            // Part 2
            // function name => findKMostFrequentWords

            // Part 3
            // function name => filterCommonEnglishWords

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}