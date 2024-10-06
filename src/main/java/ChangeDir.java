import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeDir {
    // Static variable to track the current directory
    private static Path actualDir = Paths.get(System.getProperty("user.dir"));
    private static Path previousDir = actualDir;  // Track the previous directory for 'cd -'

    public static void changeDirectory(String newDir) {
        // Handle 'cd' with no arguments, defaulting to the home directory
        if (newDir == null || newDir.equals("") || newDir.equals("~")) {
            newDir = System.getProperty("user.home");
        } else if (newDir.equals("-")) {
            // Handle 'cd -' to switch to the previous directory
            Path tempDir = actualDir;
            newDir = previousDir.toString();
            previousDir = tempDir;
        }

        // Resolve the new directory based on the current one
        Path targetDirectory = actualDir.resolve(newDir).normalize();

        // Check if the new path exists and is a directory
        if (Files.exists(targetDirectory, new LinkOption[0]) && Files.isDirectory(targetDirectory, new LinkOption[0])) {
            // Update the previous directory before changing to the new one
            previousDir = actualDir;
            actualDir = targetDirectory; // Successfully change the current directory
        } else {
            System.out.println("cd: " + newDir + ": No such file or directory");
        }
    }

    // Method to get the current directory
    public static String getCurrentDirectory() {
        return actualDir.toString();
    }
}
