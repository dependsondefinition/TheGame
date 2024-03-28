package Units;

public class Shooter extends Unit {
    public Shooter(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
        this.fineTree = 1f;
        this.fineSwamp = 1.8f;
        this.fineHill = 2.2f;
        this.fineMountain = 3.5f;
    }
    public Shooter()
    {
        super();
    }
}
