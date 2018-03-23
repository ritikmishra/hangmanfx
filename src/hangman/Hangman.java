package hangman;

import java.util.Random;

public class Hangman
{
    private int lives = 4;
    private String wordToGuess;

    Hangman()
    {
        FileHandler fileHandler = new FileHandler();
        Random r = new Random();

        wordToGuess = fileHandler.getLine(r.nextInt(fileHandler.getEngFileSize()), fileHandler.getFileName());
    }

    /**
     * This helps return if the user has enter a right key and if they do it will tell us the place in which it is located
     * @param letter which letter to look for
     * @return the placements of the letter, if not present then it will take off a life
     */
    public int[] checkIfContainLetter(char letter)
    {
        int[] placements = {-1,-1,-1,-1};

        for(int i=0; i < 4; i++)
        {
            if(wordToGuess.equals(letter))
            {
                placements[i] = 1;
            }
        }

        if(placements == new int[] {-1,-1,-1,-1})
        {
            lives--;
        }

        return placements;
    }

    /**
     * @return wordToGuess
     */
    private String getWordToGuess()
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

}
