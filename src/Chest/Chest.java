package Chest;

import Units.Horseman;
import Units.Unit;

public class Chest extends Unit {
    private final String sgn = "\u001B[43m"+ "\u001B[30m" + "n" + "\u001B[0m" + "\u001B[0m";
    private final int upgrade = (int) ((Math.random() * 4));
    private boolean open = false;
    private boolean onField = false;
    public Chest()
    {
        super();
        this.sign = sgn;
    }
    public void openChest(Unit unit)
    {
        switch(upgrade)
        {
            case 0: //hp bonus or evasion bonus
            {
                if(unit.getHp() < unit.getDefaultHP())
                {
                    unit.setHp(unit.getDefaultHP());
                    System.out.println(unit.getName() + "'s health is restored");
                }
                else{
                    unit.setEvasion(unit.getEvasion() + 0.2F);
                    System.out.println(unit.getName() + "'s evasion is " + unit.getEvasion());
                }
                break;
            }
            case 1: //armor
            {
                unit.setDefence(unit.getDefence() + 5);
                System.out.println(unit.getName() + "'s armor is " + unit.getDefence());
                break;
            }
            case 2: //horse
            {
                if(unit instanceof Horseman){
                    unit.setDefaultMovement(unit.getDefaultMovement() + 2);
                }
                else {
                    unit.setFines(Horseman.fineTree, Horseman.fineSwamp, Horseman.fineHill, Horseman.fineMountain);
                    unit.setDefaultMovement(unit.getDefaultMovement() + 5);
                    System.out.println(unit.getName() + " become a horseman!!!");
                }
                System.out.println(unit.getName() + "'s movement improved to " + unit.getDefaultMovement());
                break;
            }
            case 3: //evasion bonus
            {
                unit.setEvasion(unit.getEvasion() + 0.5f);
                System.out.println(unit.getName() + "'s evasion is " + unit.getEvasion());
                break;
            }
        }
        open = true;
    }
    public boolean canBeOpen(Unit unit)
    {
        return Math.hypot(this.xcord - unit.getX(), this.ycord - unit.getY()) == 1 && !open;
    }
    public void setOnField(boolean onField) {
        this.onField = onField;
    }
    public boolean isOnField() {
        return onField;
    }
    public boolean isOpen() {
        return open;
    }
}
