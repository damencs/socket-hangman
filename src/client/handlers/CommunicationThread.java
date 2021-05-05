/*
    Names:
        - Damen DeBerry
        - Christopher Lyons
        - Logan Moore
    Class: Parallel and Distributed Computing
    Assignment: Choose the project topic which uses at least one of any distributed computing techniques.
 */
package client.handlers;

import java.io.*;
import java.net.*;
import java.util.*;

public class CommunicationThread extends Thread
{
    private final PrintWriter output;
    private final Scanner scanner;

    public CommunicationThread(Socket socket) throws IOException
    {
        this.scanner = new Scanner(System.in);
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    /* Reads inputs and passes to server. - Terminates the client side if submitted 'QUIT'. */
    @Override
    public void run()
    {
        String message;

        do
        {
            message = scanner.nextLine();
            output.println(message);
        } while (!(message.equals("QUIT")));

        System.out.println(" - - The client connection has been diminished. - -");
        System.exit(0);
    }
}