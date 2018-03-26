package hangman;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler
{
    private final String fileName;
    private File file;

    public FileHandler(String fileName) {this.fileName = fileName;}

    /**
     * Makes new file with certain path
     *
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
     *
     * @param path path of which file wanted to be read
     * @return The contents of the file in a very large string
     */
    public String readWholeFile(String path) throws IOException
    {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder result = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null)
        {
            result.append(line);
            result.append("\n");
        }
        fileReader.close();

        return result.toString();
    }

    /**
     * Reads the whole fle
     */
    public String readWholeFile() throws IOException
    {
        return readWholeFile(this.fileName);
    }

    /**
     * Gets line of file
     *
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
     *
     * @param path   path of file is wanted to be written to
     * @param object object that is been written to the file
     * @param append Whether or not to append to the file
     */
    public void writeToFile(String path, Object object, boolean append) throws IOException
    {
        FileWriter write = new FileWriter(path, append);
        write.write(object.toString());
        write.flush();
        write.close();
    }

    /**
     * Writes to the file
     * @param object The thing to write
     * @param append Whether or not to append to the file
     * @throws IOException
     */
    public void writeToFile(Object object, boolean append) throws IOException
    {
        writeToFile(fileName, object, append);
    }

    /**
     * @return fileName
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @return File size
     */
    public int getFileSize(String path)
    {
        file = new File(path);
        int count = 0;
        if(file.exists())
        {
            try
            {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while(bufferedReader.readLine() != null)
                {
                    count++;
                }

                fileReader.close();
            }
            catch(IOException e) {e.printStackTrace();}
        }

        return count;
    }

}


