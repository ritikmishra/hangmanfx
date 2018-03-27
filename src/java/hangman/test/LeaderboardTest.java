package hangman.test;

import hangman.util.Leaderboard;

/**
 * Test the leaderboard
 */
public class LeaderboardTest
{
    public static void main(String[] args)
    {
        // Don't edit the main leaderboard file
        Leaderboard leaderboard = new Leaderboard("src/hangman/test/testleaderboard.csv");

        // Ensure that it can be written as as CSV properly
        System.out.println("leaderboard.toCSV() = " + leaderboard.toCSV());

        // Add some entries
        leaderboard.addEntry("Miguel", 10, "bob");
        leaderboard.addEntry("Ritik", 18, "kitty");

        // Write the file
        leaderboard.updateFile();


    }
}
