package Units;

public class Spearman extends Melee {
    public Spearman(String nam, String n, int health, int dam, int dist, int def, float move, int pr) {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public Spearman()
    {
        super();
        this.name = "spearman";
        this.sign = "|";
        this.hp = 35;
        this.damage = 3;
        this.defence = 4;
        this.movement = 6;
        this.defaultMovement = this.movement;
        this.price = 15;
    }
}
