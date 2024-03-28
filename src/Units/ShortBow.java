package Units;

public class ShortBow extends Shooter {
    public ShortBow(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public ShortBow(){
        super();
        this.name = "shortbow";
        this.sign = ")";
        this.hp = 25;
        this.damage = 3;
        this.distance = 3;
        this.defence = 4;
        this.movement = 4;
        this.defaultMovement = this.movement;
        this.price = 19;
    }
}
