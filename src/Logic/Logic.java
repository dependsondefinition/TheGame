package Logic;

import Units.*;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logic {
    private int budget = 75;
    private boolean canSpawn = true;
    private final Scanner scan = new Scanner(System.in);
    Field fld;
    private int count = 0;
    private Unit enemy;
    private final List<Unit> unitTable = List.of(new Swordsman(), new Spearman(), new Axeman(), new LongBow(), new ShortBow(),
            new CrossBow(), new Knight(), new Cuirassier(), new HorseBow());
    private ArrayList<Unit> userUnits = new ArrayList<>();
    float fine = 0;
    public void setField(Field btl)
    {
        this.fld = btl;
    }
    public void start()
    {
        System.out.println("This town is going to be attacked!");
        System.out.println("You need to recruit some mercenaries!");
        System.out.println("Your budget is: " + budget);
        System.out.println("Here is the unit table");
    }
    @Override
    public String toString()
    {
        String table = "";
        for(int i = 1; i < unitTable.size() + 1; i++)
        {
            table += i + ". " + unitTable.get(i - 1).toString() + "\n";
        }
        return table;
    }
    public void init()
    {
        System.out.println("Your budget is: " + budget);
        if(budget >= unitTable.getFirst().getPrice())
        {
            System.out.println("Type number of the unit, which you want to hire");
            System.out.println("If you dont want to buy units type 0");
            spawnUnit(scan.nextInt());
        }
        else{
            System.out.println("Your budget is too low to hire units!");
            canSpawn = false;
        }
    }
    public void usrMove(Bot hiddenBot)
    {
        if (count >= userUnits.size()) {
            count = 0;
            toDefault(userUnits);
        }
        Unit thisUnit = userUnits.get(count);
        checkEnemy(thisUnit, hiddenBot.getBotUnits());
        choice(count);
        int dec = scan.nextInt();
        if (dec == 3 || thisUnit.getMovement() < 1f && !thisUnit.isSeeEn()) {
            setUnits(userUnits);
            count++;
        } else if (dec == 1 && userUnits.get(count).getMovement() >= 1f) {
            fld.getMap().get(thisUnit.getX()).get(thisUnit.getY()).releaseUn();
            System.out.println("Choose direction of the movement");
            chDir(thisUnit);
            thisUnit.setDir(scan.nextInt());
            thisUnit.move(fld);
            setUnits(userUnits);
        } else if (dec == 2 && thisUnit.isSeeEn()) {
            showEnemy(thisUnit, hiddenBot.getBotUnits());
            chooseEnemy(hiddenBot.getBotUnits());
            thisUnit.attack(enemy);
            if (enemy.getHp() <= 0) {
                fld.getMap().get(enemy.getX()).get(enemy.getY()).releaseUn();
                hiddenBot.getBotUnits().remove(enemy);
                enemy = null;
            }
            count++;
        }
    }
    private void checkEnemy(Unit user, ArrayList <Unit> enemies)
    {
        for(int i = 0; i < enemies.size(); i++)
        {
            user.seeEnemy(enemies.get(i));
            if(user.isSeeEn())
            {
                break;
            }
        }
    }
    private void showEnemy(Unit user, ArrayList <Unit> enemies)
    {
        for(int i = 0; i < enemies.size(); i++)
        {
            user.seeEnemy(enemies.get(i));
            if(user.isSeeEn())
            {
                System.out.println(i + 1 + ". " + enemies.get(i));
            }
        }
    }
    private void chooseEnemy(ArrayList <Unit> enemies)
    {
        System.out.println("Choose enemy");
        enemy = enemies.get(scan.nextInt() - 1);
    }
    void toDefault(ArrayList <Unit> units)
    {
        for(int i = 0; i < units.size(); i++)
        {
            if(units.get(i).getMovement() < units.get(i).getDefaultMovement())
            {
                units.get(i).setMovement(units.get(i).getDefaultMovement());
            }
        }
    }
    private void spawnUnit(int num)
    {
        if(num == 0)
        {
            canSpawn = false;
        }
        else if(num == 10)
        {
            if(userUnits.isEmpty()) {
                userUnits.add(new SuperUnit(scan));
                budget = 0;
            }
            else {
                canSpawn = false;
            }
        }
        else if(budget >= unitTable.get(num - 1).getPrice())
        {
            switch (num) {
                case 1: { userUnits.add(new Swordsman()); break;}
                case 2: { userUnits.add(new Spearman()); break;}
                case 3: { userUnits.add(new Axeman()); break;}
                case 4: { userUnits.add(new LongBow()); break;}
                case 5: { userUnits.add(new ShortBow()); break;}
                case 6: { userUnits.add(new CrossBow()); break;}
                case 7: { userUnits.add(new Knight()); break;}
                case 8: { userUnits.add(new Cuirassier()); break;}
                case 9: { userUnits.add(new HorseBow()); break;}
            }
            Unit lastUnit = userUnits.getLast();
            lastUnit.setCoord(0, userUnits.size() - 1);
            lastUnit.setSign("\u001B[32m" + lastUnit.getSign() + "\u001B[0m");
            budget = budget - lastUnit.getPrice();
        }
        else {
            System.out.println("You cant afford to hire this unit!");
            System.out.println("Do you want to hire other units? (Y|N)");
            if(scan.next().equals("N"))
            {
                canSpawn = false;
            }
        }
    }
    private void choice(int num)
    {
        System.out.println(userUnits.get(num));
        System.out.println("Choose action from list:");
        if(userUnits.get(num).getMovement() >= 1f)
        {
            System.out.println("1. Move this unit");
        }
        if(userUnits.get(num).isSeeEn())
        {
            System.out.println("2. Attack enemy");
        }
        System.out.println("3. Skip this unit (idle)");
    }
    private void chDir(Unit un)
    {
        boolean upFree = false, rightFree = false, downFree = false, leftFree = false;
        if(un.getX() > 0) {
            if (checkDir(-1, 0, un)) {
                System.out.println("1. Up");
            }
            upFree = true;
        }
        if(un.getY() < fld.getYsize() - 1) {
            if (checkDir(0, 1, un)) {
                System.out.println("2. Right");
            }
            rightFree = true;
        }
        if(un.getX() < fld.getXsize() - 1) {
            if (checkDir(1, 0, un)) {
                System.out.println("3. Down");
            }
            downFree = true;
        }
        if(un.getY() > 0) {
            if (checkDir(0,  -1, un)) {
                System.out.println("4. Left");
            }
            leftFree = true;
        }
        if(upFree && rightFree && checkDir(-1, 1, un))
        {
            System.out.println("5. Up + Right");
        }
        if(upFree && leftFree && checkDir(-1, -1, un))
        {
            System.out.println("6. Up + Left");
        }
        if(downFree && rightFree && checkDir(1, 1, un))
        {
            System.out.println("7. Down + Right");
        }
        if(downFree && leftFree && checkDir(1, -1, un))
        {
            System.out.println("8. Down + Left");
        }
    }
    boolean checkDir(int x, int y, Unit unit)
    {
        Cell nextCell = fld.getMap().get(unit.getX() + x).get(unit.getY() + y);
        if(x == 0 || y == 0)
        {
            fine += unit.fine(nextCell.getTer());
        }
        else
        {
            Cell XCell = fld.getMap().get(unit.getX() + x - Integer.compare(x, 0)).get(unit.getY() + y);
            Cell YCell = fld.getMap().get(unit.getX() + x).get(unit.getY() + y - Integer.compare(y, 0));
            fine += unit.fine(nextCell.getTer()) + Math.min(unit.fine(XCell.getTer()), unit.fine(YCell.getTer()));
        }
        if(nextCell.isPlain() && unit.getMovement() >= fine)
        {
            fine = 0;
            return true;
        }
        else if(unit.getMovement() < fine || nextCell.isOccupied())
        {
            fine = 0;
            return false;
        }
        else { return checkDir(x + Integer.compare(x, 0), y + Integer.compare(y, 0), unit);}

    }
    public ArrayList<Unit> retUs() {return this.userUnits;}
    public boolean isContSpawn() { return canSpawn;}
    public void setUnits(ArrayList<Unit> units)
    {
        for(int i = 0; i < units.size(); i++)
        {
            fld.getMap().get(units.get(i).getX()).get(units.get(i).getY()).setUn(units.get(i));
            while (!fld.getMap().get(units.get(i).getX()).get(units.get(i).getY()).isOccupied()) {
                units.get(i).move(this.fld);
                fld.getMap().get(units.get(i).getX()).get(units.get(i).getY()).setUn(units.get(i));
            }
        }
    }
}
