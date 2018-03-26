package hangman.util;

import java.util.Random;

public class Hangman
{
    private final int startingLives = 6;
    private int lives = startingLives;
    private String wordToGuess;
    public boolean[] placements;
    private String name;

    Hangman(){
        newWord();
    }

    public Hangman(String name)
    {
        this();
        this.name = name;
        System.out.println(this.name);
    }


    /**
     * This helps return if the user has enter a right key and if they do it will tell us the place in which it is located
     *
     * @param guessedLetter which letter to look for
     * @return the placements of the letter, if not present then it will take off a life
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


    public void newWord()
    {
        FileHandler fileHandler = new FileHandler("src/english.txt");
        Random r = new Random();

        wordToGuess = fileHandler.getLine(r.nextInt(fileHandler.getFileSize(fileHandler.getFileName())), fileHandler.getFileName());
        placements = new boolean[wordToGuess.length()];
    }

    /**
     * @return wordToGuess
     */
    public String getWordToGuess()
    {
        return wordToGuess;
    }

    /**
     * @return lives
     */
    public int getLives()
    {
        return lives;
    }

    public void takeLife()
    {
        lives--;
    }

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
