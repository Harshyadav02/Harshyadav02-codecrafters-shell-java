public class PwdCommand {

    // Method to print the current working directory
    public static void pwdCommand(StringBuilder currentDirectory) {
        // Get the current working directory
        
        System.out.println(currentDirectory);
    }

    public static void pwdCommand() {
        // Get the current working directory
        System.out.println(System.getProperty("user.dir"));

        
    }
}
