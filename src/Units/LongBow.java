package Units;

public class LongBow extends Shooter {
    public LongBow(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public LongBow()
    {
        super();
        this.name = "longbow";
        this.sign = "]";
        this.hp = 30;
        this.damage = 6;
        this.distance = 5;
        this.defence = 8;
        this.movement = 2;
        this.defaultMovement = this.movement;
        this.price = 15;
    }
}
