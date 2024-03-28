package Units;

public class CrossBow extends Shooter {
    public CrossBow(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public CrossBow()
    {
        super();
        this.name = "crossbow";
        this.sign = ">";
        this.hp = 40;
        this.damage = 7;
        this.distance = 6;
        this.defence = 3;
        this.movement = 2;
        this.defaultMovement = this.movement;
        this.price = 23;
    }
}
