import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ChangeDir {
    private static Path actualDir = Paths.get(System.getProperty("user.dir"));
   public ChangeDir() {
   }

   public static String changeDirectory(String newDir, String currentDirectory) {
      Path targetDirectory = actualDir.resolve(newDir).normalize();
      
      // Check if the new path exists and is a directory
      if (Files.exists(targetDirectory, new LinkOption[0]) && Files.isDirectory(targetDirectory, new LinkOption[0])) {
        currentDirectory = targetDirectory.toString();
         return currentDirectory;
      } else {
         return newDir + ": No such file or directory";
      }
   }
}
