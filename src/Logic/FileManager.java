package Logic;

import java.io.*;

public class FileManager {
    private FileInputStream load;
    private FileOutputStream save;
    private ObjectInputStream objLoad;
    private ObjectOutputStream objSave;
    private final String mapPath;
    private final String gamePath;
    FileManager() {
        mapPath = "d:/labs_java/maps/";
        gamePath = "d:/labs_java/games/";
    }
    public void SaveGame(String file, Player pl, Shop sp, Town tn) {
        SavedGame sGame = new SavedGame(pl, sp, tn);
        try {
            save = new FileOutputStream(gamePath + file + ".ser");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            objSave = new ObjectOutputStream(save);
            objSave.writeObject(sGame);
            objSave.close();
            save.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public SavedGame LoadGame(String file) {
        SavedGame sg;
        try {
            load = new FileInputStream(gamePath + file + ".ser");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            objLoad = new ObjectInputStream(load);
            sg = (SavedGame) objLoad.readObject();
            objLoad.close();
            load.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sg;
    }
    public Field LoadMap(String file) {
        try {
            load = new FileInputStream(mapPath + file + ".ser");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            objLoad = new ObjectInputStream(load);
            SavedField sField = (SavedField) objLoad.readObject();
            objLoad.close();
            load.close();
            return new Field(sField);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void showMaps() {
        File Map = new File(mapPath);
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
    public void showGames() {
        File Game = new File(gamePath);
        File[] files = Game.listFiles();
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
