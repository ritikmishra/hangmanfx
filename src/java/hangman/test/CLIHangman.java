package hangman.test;

import hangman.util.Hangman;

import java.util.Scanner;

/**
 * A command line implementation of the Hangman game
 *
 * @deprecated For testing purposes only
 */
public class CLIHangman
{
    public static void main(String[] args)
    {
        // Make a new instance of the Hangman object
        Hangman hangman = new Hangman("Bobby");

        // Be able to listen for user input
        Scanner scanner = new Scanner(System.in);

        // Let the user guess letters as long as they don't lose all of their lives
        while(hangman.getLives() > 0)
        {

            System.out.print("Enter a guess: ");
            if(hangman.checkIfContainLetter(scanner.nextLine()))
            {
                System.out.println("You guessed correctly!");

            }
            else
            {
                System.out.println("Your guess was wrong");
            }

            System.out.println("Here are your correctly guessed letters: " + hangman.getUserDisplay());
        }

        // Tell the user their score
        System.out.println("Your score is " + hangman.getScore());
        System.out.println("The word was " + hangman.getWordToGuess());


    }
}
