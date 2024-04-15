package Logic;

import java.io.*;

public class FileManager {
    private FileInputStream load;
    private FileOutputStream save;
    private ObjectInputStream objLoad;
    private ObjectOutputStream objSave;
    private final String dirPath = "d:/labs_java/maps/";
    FileManager(){}
    public Field LoadMap(String file)
    {
        try {
            load = new FileInputStream(dirPath + file + ".ser");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            objLoad = new ObjectInputStream(load);
            savedField sField = (savedField) objLoad.readObject();
            objLoad.close();
            load.close();
            return new Field(sField);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void showFiles()
    {
        File Map = new File(dirPath);
        File[] files = Map.listFiles();
        if (files != null) {
            for(File fl : files)
            {
                if(fl.isFile())
                {
                    System.out.println(fl.getName());
                }
            }
        }
    }
}
