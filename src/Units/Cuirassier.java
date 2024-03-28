package Units;

public class Cuirassier extends Horseman {
    public Cuirassier(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public Cuirassier()
    {
        super();
        this.name = "cuirassier";
        this.sign = "?";
        this.hp = 50;
        this.damage = 2;
        this.distance = 1;
        this.defence = 7;
        this.movement = 5;
        this.defaultMovement = this.movement;
        this.price = 23;
    }
}
