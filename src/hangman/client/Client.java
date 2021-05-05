/*
    Names:
        - Damen DeBerry
        - Christopher Lyons
        - Logan Moore
    Class: Parallel and Distributed Computing
    Assignment: Choose the project topic which uses at least one of any distributed computing techniques.
 */
package client;

import client.handlers.ClientHandler;
import java.io.IOException;

public class Client
{
    static class ClassView
    {
        ClientHandler clientHandler;

        public ClassView() throws IOException
        {
            clientHandler = new ClientHandler();
        }
    }

    public static void main(String[] Args) throws IOException
    {
        ClassView classView = new ClassView();
    }
}
