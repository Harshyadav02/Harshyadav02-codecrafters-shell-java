import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalProgramRunner {

    // Method to run external programs with optional arguments
    public static void runExternalProgram(String userCommand) {
        // Return if the userCommand is empty
        if (userCommand.equals("")) {
            return;
        }

        // Split the input into the program name and its arguments
        String[] parts = userCommand.split("\\s+");
        String programName = parts[0]; // Program to be executed
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, parts.length - 1); // Store the arguments

        // Retrieve PATH environment variable
        String path = System.getenv("PATH");
        String[] directories = path.split(":");
        boolean commandExecuted = false;

        try {
            // Search through the directories in the PATH to find the executable program
            for (String dir : directories) {
                File commandFile = new File(dir, programName);

                // If the program exists and is executable, start the process
                if (commandFile.exists() && commandFile.canExecute()) {
                    ProcessBuilder processBuilder = new ProcessBuilder(parts); // Initialize the process with the command and arguments
                    Process process = processBuilder.start(); // Start the process

                    // Capture the output of the process
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line); // Print the output line-by-line
                    }
                    int exitCode = process.waitFor(); // Wait for the process to finish
                    if (exitCode != 0) {
                        System.err.println("Error executing command: " + programName); // Error handling
                    }
                    commandExecuted = true; // Indicate the command was successfully executed
                    break; // Exit loop after executing the command
                }
            }

            // If no valid command was found in PATH, print an error message
            if (!commandExecuted) {
                System.out.println(programName + ": command not found");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Handle any exceptions that occur
        }
    }
}
