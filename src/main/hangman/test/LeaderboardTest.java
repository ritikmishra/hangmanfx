package main.hangman.test;

import main.hangman.Leaderboard;

public class LeaderboardTest
{
    public static void main(String[] args)
    {
        Leaderboard leaderboard = new Leaderboard("src/hangman/test/testleaderboard.csv");
        System.out.println("leaderboard.toCSV() = " + leaderboard.toCSV());

        leaderboard.addEntry("Miguel", 10);
        leaderboard.addEntry("Ritik", 18);
        leaderboard.updateFile();


    }
}
