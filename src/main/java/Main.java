import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialize scanner to read user input

        while (true) {
            // Always print the prompt for user input
            System.out.print("$ ");
            String input = scanner.nextLine().trim(); // Trim to handle extra spaces
            String[] msg = input.split("\\s+"); // Split input into command and arguments

            if (msg.length == 0 || msg[0].isEmpty()) {
                continue; // Ignore empty commands
            }

            // Exit condition: if the user enters 'exit' or 'exit 0', terminate the shell
            if (msg[0].equalsIgnoreCase("exit") || msg[0].equalsIgnoreCase("exit 0")) {
                System.exit(0);
            }

            // Handle built-in commands: 'echo', 'type', 'pwd'
            else if (msg[0].equals("echo")) {
                EchoCommand.echo(msg); // Call the echo method
            } else if (msg[0].equals("type")) {
                if (msg.length > 1) {
                    TypeCommand.typeCommand(msg[1]); // Call the typeCommand method
                } else {
                    System.out.println("type: missing argument");
                }
            } else if (msg[0].equals("pwd")) {
                PwdCommand.pwdCommand(); // Call the pwdCommand method
            } else if (msg[0].equals("cd")) {
                // Check if there's an argument for cd, pass null otherwise
                if (msg.length > 1) {
                    ChangeDir.changeDirectory(msg[1]);
                } else {
                    ChangeDir.changeDirectory(null); // Default to home directory if no argument
                }
            }
            // Handle external commands
            else {
                ExternalProgramRunner.runExternalProgram(input); // Call the method to run external programs
            }
        }
    }
}
