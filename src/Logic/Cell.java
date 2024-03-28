package Logic;

import Units.Unit;

public class Cell {
    private String typeOfTerrain = "";
    private boolean occupied = false;
    private Unit un;
    public Cell(String input)
    {
        this.typeOfTerrain = input;
    }
    public void setUn(Unit unit)
    {
        if(this.isPlain())
        {
            this.un = unit;
            this.typeOfTerrain = unit.getSign();
            this.occupied = true;
        }
    }
    public void releaseUn()
    {
        this.typeOfTerrain = "*";
        this.occupied = false;
        this.un = null;
    }
    public String getTer()
    {
        return this.typeOfTerrain;
    }
    public boolean isPlain()
    {
        return this.typeOfTerrain.equals("*");
    }
    public boolean isOccupied()
    {
        return occupied;
    }
}
