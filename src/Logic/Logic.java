package Logic;

import Units.*;
import Chest.Chest;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logic {
    private int budget = 75;
    private boolean canSpawn = true;
    private final Scanner scan;
    Field fld;
    Chest chest = new Chest();
    private int count = 0;
    private Unit enemy;
    private final List<Unit> unitTable = List.of(new Swordsman(), new Spearman(), new Axeman(), new LongBow(), new ShortBow(),
            new CrossBow(), new Knight(), new Cuirassier(), new HorseBow());
    private ArrayList<Unit> userUnits = new ArrayList<>();
    float fine = 0;
    Logic(Scanner scan){
        this.scan = scan;
        System.out.println("This town is going to be attacked!");
        System.out.println("You need to recruit some mercenaries!");
        System.out.println("Your budget is: " + budget);
        System.out.println("Here is the unit table");
    }
    public void setField(Field btl)
    {
        this.fld = btl;
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
        if(Math.random() > 0.5 && !chest.isOnField())
        {
            spawnChest();
        }
        choice(count);
        int dec = scan.nextInt();
        if (dec == 3 || thisUnit.getMovement() < 1f && !thisUnit.isSeeEn()) {
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
                addWR(enemy);
                fld.getMap().get(enemy.getX()).get(enemy.getY()).releaseUn();
                hiddenBot.getBotUnits().remove(enemy);
                enemy = null;
            }
            count++;
        } else if(dec == 4 && chest.canBeOpen(thisUnit)){
            chest.openChest(thisUnit);
            fld.getMap().get(chest.getX()).get(chest.getY()).releaseUn();
        }
    }
    private void addWR(Unit unit)
    {
        if(unit instanceof Horseman)
        {
            Town.wood += 2;
            Town.rock += 1;
        }
        else if(unit instanceof Melee)
        {
            Town.wood += 1;
            Town.rock += 1;
        }
        else if(unit instanceof Shooter)
        {
            Town.wood += 2;
            Town.rock += 2;
        }
    }
    private void checkEnemy(Unit user, ArrayList <Unit> enemies)
    {
        for (Unit unit : enemies) {
            user.seeEnemy(unit);
            if (user.isSeeEn()) {
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
        for (Unit unit : units) {
            if (unit.getMovement() < unit.getDefaultMovement()) {
                unit.setMovement(unit.getDefaultMovement());
            }
        }
    }
    private void spawnChest()
    {
        int x = (int) (Math.random() * (fld.getXsize() - 2) + 1),y = (int) (Math.random() * (fld.getYsize() - 2) + 1);
        while(!fld.getMap().get(x).get(y).isPlain())
        {
            x = (int) (Math.random() * (fld.getXsize() - 2) + 1);
            y = (int) (Math.random() * (fld.getYsize() - 2) + 1);
        }
        chest.setCoord(x, y);
        fld.getMap().get(x).get(y).setUn(chest);
        chest.setOnField(true);
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
        Unit unit = userUnits.get(num);
        System.out.println(unit);
        System.out.println("Choose action from list:");
        if(unit.getMovement() >= 1f)
        {
            System.out.println("1. Move this unit");
        }
        if(unit.isSeeEn())
        {
            System.out.println("2. Attack enemy");
        }
        System.out.println("3. Skip this unit (idle)");
        if(chest.canBeOpen(unit) && chest.isOnField())
        {
            System.out.println("4. Get upgrade");
        }
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
        int n = unit.getFineNumber();
        if(x == 0 || y == 0)
        {
            fine += fld.fine(nextCell.getTer(), n);
        }
        else
        {
            Cell XCell = fld.getMap().get(unit.getX() + x - Integer.compare(x, 0)).get(unit.getY() + y);
            Cell YCell = fld.getMap().get(unit.getX() + x).get(unit.getY() + y - Integer.compare(y, 0));
            fine += fld.fine(nextCell.getTer(), n) + Math.min(fld.fine(XCell.getTer(), n), fld.fine(YCell.getTer(), n));
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
        for (Unit unit : units) {
            fld.getMap().get(unit.getX()).get(unit.getY()).setUn(unit);
            while (!fld.getMap().get(unit.getX()).get(unit.getY()).isOccupied()) {
                unit.move(this.fld);
                fld.getMap().get(unit.getX()).get(unit.getY()).setUn(unit);
            }
        }
    }
}
