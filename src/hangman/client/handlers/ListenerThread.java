/*
    Names:
        - Damen DeBerry
        - Christopher Lyons
        - Logan Moore
    Class: Parallel and Distributed Computing
    Assignment: Choose the project topic which uses at least one of any distributed computing techniques.
 */
package client.handlers;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ListenerThread extends Thread
{
    private final Scanner input;

    public ListenerThread(Socket socket) throws IOException
    {
        input = new Scanner(socket.getInputStream());
    }

    /* Listen for inputs from server until connection closed. */
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                System.out.println(input.nextLine());
            }
            catch (java.util.NoSuchElementException e)
            {
                System.out.println("- - The Server Connection has been diminished. - -");
                System.exit(0);
            }
        }
    }
}