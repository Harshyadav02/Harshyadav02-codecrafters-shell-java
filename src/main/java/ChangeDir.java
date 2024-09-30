import java.nio.file.Files;
import java.nio.file.Path;

public class ChangeDir {
   
    private static Path  currentDirectory;

    public static void changeDirectory(String dir){

        Path targetDirectory = currentDirectory.resolve(dir).normalize();
        if (Files.exists(targetDirectory) && Files.isDirectory(targetDirectory)) {
            currentDirectory = targetDirectory;
            
        } else {
            System.out.println("/dir: No such file or directory");
        }

    }
}
