package hangman;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class FileData {

    /*
    method reads the listed file and stores all lines to a string array, a random line is then chosen which contains
    a single word. That word is then returned to be used in the hangman game.
     */
    public String word_from_file() throws IOException
    {
        String file_name = "words.txt";
        Random rand = new Random();

        String word = new String();

        try
        {
            ReadFile file = new ReadFile(file_name);
            String[] ary_lines = file.OpenFile();

            int i = rand.nextInt(ary_lines.length);

            word = ary_lines[i];
            return word;
        }
        catch (IOException e)
        {
            System.out.println( e.getMessage() );
            return word;
        }
    }

    /*
    This method uses the String.indexOf() function to check if a chosen character exists in the word. If it does exist
    then the boolean returns true else it returns false.
     */
    public boolean exists_in_word(char guess, String word)
    {
        if (word.indexOf(guess) >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
    This method asks for the number of players in the game to decide if the game is multi-player or against the
    computer.
     */
    public int number_of_players()
    {
        Scanner user_input = new Scanner(System.in);

        System.out.println("Enter the number of players in this game of hangman: ");
        int player_count = user_input.nextInt();

        return player_count;
    }

    /*
    This method finds the index of the character in the word and changes the "_" character in the String array
    with the given correct guess in all locations in the string.
     */
    public String[] letter_substitution(char guess, String word, String[] answer)
    {
        int index = word.indexOf(guess);
        String input = Character.toString(guess);
        while (index >= 0)
        {
            answer[index] = input;
            index = word.indexOf(guess, index + 1);
        }
        return answer;
    }

    /*
    Boolean method to see if the guess has already been used before or not.
     */
    public boolean previously_guessed(char guess, String[] used_characters, int guess_count)
    {
        if (guess_count == 0)
        {
            return false;
        }

        else {
            String input = Character.toString(guess);
            for (int i = 0; i < guess_count; i++) {
                if (used_characters[i].equals(input)) {
                    return true;
                }
            }
            return false;
        }
    }

    public String[] used_guess(char guess, String[] used_characters, int index)
    {
        String character = Character.toString(guess);
        used_characters[index] = character;
        return used_characters;
    }

    public void print_hangman(String[] answer, int answer_length)
    {
        for (int j = 0; j < answer_length; j++)
        {
            System.out.print(answer[j]);
        }
        System.out.println();
    }

    /*
    main method with function calls
     */
    public static void main(String[] args) throws IOException {

        Scanner user_input = new Scanner(System.in);
        FileData hangman = new FileData();

        int max_guesses = 8;
        int guess_count = 0;
        String[] used_characters = new String[26];

        int player_count = hangman.number_of_players();
        String word = "";
        Character guess;

        if (player_count == 2)
        {

            System.out.println("Enter the word you would like to use: ");
            word = user_input.next();

        }

        else if (player_count == 1)
        {
            word = hangman.word_from_file();
        }

        int answer_length = word.length();
        String[] answer = new String[answer_length];

        for (int i = 0; i < answer_length; i++)
        {
            answer[i] = "_ ";
        }

        //System.out.println("The word is: " + word);
        System.out.println("\nThe word for hangman is: ");
        hangman.print_hangman(answer, answer_length);

        while (max_guesses != 0)
        {
            System.out.println("\nEnter your character guess for hangman: ");
            guess = user_input.next().charAt(0);

            if (hangman.previously_guessed(guess, used_characters, guess_count))
            {
                System.out.println("\nYou have already guessed this letter, please guess again.\n");
            }

            else if (hangman.exists_in_word(guess, word))
            {
                System.out.println("\nYour guess was correct, congrats!\n");
                hangman.letter_substitution(guess, word, answer);
                hangman.used_guess(guess, used_characters, guess_count);
                guess_count++;
            }

            else
            {
                max_guesses--;
                System.out.println("\nI'm sorry your guess was incorrect, you have " + max_guesses + " guesses left.\n");
                hangman.used_guess(guess, used_characters, guess_count);
                guess_count++;
            }

            hangman.print_hangman(answer, answer_length);
            //hangman.print_hangman(used_characters, 26);
        }
    }
}
