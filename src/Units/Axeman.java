package Units;

public class Axeman extends Melee
{
    public Axeman(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public Axeman()
    {
        super();
        this.name = "axeman";
        this.sign = "7";
        this.hp = 45;
        this.damage = 9;
        this.defence = 3;
        this.movement = 4;
        this.defaultMovement = this.movement;
        this.price = 20;
    }
}
