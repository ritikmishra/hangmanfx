package hangman.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard
{

    /**
     * The file where we store our leaderboard
     *
     * @see Leaderboard#Leaderboard(String)
     */
    private FileHandler file = new FileHandler("src/leaderboard.csv");

    /**
     * A list of leaderboard entries
     * Guaranteed to be sorted in descending order (where index 0 is the highest scorer) or your money back
     */
    private List<LeaderboardEntry> leaderboard;


    public Leaderboard()
    {
        leaderboard = readFile();
    }

    public Leaderboard(String filepath)
    {
        file = new FileHandler(filepath);
        leaderboard = readFile();
    }

    private List<LeaderboardEntry> readFile()
    {
        List<LeaderboardEntry> result = new ArrayList<>();

        try
        {
            String contents = file.readWholeFile();
            String[] entries = contents.split("\n");
            for(String entry : entries)
            {
                if(entry.length() != 0)
                    result.add(new LeaderboardEntry(entry));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public void addEntry(String name, double score, String word, double timeElapsed)
    {
        addEntry(new LeaderboardEntry(name, score, word, timeElapsed));
    }

    public void addEntry(LeaderboardEntry entry)
    {
        double score = entry.getScore();

        for(int i = 0; i < leaderboard.size(); i++)
        {
            if(score > leaderboard.get(i).getScore())
            {
                leaderboard.add(i, entry);
                return;
            }
        }
        leaderboard.add(entry);

    }

    public String toCSV()
    {
        StringBuilder result = new StringBuilder();

        for(LeaderboardEntry entry : leaderboard)
        {
            result.append(entry.toCSV());
            result.append("\n");
        }
        return result.toString();
    }

    public List<LeaderboardEntry> getLeaderboard()
    {
        return leaderboard;
    }

    public void updateFile()
    {
        try
        {
            file.writeToFile(toCSV(), false);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
