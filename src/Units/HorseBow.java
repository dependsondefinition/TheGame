package Units;

public class HorseBow extends Horseman {
    public HorseBow(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public HorseBow()
    {
        super();
        this.name = "horsebow";
        this.sign = "}";
        this.hp = 25;
        this.damage = 3;
        this.distance = 3;
        this.defence = 2;
        this.movement = 5;
        this.defaultMovement = this.movement;
        this.price = 25;
    }
}
