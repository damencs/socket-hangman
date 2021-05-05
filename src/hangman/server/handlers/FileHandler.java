/*
    Names:
        - Damen DeBerry
        - Christopher Lyons
        - Logan Moore
    Class: Parallel and Distributed Computing
    Assignment: Choose the project topic which uses at least one of any distributed computing techniques.
 */
package server.handlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler
{
    private static final String FILEPATH = System.getProperty("user.dir") + "\\src\\resources\\words.txt";

    /* Randomly select a word from the provided file. */
    public String readFile() throws IOException
    {
        BufferedReader bufferedReader;
        FileReader fileReader;
        ArrayList<String> wordList = new ArrayList<String>();

        fileReader = new FileReader(FILEPATH);
        bufferedReader = new BufferedReader(fileReader);

        String word;
        while ((word = bufferedReader.readLine()) != null)
        {
            wordList.add(word);
        }

        return wordList.get((int)(Math.random() * wordList.size()));
    }
}