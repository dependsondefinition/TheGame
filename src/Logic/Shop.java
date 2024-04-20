package Logic;

import Units.*;

import java.io.Serializable;
import java.util.List;

public class Shop implements Serializable {
    private int budget;
    private final List<Unit> unitTable = List.of(new Swordsman(), new Spearman(), new Axeman(), new LongBow(), new ShortBow(),
                new CrossBow(), new Knight(), new Cuirassier(), new HorseBow());;
    Shop() {
        budget = 75;
    }
    public void buy(int num, Player user) {
        switch (num) {
            case 1: { user.addUnit(new Swordsman()); break;}
            case 2: { user.addUnit(new Spearman()); break;}
            case 3: { user.addUnit(new Axeman()); break;}
            case 4: { user.addUnit(new LongBow()); break;}
            case 5: { user.addUnit(new ShortBow()); break;}
            case 6: { user.addUnit(new CrossBow()); break;}
            case 7: { user.addUnit(new Knight()); break;}
            case 8: { user.addUnit(new Cuirassier()); break;}
            case 9: { user.addUnit(new HorseBow()); break;}
            case 10: {user.addUnit(new SuperUnit(GameProcess.scan)); budget = 0; break;}
        }
        if(!(user instanceof Bot))
        {
            Unit un = user.getUnits().getLast();
            if(!(un instanceof SuperUnit)) {
                budget -= un.getPrice();
            }
            un.setDefence(un.getDefence() + user.getArmorUp());
            un.setDamage(un.getDamage() + user.getAttackUp());
            un.setHp(un.getHp() + user.getHealthUp());
            un.setDefaultHP(un.getDefaultHP() + user.getHealthUp());
            un.setMovement(un.getMovement() + user.getMoveUp());
            un.setDefaultMovement(un.getDefaultMovement() + user.getMoveUp());
        }
    }
    public boolean affordable(int num)
    {
        return budget >= unitTable.get(num).getPrice();
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
    public int getBudget() {
        return budget;
    }
    public void setBudget(int incr) {
        budget = incr;
    }
}
