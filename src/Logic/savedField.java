package Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SavedField implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int xsize;
    private final int ysize;
    private final HashMap<String, float[]> Fines;
    private final ArrayList<String> ter;
    private final ArrayList<ArrayList<Cell>> Map;
    public SavedField(int xsize, int ysize, ArrayList<ArrayList<Cell>> Map, HashMap<String, float[]> Fines, ArrayList<String> ter) {
        this.xsize = xsize;
        this.ysize = ysize;
        this.Map = Map;
        this.Fines = Fines;
        this.ter = ter;
    }
    public int getXsize() {
        return xsize;
    }
    public int getYsize() {
        return ysize;
    }
    public ArrayList<ArrayList<Cell>> getMap() {
        return Map;
    }
    public HashMap<String, float[]> getFines() {
        return Fines;
    }
    public ArrayList<String> getTer() {
        return ter;
    }
}
