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
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class InitializeSocket
{
    private Socket socket;
    private String HOST;
    private final int PORT = 8000;

    /* Established a connection to desired IP. */
    public InitializeSocket() throws IOException
    {
        System.out.println("Please enter the IP you would like to connect to (ENTER for LOCALHOST):");
        Scanner scanner = new Scanner(System.in);

        HOST = scanner.nextLine();
        if (HOST == null)
        {
            HOST = InetAddress.getLocalHost().toString();
        }
        socket = new Socket(HOST, PORT);
    }

    public Socket getSocket()
    {
        return this.socket;
    }
}