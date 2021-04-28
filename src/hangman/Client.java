package hangman;
import java.net.*;
import java.io.*;

public class Client {
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream output = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connection Established");
            System.out.println("Welcome to Hangman! You have 8 wrong guesses before you loose.\nType 'Game Over' once finished ");
            System.out.println("Guess a letter:");

            input  = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u) {
            System.out.println(u);
        }

        catch(IOException i) {
            System.out.println(i);
        }

        // Reads letter guessed
        String guess = "";

        // keep reading until "Game Over" is input
        while (!guess.equals("Game Over")) {
            try {
                guess = input.readLine();
                output.writeUTF(guess);
            }
            catch(IOException i) {
                System.out.println(i);
            }
        }

        try {
            input.close();
            output.close();
            socket.close();
        }

        catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 8000);
    }
}
