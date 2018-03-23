package hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Leaderboard
{

    private FileHandler file = new FileHandler("src/leaderboard.csv");
    private List<LeaderboardEntry> leaderboard;


    public static class LeaderboardEntry
    {


        private final String name;
        private final double score;


        private final Date timestamp;

        public LeaderboardEntry(String name, double score)
        {
            this.name = name;
            this.score = score;
            this.timestamp = new Date();
        }

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

        public String toCSV()
        {
            return name + ", " + score + ", " + timestamp.toString();
        }
    }

    Leaderboard()
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

    public void addEntry(String name, double score)
    {
        addEntry(new LeaderboardEntry(name, score));
    }

    public void addEntry(LeaderboardEntry entry)
    {
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
