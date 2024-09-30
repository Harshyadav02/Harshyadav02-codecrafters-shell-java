import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public static void runningExternalProgramsWithArguments(String userCommand) {
        if (userCommand.equals("")) {
            System.out.println("No command found");
            return;
        }
        String parts[] = userCommand.split("\\s+");
        // executable file
        String programName = parts[0];
        System.out.println(programName);
        // actual command
        String args = parts[parts.length - 1];
        System.out.println(args);
        // reterving path
        String path = System.getenv("PATH");
        String directories[] = path.split(":");

        try {
            for (String dir : directories) {

                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command(dir + "/" + programName, args);

                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    System.err.println("Error executing command: " + programName);
                }
                return;
            }
        } catch (IOException | InterruptedException e) {
            // Handle exceptions (optional)
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String path = System.getenv("PATH");
        String directories[] = path.split(":");
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
            } 
            // else if(path.contains(msg[0])){
            //     System.out.println(msg[0]);
            //     runningExternalProgramsWithArguments(input);
            // }
            else
            {   System.out.println("External method called");
                runningExternalProgramsWithArguments(input);
                // System.out.println(input + ": command not found");
            }
                
                
            // runProgram(msg

        }

    }
}
