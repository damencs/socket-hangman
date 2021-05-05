/*
    Names:
        - Damen DeBerry
        - Christopher Lyons
        - Logan Moore
    Class: Parallel and Distributed Computing
    Assignment: Choose the project topic which uses at least one of any distributed computing techniques.
 */
package server;

import server.handlers.ClientHandler;
import java.io.*;
import java.net.*;

public class Server
{
    private static ServerSocket serverSocket;
    private static final int PORT = 8000;

    /* Establishes Server on specified port and listens for client inputs. */
    public static void main(String[] Args) throws IOException
    {
        System.out.println("- Starting server!");
        serverSocket = new ServerSocket(PORT);
        System.out.println("- - Server Successfully Running.");

        do
        {
            Socket client = serverSocket.accept();
            System.out.println("- - - Client Accepted! [" + client.toString() + "]");
            ClientHandler handler = new ClientHandler(client);
            handler.start();
        } while (true);
    }
}
