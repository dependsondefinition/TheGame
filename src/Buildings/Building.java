package Buildings;

public class Building {
    int level;
    int woodPr;
    int rockPr;
    String name;
    Building(int lvl, int wood, int rock, String name)
    {
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
    public void setLevel(int level) {
        if (this.level > 0) {
            this.level = level;
        }
    }
    public int getLevel() {
        return level;
    }
    public String getName() {
        return name;
    }
}
