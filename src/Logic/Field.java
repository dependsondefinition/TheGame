package Logic;

import java.util.ArrayList;
import java.lang.Math;

import static java.lang.String.valueOf;

public class Field {
    private int xsize;
    private int ysize;
    private final char[] typeOfObstacle = {'*', '!', '#', '@', '^'}; //* - plain, ! - tree, # - swamp, @ - hill, ^ - mountain

    private ArrayList<ArrayList<Cell>> Map = new ArrayList<>();
    public Field (int x, int y)
    {
        this.xsize = x;
        this.ysize = y;
        this.generate();
    }
    private void generate()
    {
        int terrain;
        for(int i = 0; i < xsize; i++)
        {
            int lim = 3;
            terrain = (int) (Math.random() * 4) + 1;
            Map.add(i, new ArrayList<Cell>());
            for(int j = 0; j < ysize; j++)
            {
                if(i == 0 || i == xsize - 1 || lim == 0 || j == 0 || j == ysize - 1)
                {
                    Map.get(i).add(j, new Cell(valueOf(typeOfObstacle[0])));
                }
                else
                {
                    Map.get(i).add(j, new Cell(valueOf(typeOfObstacle[randTer(terrain)])));
                    if(!Map.get(i).get(j - 1).getTer().equals("*") || !Map.get(i - 1).get(j).getTer().equals("*"))
                    {
                        Map.get(i).add(j, new Cell(valueOf(typeOfObstacle[0])));
                    }
                    else if (!Map.get(i).get(j).getTer().equals("*")){
                        lim--;
                    }
                }
            }
        }
    }
    public ArrayList<ArrayList<Cell>> getMap()
    {
        return this.Map;
    }
    private int randTer(int ter1)
    {
        if(Math.round(Math.random()) == 1)
        {
            return 0;
        }
        else
        {
            return ter1;
        }
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
    public int getXsize(){ return this.xsize;}
    public int getYsize(){ return this.ysize;}
}
