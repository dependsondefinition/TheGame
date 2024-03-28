package Units;

public class Melee extends Unit {
    public Melee(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }

    public Melee() {
        super();
        this.distance = 1;
        this.fineTree = 1.2f;
        this.fineSwamp = 1.5f;
        this.fineHill = 2f;
        this.fineMountain = 2.5f;
    }
}
