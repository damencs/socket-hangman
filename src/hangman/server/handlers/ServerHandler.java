/*
    Names:
        - Damen DeBerry
        - Christopher Lyons
        - Logan Moore
    Class: Parallel and Distributed Computing
    Assignment: Choose the project topic which uses at least one of any distributed computing techniques.
 */
package server.handlers;

import java.io.IOException;

public class ServerHandler
{
    FileHandler read = new FileHandler();

    public String getWord()
    {
        String word = null;

        try
        {
            word = read.readFile();
        }
        catch (IOException x)
        {
        }

        return word;
    }
}