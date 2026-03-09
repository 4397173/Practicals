//Joshua Scholtz 4397173
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Practical_15 {

    public static Map<String, List<String>> buildAnagramMap(String filename) throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                for (String raw : tokens) {
                    String w = cleanWord(raw);
                    if (w.isEmpty()) continue;
                    String key = alphabetize(w);
                    map.computeIfAbsent(key, k -> new ArrayList<>()).add(w);
                }
            }
        }
        return map;
    }

    private static String cleanWord(String s) {
        if (s == null) return "";
        int start = 0, end = s.length() - 1;
        while (start <= end && !isAllowedChar(s.charAt(start))) start++;
        while (end >= start && !isAllowedChar(s.charAt(end))) end--;
        if (start > end) return "";
        return s.substring(start, end + 1).toLowerCase(Locale.ROOT);
    }

    private static boolean isAllowedChar(char c) {
        return Character.isLetterOrDigit(c) || c == '\'';
    }

    private static String alphabetize(String w) {
        char[] arr = w.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    // Duplicates removed here
    public static void printAnagramGroups(Map<String, List<String>> anagrams, PrintWriter out) {
        for (Map.Entry<String, List<String>> e : anagrams.entrySet()) {
            List<String> group = e.getValue();
            if (group.size() >= 2) {
                LinkedHashSet<String> unique = new LinkedHashSet<>(group);
                System.out.println(e.getKey() + " -> " + unique);
                out.println(e.getKey() + " & " + unique + " \\\\");
            }
        }
    }

    public static void main(String[] args) {
        String defaultPath = "C:\\Users\\ADMIN-PC\\Documents\\UWC BSC COM 2026\\CSC 211\\15Practical\\joyce1922_ulysses.text";
        String filename = (args.length == 0) ? defaultPath : args[0];

        try {
            Map<String, List<String>> anagrams = buildAnagramMap(filename);
           //Create LaTeX output file
            PrintWriter output = new PrintWriter(new FileWriter("theAnagrams.tex"));

            output.println("\\documentclass{article}");
            output.println("\\begin{document}");
            output.println("\\section*{Anagram Groups}");
            output.println("\\begin{tabular}{ll}");
            output.println("Key & Words \\\\ \\hline");

            printAnagramGroups(anagrams, output);

            output.println("\\end{tabular}");
            output.println("\\end{document}");
            output.close();


            System.out.println("Anagrams written to theAnagrams.tex");

        } catch (IOException e) {
            System.err.println("Error reading file '" + filename + "': " + e.getMessage());
        }
    }
}
