package Logic;

import java.util.ArrayList;
import java.util.HashMap;

public class Field {
    private int xsize;
    private int ysize;
    private HashMap<String, float[]> Fines;
    private ArrayList<String> ter;
    private ArrayList<ArrayList<Cell>> Map;
    public Field (savedField sField)
    {
        this.xsize = sField.getXsize();
        this.ysize = sField.getYsize();
        this.Fines = sField.getFines();
        this.ter = sField.getTer();
        this.Map = sField.getMap();
    }
    @Override
    public String toString()
    {
        String fld = "";
        for(int i = 0; i < xsize; i++)
        {
            for(int j = 0; j < ysize; j++)
            {
                fld += Map.get(i).get(j).getTer();
            }
            fld += "\n";
        }
        return fld;
    }
    public float fine(String ter, int n)
    {
        if(getTer().contains(ter)) {
            return getFines().get(ter)[n];
        }
        else {
            return getFines().get("*")[n];
        }
    }
    public ArrayList<ArrayList<Cell>> getMap()
    {
        return this.Map;
    }
    public HashMap<String, float[]> getFines() {
        return Fines;
    }
    public ArrayList<String> getTer() {
        return ter;
    }
    public int getXsize(){ return this.xsize;}
    public int getYsize(){ return this.ysize;}
}
