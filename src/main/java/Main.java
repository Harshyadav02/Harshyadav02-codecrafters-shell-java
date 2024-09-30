import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    // prinnt the msg based to the terminal
    public static void echo(String msg[]) {

        for (int word = 1; word < msg.length; word++) {
            System.out.print(msg[word]);
            if (word != msg.length - 1)
                System.out.print(" ");
        }
        System.out.println();
    }

    // Implementing the 'type' command
    public static void typeCommand(String command) {
        // List of built-in commands
        String builtIns = "echo type exit";

        // Check if the command is a shell built-in
        if (builtIns.contains(command)) {
            System.out.println(command + " is a shell builtin");
        } else {
            // Get the PATH environment variable
            String pathEnv = System.getenv("PATH");

            if (pathEnv != null) {
                // Split PATH into individual directories
                String[] paths = pathEnv.split(":");

                // Iterate through each directory in PATH
                for (String path : paths) {
                    File dir = new File(path);
                    File commandFile = new File(dir, command);

                    // Check if the file exists and is executable
                    if (commandFile.exists() && commandFile.canExecute()) {
                        System.out.println(command + " is " + commandFile.getAbsolutePath());
                        return;
                    }
                }
            }
            // If the command is not found in PATH
            System.out.println(command + ": not found");
        }
    }

    // runing the program

    public static void runProgram(String[] msg) {
    if (msg.length == 0) {
        System.out.println("No command provided.");
        return;
    }

    // Get the command and arguments
    String command = msg[0];
    String[] args = new String[msg.length - 1];
    System.arraycopy(msg, 1, args, 0, msg.length - 1);
    
    // Get the PATH environment variable
    String pathEnv = System.getenv("PATH");
    if (pathEnv != null) {
        String[] paths = pathEnv.split(":");

        // Iterate through each directory in PATH to find the executable
        for (String path : paths) {
            File commandFile = new File(path, command);
            if (commandFile.exists() && commandFile.canExecute()) {
                try {
                    // Combine command and arguments into a single array
                    String[] commandAndArgs = new String[args.length + 1];
                    commandAndArgs[0] = commandFile.getAbsolutePath();
                    System.arraycopy(args, 0, commandAndArgs, 1, args.length);
                    
                    ProcessBuilder processBuilder = new ProcessBuilder();
                    processBuilder.command(commandAndArgs);  // Pass the combined array
                    Process process = processBuilder.start();

                    // Get the output from the process
                    InputStream inputStream = process.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    while (scanner.hasNextLine()) {
                        System.out.println(scanner.nextLine());
                    }

                    // Wait for the process to finish
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        System.out.println("Command exited with error code: " + exitCode);
                    }
                    return;
                } catch (Exception e) {
                    System.out.println("Error executing command: " + e.getMessage());
                    return;
                }
            }
        }
    }
    // If the command is not found in PATH
    System.out.println(command + ": not found");
}


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            // input from user
            String input = scanner.nextLine();

            // spliting the array based in whitespaces
            String msg[] = input.split("\\s+");

            // Exit the loop if the user types "exit"
            if (input.equalsIgnoreCase("exit 0")) {
                System.exit(0);
            }
            // calling echo to print msg
            else if (msg[0].equals("echo")) {
                echo(msg);
            } else if (msg[0].equals("type")) {
                typeCommand(msg[1]);
            } else
                System.out.println(input + ": command not found");
                // runProgram(msg);

        }
    }
}
