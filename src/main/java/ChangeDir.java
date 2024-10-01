import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeDir {
    // Static variable to track the current directory
    private static Path actualDir = Paths.get(System.getProperty("user.dir"));

    public static void changeDirectory(String newDir) {
        // Resolve the new directory based on the current one
        Path targetDirectory = actualDir.resolve(newDir).normalize();

        // Check if the new path exists and is a directory
        if (Files.exists(targetDirectory, new LinkOption[0]) && Files.isDirectory(targetDirectory, new LinkOption[0])) {
            // Successfully change the current directory
            actualDir = targetDirectory;
        } else {
            System.out.println("cd: " + newDir + ": No such file or directory");
        }
    }

    // Method to get the current directory
    public static String getCurrentDirectory() {
        return actualDir.toString();
    }
}
