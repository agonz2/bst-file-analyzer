package src;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemClass {
    Scanner scanner; 
    NodeClass Nodes;
    File analysis;
    String path;
    ArrayList<String> StringList;
    
    // Create new scanner on class instantiation
    public SystemClass() {
        this.scanner = new Scanner(System.in);
    }

    // Check if the file actually exists
    public void validateFile(){
        String filePath = scanner.nextLine();
        Path file = Paths.get(filePath);
        this.path = file.toString();

        if (!Files.exists(file) && !Files.isRegularFile(file)){
            System.out.println("No such file, try again");
            validateFile();
        }
        else {
            this.analysis = new File("analysis.txt");
            this.Nodes = new NodeClass();
            this.StringList = new ArrayList<>();
            readToArray(file.toFile());
        }
    }

    // Validates each word in each line to add to string array
    private void computeLine(String currentLine) {
        String[] splitText;
        StringBuilder processString;
        StringBuilder secondString;

        // Split and put each word into string array
        splitText = currentLine.split("[\\s\\n]+");

        // Iterate through each index in string array
        checkString:
        for (String text : splitText) {
            String mainString;
            int dashCount = 0;
            processString = new StringBuilder();
            secondString = new StringBuilder();

            // If word appears more than once, move to next word
            for (String word : StringList) {
                if (text.equals(word)) continue checkString;
            }

            // Iterate through each character in text
            for (int j = 0; j < text.length(); j++) {
                char c = text.charAt(j);

                if (c == '-') dashCount++;

                // If character is a letter add it to string otherwise end loop
                if (!Character.isLetter(c)) continue;

                // Add characters to new string if two dashes are found
                if (dashCount >= 2){
                    secondString.append(c);
                    continue;
                }

                processString.append(c);
            }

            // Convert to all lowercase string and store to string array
            mainString = processString.toString().toLowerCase();
            StringList.add(mainString);

            // Store second string also if minimum dashes are found
            if (dashCount >= 2) StringList.add(secondString.toString().toLowerCase());
        }
    }
    
    // Writes total nodes, maximum nodes, and height to analysis file
    private void writeOutput(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(analysis))) {
            int treeDepth = Nodes.getTreeDepth();

            String total = String.format("%-14s%-3s%-5s%n", "Total Nodes", ":", Nodes.getNodeTotal());
            String height = String.format("%-14s%-3s%-5s%n", "Tree Height", ":", treeDepth);
            String maximum = String.format("%-14s%-3s%-5s%n", "Maximum Nodes", ":", (int)(Math.pow(2,treeDepth) - 1));

            writer.write(
                "BINARY SEARCH TREE ANALYZER\n" +
                "File Analyzed: " + path + "\n" +
                "\n" + total + height + maximum
            );
            writer.close();

            System.exit(0);
        } catch (IllegalArgumentException | IOException e) {System.out.println(e.getMessage());}
    }

    // Read the file to its entirety to an array
    private void readToArray (File file){
        String currentLine;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Reading file..");
            while ((currentLine = reader.readLine()) != null) {
                try {
                    computeLine(currentLine);
                } catch (IllegalArgumentException e) {System.out.println(e.getMessage());}
            } 
            reader.close();
        } catch (IOException e) {System.out.println(e.getMessage());}

        scanner.close();

        // Add all unique words to the tree
        for (String word : StringList) {
            Nodes.insert(word);
        }

        // Begin constructing our output
        writeOutput();

    }
}
