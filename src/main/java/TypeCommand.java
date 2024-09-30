import java.io.File;

public class TypeCommand {

    // Method to implement the 'type' command, which checks if a command is built-in or external.
    public static void typeCommand(String command) {
        // List of built-in commands
        String builtIns = "echo type exit pwd";
        
        // Check if the command is built-in
        if (builtIns.contains(command)) {
            System.out.println(command + " is a shell builtin");
        } else {
            // Fetch the PATH environment variable to search for external commands
            String pathEnv = System.getenv("PATH");
            if (pathEnv != null) {
                String[] paths = pathEnv.split(":");
                for (String path : paths) {
                    File dir = new File(path);
                    File commandFile = new File(dir, command);

                    // If the file exists and is executable, print its absolute path
                    if (commandFile.exists() && commandFile.canExecute()) {
                        System.out.println(command + " is " + commandFile.getAbsolutePath());
                        return;
                    }
                }
            }
            // If the command is not found in the PATH, print an error message
            System.out.println(command + ": not found");
        }
    }
}
