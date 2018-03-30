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
        Leaderboard leaderboard = new Leaderboard("src/java/hangman/test/testleaderboard.csv");

        // Ensure that it can be written as as CSV properly
        System.out.println("leaderboard.toCSV() = " + leaderboard.toCSV());

        // Add some entries
        leaderboard.addEntry("Miguel", 10, "bob", 100);
        leaderboard.addEntry("Ritik", 18, "kitty", 1000);

        // Write the file
        leaderboard.updateFile();


    }
}
