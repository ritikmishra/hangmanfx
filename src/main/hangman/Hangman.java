package main.hangman;

import java.util.Random;

public class Hangman
{
    private int lives = 4;
    private String wordToGuess;
    private int[] placements;
    private String name;

    Hangman(){}

    Hangman(String name)
    {
        this.name = name;
        System.out.println(this.name);
    }

    Hangman(boolean newWord)
    {
        if(newWord) newWord();

        System.out.println(wordToGuess);
    }

    /**
     * This helps return if the user has enter a right key and if they do it will tell us the place in which it is located
     *
     * @param letter which letter to look for
     * @return the placements of the letter, if not present then it will take off a life
     */
    public int[] checkIfContainLetter(String letter)
    {

        letter = letter.replaceAll("\\s+", "");
        char[] letters = wordToGuess.toCharArray();

        for(int i = 0; i < placements.length; i++)
        {
            if(letter.equalsIgnoreCase(String.valueOf(letters[i])))
            {
                placements[i] = 1;
            }
        }

        for(int item : placements)
        {
            if(item != 0)
            {
                return placements;
            }
        }

        lives--;

        return placements;
    }

    public void newWord()
    {
        FileHandler fileHandler = new FileHandler("src/english.txt");
        Random r = new Random();

        wordToGuess = fileHandler.getLine(r.nextInt(fileHandler.getFileSize(fileHandler.getFileName())), fileHandler.getFileName());
        placements = new int[wordToGuess.length()];
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

    /**
     * Sets word to guess
     * @param word word to set
     */
    public void setWordToGuess(String word)
    {
        this.wordToGuess = word;
    }
}
