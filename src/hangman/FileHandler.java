package hangman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler
{
    private static String fileName = "src/english.txt";
    private static File file;

    FileHandler() {}

    /**
     * Makes new file with certain path
     * @param path path of file
     */
    public void newFile(String path)
    {
        file = new File(path);

        try
        {
            if(file.createNewFile())
            {
                System.out.println("File is created!");
            }
            else
            {
                System.out.println("File already exists.");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Reads the whole file of the file located in path
     * @param path path of which file wanted to be read
     */
    public void readWholeFile(String path)
    {
        try
        {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();

            System.out.println("Contents of file:");
            System.out.println(stringBuffer.toString());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Gets line of file
     * @param line line of file in which it will return
     * @param path path of file wanted
     * @return String of the line specified
     */
    public String getLine(int line, String path)
    {
        try
        {
            return Files.readAllLines(Paths.get(path)).get(line);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Writes to file
     * @param path path of file is wanted to be written to
     * @param object object that is been written to the file
     */
    public void writeToFile(String path, Object object)
    {
        FileWriter write = null;
        try
        {
            write = new FileWriter(path, true);
            write.write(String.valueOf(object));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return fileName
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @return size of english file
     */
    public int getEngFileSize()
    {
        return 500;
    }

}


