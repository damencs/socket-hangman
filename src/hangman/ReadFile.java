package hangman;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile {

    private String path;
// sets up the path string

    public ReadFile(String file_path)   {
        path = file_path;
        // creates a file path for the program to go to the right location
    }

    //This function call opens the file and stores all of the data by row into an string array
    public String[] OpenFile() throws IOException   {

        FileReader fr = new FileReader(path);
        BufferedReader text_reader = new BufferedReader(fr);

        //creates an array based on the number of lines in the designated file
        int number_of_lines = read_lines();
        String[] text_data = new String[number_of_lines];

        // an array to store each line from the file into the array
        int i;
        for (i = 0; i < number_of_lines; i++){
            text_data[i] = text_reader.readLine();
        }

        //closes the file and returns the array
        text_reader.close();
        return text_data;
    }

    //This function reads the lines of the file one by one
    int read_lines() throws IOException  {

        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_to_read);

        String aline;
        int number_of_lines = 0;

        //loop calculates the number of lines in the file
        while (( aline = bf.readLine()) != null) {
            number_of_lines++;
        }

        bf.close();

        return number_of_lines;
    }

}
