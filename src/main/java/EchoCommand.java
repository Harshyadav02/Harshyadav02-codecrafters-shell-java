public class EchoCommand {

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
}
