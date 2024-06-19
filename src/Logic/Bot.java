package Logic;

import Units.*;

import java.util.ArrayList;

public class Bot extends Player {
    private Unit target;
    private int botCount;
    Bot()
    {
        super();
        botCount = 0;
    }
    public void addNew(Logic hiddenLogic, Bot bot){
        int rand = 0;
        if(Units.isEmpty())
        {
            rand = (int) (Math.random() * 8) + 1;
        }
        else if(Units.getLast() instanceof Melee)
        {
            rand = (int) (Math.random() * 5) + 4;
        }
        else if(Units.getLast() instanceof Horseman)
        {
            rand = (int) (Math.random() * 5) + 1;
        }
        else if(Units.getLast() instanceof Shooter)
        {
            rand = (int) (Math.random() * 2) + (int) Math.round(Math.random()) * 6 + 1;
        }
        hiddenLogic.shop.buy(rand, bot);
        Unit lastUnit = Units.getLast();
        lastUnit.setCoord(hiddenLogic.fld.getXsize() - 1, hiddenLogic.fld.getYsize() - Units.size());
        lastUnit.setSign("\u001B[31m" + lastUnit.getSign() + "\u001B[0m");
    }
    public void showUnits()
    {
        System.out.println("Your enemies:");
        for (Unit botUnit : Units) {
            System.out.println(botUnit);
        }
        System.out.println();
    }
    public void botMove(Logic hiddenLogic, Bot bot)
    {
        if (botCount >= Units.size()) {
            botCount = 0;
            hiddenLogic.toDefault(Units);
        }
        Unit thisBot = Units.get(botCount);
        if(hiddenLogic.chest.isOnField() && !hiddenLogic.chest.isOpen())
        {
            target = hiddenLogic.chest;
        }
        else {
            target = setTarget(thisBot, hiddenLogic.player.getUnits());
            thisBot.seeEnemy(target);
        }
        if(hiddenLogic.chest.canBeOpen(thisBot))
        {
            hiddenLogic.chest.openChest(thisBot);
            hiddenLogic.fld.getMap().get(hiddenLogic.chest.getX()).get(hiddenLogic.chest.getY()).releaseUn();
            target = null;
        }
        else if (thisBot.isSeeEn()) {
            thisBot.attack(target);
            System.out.println("Bot " + thisBot.getName() + " attacked your unit " + target.getName());
            System.out.println(target.getName() + "'s HP: " + target.getHp());
            if (target.getHp() <= 0) {
                hiddenLogic.fld.getMap().get(target.getX()).get(target.getY()).releaseUn();
                hiddenLogic.player.getUnits().remove(target);
                target = null;
            }
            botCount++;
        }
        else if (thisBot.getMovement() >= 1f) {
            chooseDir(thisBot, hiddenLogic);
            if(thisBot.getDir() != 0)
            {
                hiddenLogic.fld.getMap().get(thisBot.getX()).get(thisBot.getY()).releaseUn();
                thisBot.move(hiddenLogic.fld, bot);
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
}