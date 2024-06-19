package Units;

public class Melee extends Unit {
    public Melee(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
        this.distance = 1;
    }
}
