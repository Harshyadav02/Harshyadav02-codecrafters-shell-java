import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    // Method to print a message passed by the user using the 'echo' command.
    public static void echo(String msg[]) {
        // Iterate through the arguments passed with the 'echo' command
        for (int word = 1; word < msg.length; word++) {
            System.out.print(msg[word]);
            if (word != msg.length - 1)
                System.out.print(" ");
        }
        System.out.println();
    }

    // Method to implement the 'type' command, which checks if a command is built-in or external.
    public static void typeCommand(String command) {
        // List of built-in commands
        String builtIns = "echo type exit";
        
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

    // Method to run external programs with optional arguments
    public static void runningExternalProgramsWithArguments(String userCommand) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialize scanner to read user input

        while (true) {
            // Always print the prompt for user input
            System.out.print("$ ");
            String input = scanner.nextLine();
            String[] msg = input.split("\\s+"); // Split input into command and arguments

            // Exit condition: if the user enters 'exit' or 'exit 0', terminate the shell
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("exit 0")) {
                System.exit(0);
            }
            // Handle built-in commands: 'echo' and 'type'
            else if (msg[0].equals("echo")) {
                echo(msg); // Call the echo method
            } else if (msg[0].equals("type")) {
                typeCommand(msg[1]); // Call the typeCommand method
            }
            // Handle external commands
            else {
                runningExternalProgramsWithArguments(input); // Call the method to run external programs
            }
        }
    }
}
