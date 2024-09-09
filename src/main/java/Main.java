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

    // making of type command 
    public static void typeCommand(String str){

        String inbuilts = "echo type exit";

        if(inbuilts.contains(str)){
            System.out.println(str+" is a shell builtin");
        }
        else{
            System.out.println(str+": not found");
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
