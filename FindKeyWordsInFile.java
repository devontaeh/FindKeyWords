import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

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

    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
            System.err.println("Usage: java FindKeyWordsInFile k file.txt MostFrequentEnglishWords.txt");
            System.exit(1);
        }

        Path filePath = Path.of("E:\\school\\4thyear\\CS2210\\A3\\file1.txt");

        int k = Integer.parseInt(args[0]);
        String inputFileFileName = new String(Files.readAllBytes(Paths.get(args[1])));

        String englishWordsFileName = args[2];
        System.out.println(inputFileFileName);

        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Void> englishWords = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();

        inputFileFileName = inputFileFileName.toLowerCase().replaceAll("[^a-z']", " ").replaceAll(" +", " ");
        String[] words = inputFileFileName.split(" ");

        try {

            // count frequencies

          
            // inputFileFileName = inputFileFileName.;
            // System.out.println(inputFileFileName);

            
            // Part 1
            // function name => computeWordFrequencies
            
            wordFrequencies = computeWordFrequencies(words);
            wordFrequencies.inOrderTraversal();



            // for (String word : words) {
            //     if (wordFrequencies.get(word) != null) {
            //         int frequency = wordFrequencies.get(word) + 1;
            //         wordFrequencies.put(word, frequency);
            //     } else {
            //         wordFrequencies.put(word, 1);
            //     }

            // }

            // System.out.println(words);
            // System.out.println(words);

            // check if the string has non alphabetical characters
            // check if string has an alphahbectical characters -> 10,000 not a word
            // for (int i = 0; i < word.length() - 1; i++) {
            // String omissionWord = word.toLowerCase();
            // omissionWord = omissionWord.substring(0, i) + omissionWord.substring(i + 1);
            // if (checkSpelling(omissionWord)) {
            // wordList.add(omissionWord);
            // }
            // }

            // if (wordFrequencies.get(word) != null) {
            // int frequency = wordFrequencies.get(word) + 1;
            // wordFrequencies.put(word, frequency);
            // } else {
            // wordFrequencies.put(word, 1);

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