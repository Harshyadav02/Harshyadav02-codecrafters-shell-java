public class PwdCommand {

    // Method to print the current working directory
    public static void pwdCommand() {
        // Get the current working directory
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
    }
}
