package Units;

public class Swordsman extends Melee {

    public Swordsman(String nam, String n, int health, int dam, int dist, int def, float move, int pr) {
        super(nam, n, health, dam, dist, def, move, pr);
    }
    public Swordsman()
    {
        super();
        this.name = "swordsman";
        this.sign = "1";
        this.hp = 50;
        this.damage = 5;
        this.defence = 8;
        this.movement = 3;
        this.defaultMovement = this.movement;
        this.price = 10;
    }
}
