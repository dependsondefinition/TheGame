package Units;

public class Knight extends Horseman {
    public Knight(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public Knight()
    {
        super();
        this.name = "knight";
        this.sign = "$";
        this.hp = 30;
        this.damage = 5;
        this.distance = 1;
        this.defence = 3;
        this.movement = 6;
        this.defaultMovement = this.movement;
        this.price = 20;
    }
}
