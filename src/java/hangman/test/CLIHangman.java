package hangman.test;

import hangman.Hangman;

import java.util.Scanner;

public class CLIHangman
{
    public static void main(String[] args)
    {
        Hangman hangman = new Hangman("Bobby");

        Scanner scanner = new Scanner(System.in);

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

        System.out.println("Your score is " + hangman.getScore());
        System.out.println("The word was " + hangman.getWordToGuess());


    }
}
