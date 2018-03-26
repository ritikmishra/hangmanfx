package hangman.util;

import com.sun.corba.se.impl.protocol.MinimalServantCacheLocalCRDImpl;

import java.time.chrono.MinguoChronology;
import java.util.Random;

/**
 * A class to manage guessing letters, counting lives, and calculating score
 */
public class Hangman
{
    private final int startingLives = 6;
    private final int MIN_LENGTH = 4;
    private final int MAX_LENGTH = 10;
    private int lives = startingLives;
    private String wordToGuess;

    private boolean[] placements;

    private String name;

    /**
     * Create a new hangman object without a username
     */
    private Hangman(){
        generateNewWord();
    }

    /**
     * Create a new hangman object with a username
     * @param name The username
     */
    public Hangman(String name)
    {
        this();
        this.name = name;
    }


    /**
     * Check if the user has guessed a letter correctly
     *
     * @param guessedLetter which letter the user has guessed
     * @return If the user has guessed correctly
     */
    public boolean checkIfContainLetter(String guessedLetter)
    {

        guessedLetter = guessedLetter.trim();

        char[] letters = wordToGuess.toCharArray();

        for(int i = 0; i < placements.length; i++)
        {
            String ithLetter = String.valueOf(letters[i]);
            if(guessedLetter.equalsIgnoreCase(ithLetter))
            {
                placements[i] = true;
                return true;
            }
        }

        takeLife();

        return false;
    }


    /**
     * Generate a new word and assign it to {@link Hangman#wordToGuess}
     */
    private void generateNewWord()
    {
        FileHandler fileHandler = new FileHandler("src/english.txt");
        Random r = new Random();

        wordToGuess = fileHandler.getLine(r.nextInt(fileHandler.getNumLines(fileHandler.getFileName())));

        // Ensure that the word is within length bounds
        if(wordToGuess.length() < MIN_LENGTH || wordToGuess.length() > MAX_LENGTH)
        {
            generateNewWord();
            return;
        }
        // Ensure the word only has letters
        for(char letter : wordToGuess.toCharArray())
        {
            if(!Character.isLetter(letter))
            {
                generateNewWord();
                return;
            }
        }
        placements = new boolean[wordToGuess.length()];
        System.out.println(wordToGuess);
    }

    /**
     * @return wordToGuess
     */
    public String getWordToGuess()
    {
        return wordToGuess;
    }

    /**
     * @return lives left
     */
    public int getLives()
    {
        return lives;
    }

    /**
     * Subtract 1 from the number of lives left
     *
     */
    public void takeLife()
    {
        lives--;
    }

    /**
     * Get a string which is safe to display to the user, because it is "unsafe" to show the user the entire word
     *
     * For example, if the user guessed the letters "e" and "t", and the word to guess is "rate" this will return
     * "_ _ t e"
     * @return A string that is safe to show to the user
     */
    public String getUserDisplay()
    {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < placements.length; i++)
        {
            boolean correctlyGuessed = placements[i];
            if(correctlyGuessed)
            {
                result.append(wordToGuess.charAt(i));
            }
            else
            {
                result.append("_");
            }

            result.append(" ");
        }
        return result.toString();

    }

    /**
     * Calculate score based on length of the word and number of incorrect guesses
     * @return The score
     */
    public double getScore()
    {
        int incorrectGuesses = startingLives - lives;

        int correctGuesses = 0;
        for(boolean correctlyGuessed : placements)
        {
            if(correctlyGuessed) correctGuesses++;
        }

        return (correctGuesses - incorrectGuesses) / (double) wordToGuess.length();
    }
}
