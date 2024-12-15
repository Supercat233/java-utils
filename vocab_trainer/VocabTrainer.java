import java.io.*;
import java.util.*;

public class VocabTrainer {
    public static void main(String[] args) {
        List<String[]> wordList = loadWords("words.txt");
        if (wordList.isEmpty()) {
            System.out.println("No vocabulary found. Please check words.txt");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int correct = 0;

        System.out.println("📚 Vocabulary Trainer - Press Enter without typing to exit.\n");

        Collections.shuffle(wordList);
        for (String[] pair : wordList) {
            System.out.print("What is the meaning of \"" + pair[0] + "\"? ");
            String answer = scanner.nextLine().trim();

            if (answer.isEmpty()) break;

            if (answer.equalsIgnoreCase(pair[1])) {
                System.out.println("✅ Correct!\n");
                correct++;
            } else {
                System.out.println("❌ Incorrect. Correct answer: " + pair[1] + "\n");
            }
        }

        System.out.println("Done! Your score: " + correct + " / " + wordList.size());
    }

    private static List<String[]> loadWords(String filename) {
        List<String[]> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(",")) {
                    String[] pair = line.trim().split(",", 2);
                    words.add(pair);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return words;
    }
}
