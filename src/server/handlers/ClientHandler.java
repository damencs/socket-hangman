/*
    Names:
        - Damen DeBerry
        - Christopher Lyons
        - Logan Moore
    Class: Parallel and Distributed Computing
    Assignment: Choose the project topic which uses at least one of any distributed computing techniques.
 */
package server.handlers;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientHandler extends Thread
{
    private Socket socket;
    private Scanner scanner;
    private PrintWriter output;
    private ServerHandler serverHandler = new ServerHandler();

    public ClientHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        this.scanner = new Scanner(this.socket.getInputStream());
        this.output = new PrintWriter(this.socket.getOutputStream(), true);
    }

    /* Process Game Client Submissions to Server. */
    @Override
    public void run()
    {
        boolean playing = true;;
        int score = 0;

        do
        {
            String word = serverHandler.getWord().toLowerCase();
            char[] correctWord = word.toCharArray();

            int totalTries = 6;
            char[] playerGuess = new char[word.length()];

            String allGuesses = "";

            for (int i = 0; i < playerGuess.length; i++)
            {
                playerGuess[i] = '_';
            }

            boolean isWordGuessed = false;
            int tries = 0;

            output.println("You currently have a score of: " + score + "\n");
            output.println("Lets begin! - The word has " + correctWord.length + " letters." + "\n");

            while (!isWordGuessed && tries != totalTries)
            {
                printArray(playerGuess);
                output.println("You have " + (totalTries - tries) + " tries left." + "\n");
                output.println("- Please enter your next guess! ('QUIT' to end the game.)\n");
                String guessedWord = scanner.nextLine().toLowerCase();

                boolean guessedCorrect = false;
                try
                {
                    char guessedLetter = guessedWord.charAt(0);

                    if (allGuesses.contains(String.valueOf(guessedLetter)))
                    {
                        output.println("- You've already guessed that letter! Please try another!");
                        continue;
                    }

                    for (int i = 0; i < correctWord.length; i++)
                    {
                        if (correctWord[i] == guessedLetter && guessedWord.length() == 1)
                        {
                            playerGuess[i] = guessedLetter;
                            guessedCorrect = true;
                        }
                    }

                    if (isWordGuessed(playerGuess) || word.equals(guessedWord))
                    {
                        isWordGuessed = true;
                        output.println("[ Congratulations you won! ]");
                        score++;
                    }

                    allGuesses += guessedLetter;
                }
                catch (StringIndexOutOfBoundsException e)
                {
                    output.println("\n- Please enter a valid guess.\n");
                }

                if (!guessedCorrect)
                {
                    tries++;
                }
            }

            if (!isWordGuessed)
            {
                output.println("\nYou ran out of guesses.");
                output.print("Your final state was: ");
                printArray(playerGuess);
                output.println("The word was: " + word + ".\n");
            }

            output.println("- Would you like to play another game? (yes/no)");

            try
            {
                String playAgainResponse = scanner.nextLine();
                if (playAgainResponse.equals("QUIT") || playAgainResponse.equals("no"))
                {
                    playing = false;

                    System.out.println("- - - Client has ended the connection. [" + socket.toString() + "]");

                    try
                    {
                        socket.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println("- - Unable to connect to the server.");
                    }
                }
            }
            catch (java.util.NoSuchElementException e)
            {
                System.out.println("- - - Client has ended the connection. [" + socket.toString() + "]");
            }
        } while (playing);
    }

    public boolean isWordGuessed(char[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == '_')
            {
                return false;
            }
        }
        return true;
    }

    public void printArray(char[] array)
    {
        output.print("[WORD STATUS] ");
        for (int i = 0; i < array.length; i++)
        {
            output.print(array[i] + " ");
        }
        output.print("\n");
    }
}
