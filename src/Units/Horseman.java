package Units;

public class Horseman extends Unit{
    public Horseman(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public Horseman()
    {
        super();
        this.fineTree = 1.5f;
        this.fineSwamp = 2.2f;
        this.fineHill = 1.2f;
        this.fineMountain = 3f;
    }
}
