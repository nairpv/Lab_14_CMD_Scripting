import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileScan
{
    public static void main(String[] args)
    {
        File selectedFile;

        // Check if a command-line argument is provided
        if (args.length > 0)
        {
            Path filePath = Path.of(args[0]);

            if (Files.exists(filePath) && Files.isRegularFile(filePath))
            {
                selectedFile = filePath.toFile();
            }
            else
            {
                System.err.println("Invalid file path provided: " + args[0]);
                return;
            }
        }
        else
        {
            // Launch JFileChooser if no command-line argument
            JFileChooser fileChooser = new JFileChooser("src");

            int choice = fileChooser.showOpenDialog(null);

            if (choice == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = fileChooser.getSelectedFile();
            }
            else
            {
                System.out.println("No file selected.");
                return;
            }
        }

        // Process the file
        try
        {
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            List<String> lines = Files.readAllLines(selectedFile.toPath());

            System.out.println("File Contents:");
            for (String line : lines)
            {
                System.out.println(line);
                lineCount++;
                wordCount += line.split("\\s+").length;
                charCount += line.length();
            }

            System.out.println("\nSummary Report:");
            System.out.println("File Name: " + selectedFile.getName());
            System.out.println("Number of Lines: " + lineCount);
            System.out.println("Number of Words: " + wordCount);
            System.out.println("Number of Characters: " + charCount);
        }
        catch (IOException e)
        {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
