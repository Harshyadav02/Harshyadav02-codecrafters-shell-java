import java.util.Scanner;

public class Main {

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

            // Handle built-in commands: 'echo', 'type', 'pwd'
            else if (msg[0].equals("echo")) {
                EchoCommand.echo(msg); // Call the echo method
            } else if (msg[0].equals("type")) {
                TypeCommand.typeCommand(msg[1]); // Call the typeCommand method
            } else if (msg[0].equals("pwd")) {
                PwdCommand.pwdCommand(); // Call the pwdCommand method
            }
            else if(msg[0].equals("cd")){
                ChangeDir.changeDirectory(msg[1]);
            }
            // Handle external commands
            else {
                ExternalProgramRunner.runExternalProgram(input); // Call the method to run external programs
            }
        }
    }
}
