package hangman.util;

import java.util.Date;

/**
 * A bean for the info that goes with an entry in the leaderboard: Name, score, and timestamp
 */
public class LeaderboardEntry
{
    /**
     * The name of the user
     */
    private final String name;

    /**
     * The score the user earned
     */
    private final double score;

    /**
     * When the user earned their score
     */
    private final Date timestamp;

    /**
     * Make a new leaderboard entry given the user's name and their score
     * @param name The user's name
     * @param score Their score
     */
    public LeaderboardEntry(String name, double score)
    {
        this.name = name;
        this.score = score;
        this.timestamp = new Date();
    }

    /**
     * Make a new leaderboard entry given the row in the CSV for that entry
     * @param entry the row in the CSV for that entry
     */
    public LeaderboardEntry(String entry)
    {
        String[] data = entry.split(", ");
        name = data[0];
        score = Double.valueOf(data[1]);
        timestamp = new Date(data[2]);
    }

    public double getScore()
    {
        return score;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public String getName()
    {
        return name;
    }

    /**
     * Turn this leaderboard entry into a row in a CSV
     * @return The name, score, and timestamp of the leaderboard entry, separated by commas
     */
    public String toCSV()
    {
        return name + ", " + score + ", " + timestamp.toString();

    }
}
