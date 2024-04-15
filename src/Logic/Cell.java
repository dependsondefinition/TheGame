package Logic;

import Units.Unit;

import java.io.Serializable;

public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;
    private String typeOfTerrain = "";
    private boolean occupied = false;
    public void setUn(Unit unit)
    {
        if(this.isPlain())
        {
            this.typeOfTerrain = unit.getSign();
            this.occupied = true;
        }
    }
    public void releaseUn()
    {
        this.typeOfTerrain = "*";
        this.occupied = false;
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
