import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            // Exit the loop if the user types "exit"
            if (input.equalsIgnoreCase("exit 0")) {
                break;
            }

            // System.out.println(input + ": command not found");
            String msg[] = input.split(" ");
            for(int word=1; word<msg.length;word++){
                System.out.print(msg[word]);
                if(word!=msg.length-1)
                    System.out.print(" ");
            }
            System.out.println();
        }

        // Close the scanner after the loop ends
        scanner.close();
    }
}
