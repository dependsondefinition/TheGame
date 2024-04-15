package Units;

public class Horseman extends Unit{
    public Horseman(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
        this.fineNumber = 2;
    }
}
