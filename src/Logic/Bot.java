package Logic;

import Units.*;

import java.util.ArrayList;

public class Bot {
    private ArrayList<Unit> botUnits = new ArrayList<>();
    private Unit target;
    private int botCount = 0;
    public void addNew(Logic hiddenLogic){
        int rand = 0;
        if(botUnits.isEmpty())
        {
            rand = (int) (Math.random() * 8) + 1;
        }
        else if(botUnits.getLast() instanceof Melee)
        {
            rand = (int) (Math.random() * 5) + 4;
        }
        else if(botUnits.getLast() instanceof Horseman)
        {
            rand = (int) (Math.random() * 5) + 1;
        }
        else if(botUnits.getLast() instanceof Shooter)
        {
            rand = (int) (Math.random() * 2) + (int) Math.round(Math.random()) * 6 + 1;
        }
        switch (rand) {
            case 1: { botUnits.add(new Swordsman()); break;}
            case 2: { botUnits.add(new Spearman()); break;}
            case 3: { botUnits.add(new Axeman()); break;}
            case 4: { botUnits.add(new LongBow()); break;}
            case 5: { botUnits.add(new ShortBow()); break;}
            case 6: { botUnits.add(new CrossBow()); break;}
            case 7: { botUnits.add(new Knight()); break;}
            case 8: { botUnits.add(new Cuirassier()); break;}
            case 9: { botUnits.add(new HorseBow()); break;}
        }
        Unit lastUnit = botUnits.getLast();
        lastUnit.setCoord(hiddenLogic.fld.getXsize() - 1, hiddenLogic.fld.getYsize() - botUnits.size());
        lastUnit.setSign("\u001B[31m" + lastUnit.getSign() + "\u001B[0m");
    }
    public void showUnits()
    {
        System.out.println("Your enemies:");
        for (Unit botUnit : botUnits) {
            System.out.println(botUnit);
        }
        System.out.println();
    }
    public void botMove(Logic hiddenLogic)
    {
        if (botCount >= botUnits.size()) {
            botCount = 0;
            hiddenLogic.toDefault(botUnits);
        }
        Unit thisBot = botUnits.get(botCount);
        if(hiddenLogic.chest.isOnField() && !hiddenLogic.chest.isOpen())
        {
            target = hiddenLogic.chest;
        }
        else {
            target = setTarget(thisBot, hiddenLogic.retUs());
            thisBot.seeEnemy(target);
        }
        if(hiddenLogic.chest.canBeOpen(thisBot))
        {
            hiddenLogic.chest.openChest(thisBot);
            hiddenLogic.fld.getMap().get(hiddenLogic.chest.getX()).get(hiddenLogic.chest.getY()).releaseUn();
        }
        else if (thisBot.isSeeEn()) {
            thisBot.attack(target);
            System.out.println("Bot " + thisBot.getName() + " attacked your unit " + target.getName());
            System.out.println(target.getName() + "'s HP: " + target.getHp());
            if (target.getHp() <= 0) {
                hiddenLogic.fld.getMap().get(target.getX()).get(target.getY()).releaseUn();
                hiddenLogic.retUs().remove(target);
                target = null;
            }
            botCount++;
        }
        else if (thisBot.getMovement() >= 1f) {
            chooseDir(thisBot, hiddenLogic);
            if(thisBot.getDir() != 0)
            {
                hiddenLogic.fld.getMap().get(thisBot.getX()).get(thisBot.getY()).releaseUn();
                thisBot.move(hiddenLogic.fld);
                if(thisBot.getMovement() < 1f)
                {
                    botCount++;
                }
            }
            else
            {
                botCount++;
            }
        }
    }
    private void chooseDir(Unit bot, Logic hiddenLogic)
    {
        int dir = 0;
        if(target.getX() > bot.getX())
        {
            if(target.getY() > bot.getY()) // if target is right+down
            {
                if(hiddenLogic.checkDir(1, 1, bot))
                {
                    dir = 7;
                }
                else if(hiddenLogic.checkDir(0, 1, bot))
                {
                    dir = 2;
                }
                else if(hiddenLogic.checkDir(1, 0, bot))
                {
                    dir = 3;
                }
            }
            else if(target.getY() < bot.getY()) //if target is left+down
            {
                if(hiddenLogic.checkDir(1, -1, bot))
                {
                    dir = 8;
                }
                else if(hiddenLogic.checkDir(0, -1, bot))
                {
                    dir = 4;
                }
                else if(hiddenLogic.checkDir(1, 0, bot))
                {
                    dir = 3;
                }
            }
            else //if target is down
            {
                if(hiddenLogic.checkDir(1, 0, bot)) {
                    dir = 3;
                }
                else if(bot.getY() < hiddenLogic.fld.getYsize() - 1)
                {
                    if(hiddenLogic.checkDir(1, 1, bot)) {
                        dir = 7;
                    }
                    else if(hiddenLogic.checkDir(0, 1, bot))
                    {
                        dir = 2;
                    }
                }
                else if(bot.getY() > 0)
                {
                    if(hiddenLogic.checkDir(1, -1, bot)) {
                        dir = 8;
                    }
                    else if(hiddenLogic.checkDir(0, -1, bot))
                    {
                        dir = 4;
                    }
                }
            }
        }
        else if(target.getX() < bot.getX())
        {
            if(target.getY() > bot.getY()) // if target is right+up
            {
                if(hiddenLogic.checkDir(-1, 1, bot))
                {
                    dir = 5;
                }
                else if(hiddenLogic.checkDir(0, 1, bot))
                {
                    dir = 2;
                }
                else if(hiddenLogic.checkDir(-1, 0, bot))
                {
                    dir = 1;
                }
            }
            else if(target.getY() < bot.getY()) // if target is left+up
            {
                if(hiddenLogic.checkDir(-1, -1, bot))
                {
                    dir = 6;
                }
                else if(hiddenLogic.checkDir(0, -1, bot))
                {
                    dir = 4;
                }
                else if(hiddenLogic.checkDir(-1, 0, bot))
                {
                    dir = 1;
                }
            }
            else  // if target is up
            {
                if(hiddenLogic.checkDir(-1, 0, bot)) {
                    dir = 1;
                }
                else if(bot.getY() < hiddenLogic.fld.getYsize() - 1)
                {
                    if(hiddenLogic.checkDir(-1, 1, bot)) {
                        dir = 5;
                    }
                    else if(hiddenLogic.checkDir(0, 1, bot))
                    {
                        dir = 2;
                    }
                }
                else if(bot.getY() > 0)
                {
                    if(hiddenLogic.checkDir(-1, -1, bot)) {
                        dir = 6;
                    }
                    else if(hiddenLogic.checkDir(0, -1, bot))
                    {
                        dir = 4;
                    }
                }
            }
        }
        else
        {
            if(target.getY() > bot.getY()) // if target is right
            {
                if(hiddenLogic.checkDir(0, 1, bot))
                {
                    dir = 2;
                }
                else if(bot.getX() < hiddenLogic.fld.getXsize() - 1)
                {
                    if(hiddenLogic.checkDir(1, 1, bot))
                    {
                        dir = 7;
                    }
                    else if(hiddenLogic.checkDir(1, 0, bot))
                    {
                        dir = 3;
                    }
                }
                else if(bot.getX() > 0)
                {
                    if(hiddenLogic.checkDir(-1, 1, bot))
                    {
                        dir = 5;
                    }
                    else if(hiddenLogic.checkDir(-1, 0, bot))
                    {
                        dir = 1;
                    }
                }
            }
            else if(target.getY() < bot.getY()) // if target is left
            {
                if(hiddenLogic.checkDir(0, -1, bot))
                {
                    dir = 4;
                }
                else if(bot.getX() < hiddenLogic.fld.getXsize() - 1)
                {
                    if(hiddenLogic.checkDir(1, -1, bot))
                    {
                        dir = 8;
                    }
                    else if(hiddenLogic.checkDir(1, 0, bot))
                    {
                        dir = 3;
                    }
                }
                else if(bot.getX() > 0)
                {
                    if(hiddenLogic.checkDir(-1, -1, bot))
                    {
                        dir = 6;
                    }
                    else if(hiddenLogic.checkDir(-1, 0, bot))
                    {
                        dir = 1;
                    }
                }
            }
        }
        bot.setDir(dir);
    }
    private Unit setTarget(Unit bot, ArrayList<Unit> user)
    {
        Unit target = user.getFirst();
        for (Unit unit : user) {
            if (Math.hypot(bot.getX() - unit.getX(), bot.getY() - unit.getY())
                    < Math.hypot(bot.getX() - target.getX(), bot.getY() - target.getY())) {
                target = unit;
            }
        }
        return target;
    }
    public ArrayList<Unit> getBotUnits() { return botUnits;}
}