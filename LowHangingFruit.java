// File Name: LowHangingFruit.java
// Author: Faith Reeves
// Program Purpose: For users to search and see if a word is included in a 
//                  file of random words. After program is terminated, users
//                  are told how many words were found and not found and how 
//                  many seconds they spent searching.

package lowhangingfruit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JFileChooser;

public class LowHangingFruit {

    public static void main(String[] args) throws FileNotFoundException {

        // create a String for the user's guess
        String guess;

        // create variables for words found and not found
        int wordsFound = 0;
        int wordsNotFound = 0;

        // create a boolean to check whether the word was found
        boolean isItThere = false;

        // create a String array to store the data from the "dictionary.txt" file
        String[] dictionaryWords = new String[45403];

        // prompt the user to select the "dictionary.txt" file
        System.out.println("Select the \"dictionary.txt\" file.");

        // create a JFileChooser object
        JFileChooser chooser = new JFileChooser();

        // open the file dialog box
        chooser.showOpenDialog(null);

        // get the selected file
        File f = chooser.getSelectedFile();

        // store the file path
        String filename = f.getAbsolutePath();

        // declare file object
        File myDictionary;

        // declare Scanner object
        Scanner input = null;

        // beggining of try block
        try {

            // instantiate File object
            myDictionary = new File(filename);

            // instantiate Scanner object
            input = new Scanner(myDictionary);

            // check if the file does not exist or does not contain "dictionary.txt",
            if (!myDictionary.exists() || !filename.contains("dictionary.txt")) {

                // throw file not found exception
                throw new FileNotFoundException();

            }

        } // end of try block
        // handles exception if user's file does not exist
        catch (FileNotFoundException ex) {

            // tell the user that the file name is invalid and that you are exiting the program
            System.out.print("Sorry, the file \"dictionary.txt\" was not found. Exiting Program.");

            // exit the program
            System.exit(1);

        }

        // create a counter for the while loop
        int j = 0;

        // execute the loop for each word in the "dictionary.txt" file
        while (input.hasNextLine()) {

            // store each word from the file in the dictionaryWords array
            dictionaryWords[j] = input.nextLine();

            // change all dictionary words to lowercase
            dictionaryWords[j] = dictionaryWords[j].toLowerCase();

            // increment the counter variable
            j++;
        }

        // close the file reading Scanner
        input.close();

        // create a Stopwatch object
        StopWatch timer = new StopWatch();

        // greet the user, tell them to enter words, and how to exit the program
        System.out.println("Welcome to the LHF-Spell Checker by Faith Reeves."
                + " Please enter words,\none at a time, to be found in the word list. "
                + "Enter '*' to quit.");

        // promt user for the first word
        System.out.print("First word: ");

        // start the stopwatch
        timer.start();

        // create Scanner object to read in user's words
        Scanner cin = new Scanner(System.in);

        // store the user's guess in the guess variable
        guess = cin.nextLine();

        // trim the user's answer to get rid of any accidental white space
        guess = guess.trim();

        // make the user's answer all lowercase
        guess = guess.toLowerCase();

        // while the user's guess does not equal * cycle through the loop
        while (!"*".equals(guess)) {

            // cycle through the loop for each element of the dictionaryWords array
            for (String dictionaryWord : dictionaryWords) {

                // check to see if the guess matches an array element
                if (guess.equals(dictionaryWord)) {

                    // tell the user that the word was found
                    System.out.println(guess + " found");

                    // increment the words found counter
                    wordsFound++;

                    // change the boolean to true since the word exists
                    isItThere = true;

                    // break out of the for loop
                    break;
                }
            }

            // check to see if the word was not found in the dictionary
            if (!isItThere) {

                // tell the user the word was not found
                System.out.println(guess + " not found");

                //increment the words not found counter
                wordsNotFound++;
            }

            // change the isItThere boolean back to its default false value
            isItThere = false;

            // prompt the user to enter their next word
            System.out.print("Next word: ");

            // store the user's answer in the guess variable
            guess = cin.nextLine();

            // trim the user's answer to get rid of any accidental white space
            guess = guess.trim();

            // make the user's answer all lowercase
            guess = guess.toLowerCase();
        }

        // stop the stopwatch
        timer.stop();

        // close the user input Scanner
        cin.close();

        // output an exit message that includes all collected data
        System.out.println("Thanks for playing!\n" + wordsFound + " word(s) found.\n"
                + wordsNotFound + " word(s) not found.\n" + timer.getElapsedTime()
                + " seconds spent searching.\n" + "Program terminated.");

    }
}

// class name: StopWatch
// purpose: to measure and return a period of time in seconds
class StopWatch {

    // declare and assign default vales to data fields
    private double startTime = 0;

    private double endTime = 0;

    // np arg constructor for time in seconds
    StopWatch() {

    }

    // method that sets the start time to the current time in nanoseconds
    void start() {

        startTime = System.nanoTime();
    }

    // method that sets the stop time to the current time in nanoseconds
    void stop() {

        endTime = System.nanoTime();
    }

    // method that returns the elapsed time for the stopwatch in seconds
    double getElapsedTime() {

        // declare variable that will store the elapsed time
        double elapsedTime;

        // calculate and store the elapsed time in seconds
        elapsedTime = ((endTime - startTime) / 1000000000);

        // return the elapsed time
        return elapsedTime;
    }
}