import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Word Counting Program");

        // Step 1: Prompt the user to enter text or provide a file
        System.out.print("Enter text or provide a file path: ");
        String input = scanner.nextLine();

        // Step 2: Read the input text or file
        String text;
        if (isFilePath(input)) {
            text = readTextFromFile(input);
        } else {
            text = input;
        }

        // Step 3: Split the string into an array of words
        String[] words = text.split("[\\s.,;:!?()]+"); // Using regular expression to split on space and common punctuation

        // Step 4: Initialize a counter variable
        int wordCount = 0;

        // Step 5: Iterate through the array of words and count them
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount++;
                // Step 8: Collect word frequencies
                wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }

        // Step 6: Display the total count of words
        System.out.println("Total number of words: " + wordCount);

        // Step 8: Display word frequencies
        System.out.println("Word Frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
        }

        // Step 9: Implement input validation
        // This program does a basic check for empty input or file errors.

        scanner.close();
    }

    private static boolean isFilePath(String input) {
        // A simple check if the input might be a file path
        return input.contains("/") || input.contains("\\");
    }

    private static String readTextFromFile(String filePath) {
        // Read text from a file
        try {
            return new String(Files.readAllBytes(new File(filePath).toPath()));
        } catch (IOException e) {
            System.err.println("Error reading file. Please check the file path and try again.");
            System.exit(1);
            return ""; // This line will not be reached, but needed to satisfy compiler
        }
    }
}
