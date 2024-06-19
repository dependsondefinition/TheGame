package Buildings;

import java.io.Serializable;

public class Building implements Actions, Serializable {
    private static final long serialVersionUID = 1L;
    int level;
    int woodPr;
    int rockPr;
    String name;
    Building(int lvl, int wood, int rock, String name) {
        this.level = lvl;
        this.woodPr = wood;
        this.rockPr = rock;
        this.name = name;
    }
    @Override
    public String toString()
    {
        return String.format("Name: %7s, Wood: %d, Rock: %d", name, woodPr, rockPr);
    }

    @Override
    public void action() {

    }

    @Override
    public void improvement() {
        if(level > 0)
        {
            level++;
            action();
        }
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
