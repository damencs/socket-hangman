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

public final class ClientHandler
{
    private InitializeSocket initializeSocket;

    private ListenerThread inputThread;
    private CommunicationThread outputThread;

    private final Socket clientSocket;

    public ClientHandler() throws IOException
    {
        clientSocket = getSocket();
        startCommunication();
        startListening();
    }

    /* Establish socket connection. */
    Socket getSocket() throws IOException
    {
       initializeSocket = new InitializeSocket();
       return initializeSocket.getSocket();
    }

    /* Start thread of which waits for server messages. */
    void startListening() throws IOException
    {
        inputThread = new ListenerThread(clientSocket);
        inputThread.start();
    }

    /* Start thread that writes server messages. */
    void startCommunication() throws IOException
    {
        outputThread = new CommunicationThread(clientSocket);
        outputThread.start();
    }
}
