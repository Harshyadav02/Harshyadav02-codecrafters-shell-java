import java.io.File;
import java.util.Scanner;

public class Main {

    // prinnt the msg based to the terminal
    public static void echo(String msg[]){
        
        for(int word=1; word<msg.length;word++){
            System.out.print(msg[word]);
            if(word!=msg.length-1)
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
                break;
            }
            // calling echo to print msg
            else if(msg[0].equals("echo")){
                echo(msg);
            }
            else if(msg[0].equals("type")){
                typeCommand(msg[1]);
            }
            else
                System.out.println(input + ": command not found");
            
        }

        // Close the scanner after the loop ends
        scanner.close();
    }
}
