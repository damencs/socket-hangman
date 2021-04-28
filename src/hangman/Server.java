package hangman;
import java.net.*;
import java.io.*;

public class Server {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream input =  null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");

            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            // Reads letter guessed
            String guess = "";

            while (!guess.equals("Game Over")) {
                try {
                    guess = input.readUTF();
                    System.out.println(guess);

                }
                catch(IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            input.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(8000);
    }

}
