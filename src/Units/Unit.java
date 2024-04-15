package Units;

import Logic.Field;

import java.lang.Math;

public class Unit {
    float evasion = 0;
    String name;
    int fineNumber = 0;
    protected String sign;
    int hp;
    int damage;
    int distance;
    int defence;
    float movement;
    float defaultMovement;
    int defaultHP;
    int price;
    boolean seeEn = false;
    protected int xcord;
    protected int ycord;
    int dir = 0;
    Unit(String n, String sgn, int health, int dam, int dist, int def, float move, int pr)
    {
        this.name = n;
        this.sign = sgn;
        this.hp = health;
        this.defaultHP = health;
        this.damage = dam;
        this.distance = dist;
        this.defence = def;
        this.movement = move;
        this.defaultMovement = move;
        this.price = pr;
    }
    protected Unit()
    {

    }
    @Override
    public String toString()
    {
        return String.format("Name: %10s, Sign: %s, HP: %d, Damage: %d, Distance: %d, Defence: %d, Movement: %.1f, Price: %d",
                name, sign, hp, damage, distance, defence, movement, price);
    }
    public void move(Field fld)
    {
        float fine, fineDiag = 0;
        switch(dir) {
            case 1: {
                this.xcord--;
                break;
            }
            case 2: {
                this.ycord++;
                break;
            }
            case 3: {
                this.xcord++;
                break;
            }
            case 4: {
                this.ycord--;
                break;
            }
            case 5: {
                this.xcord--;
                this.ycord++;
                fineDiag = Math.min(fld.fine(fld.getMap().get(getX() + 1).get(getY()).getTer(), fineNumber),
                        fld.fine(fld.getMap().get(getX()).get(getY() - 1).getTer(), fineNumber));
                break;
            }
            case 6: {
                this.xcord--;
                this.ycord--;
                fineDiag = Math.min(fld.fine(fld.getMap().get(getX() + 1).get(getY()).getTer(), fineNumber),
                        fld.fine(fld.getMap().get(getX()).get(getY() + 1).getTer(), fineNumber));
                break;
            }
            case 7: {
                this.xcord++;
                this.ycord++;
                fineDiag = Math.min(fld.fine(fld.getMap().get(getX() - 1).get(getY()).getTer(), fineNumber),
                        fld.fine(fld.getMap().get(getX()).get(getY() - 1).getTer(), fineNumber));
                break;
            }
            case 8: {
                this.xcord++;
                this.ycord--;
                fineDiag = Math.min(fld.fine(fld.getMap().get(getX() - 1).get(getY()).getTer(), fineNumber),
                        fld.fine(fld.getMap().get(getX()).get(getY() + 1).getTer(), fineNumber));

                break;
            }
            default: System.out.println("Wrong input"); break;
        }
        fine = fld.fine(fld.getMap().get(getX()).get(getY()).getTer(), fineNumber);
        this.movement -= fine + fineDiag;
    }
    public void attack(Unit target)
    {
        if(target.evasion > Math.random())
        {
            System.out.println(target.name + " dodged the attack with evasion " + target.evasion);
        }
        else {
            if (target.defence > this.damage) {
                target.defence -= this.damage;
            } else {
                target.hp += target.defence - this.damage;
                target.defence = 0;
            }
        }
        seeEn = false;
    }
    public int getFineNumber()
    {
        return fineNumber;
    }
    public void setFineNumber(int x)
    {
        if(x >= 0 && x <= 2)
        {
            fineNumber = x;
        }
    }
    public void setCoord(int x, int y)
    {
        this.xcord = x;
        this.ycord = y;
    }
    public void setDir(int direction)
    {
        this.dir = direction;
    }
    public void setMovement(float mov) {
        this.movement = mov;
    }
    public void setSign(String sg) {
        this.sign = sg;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setDefence(int def){
        this.defence = def;
    }

    public void setEvasion(float evas)
    {
        this.evasion = evas;
    }

    public void setDefaultMovement(float defaultMovement) {
        this.defaultMovement = defaultMovement;
    }

    public void seeEnemy(Unit enemy)
    {
        seeEn = Math.hypot(enemy.getX() - this.getX(), (enemy.getY() - this.getY())) <= this.distance;
    }
    public boolean isSeeEn() {
        return this.seeEn;
    }
    public int getHp() {return this.hp; }
    public int getDefaultHP() {
        return defaultHP;
    }

    public float getMovement() { return this.movement;}
    public float getDefaultMovement() {return this.defaultMovement; }
    public int getDir() { return this.dir; }
    public int getDefence(){
        return this.defence;
    }
    public float getEvasion() {
        return this.evasion;
    }
    public String getName() { return this.name; }
    public int getX() { return this.xcord;}
    public int getY() { return this.ycord;}
    public int getPrice() { return this.price;}
    public String getSign()
    {
        return this.sign;
    }
}


