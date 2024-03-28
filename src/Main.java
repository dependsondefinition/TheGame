import Logic.*;

public class Main {
    public static void main(String[] args)
    {
        //todo во время хода с вероятностью 0.5 появляется сундук, в котором лежит улучшение ( виды улучшения: аптечка (восстанавливает здоровье или бонус (шанс уклонения повышается)),
        // броня (увеличивает броню), лошадь ( может стать конным юнитом + улучшает перемещение), шанс уклонения)
        Logic logic = new Logic();
        Bot comp = new Bot();
        logic.start();
        System.out.println(logic);
        Field battle = new Field(5, 10);
        System.out.println(battle);
        logic.setField(battle);
        while (logic.isContSpawn())
        {
            logic.init();
            if(logic.isContSpawn()) { comp.addNew(logic);}
        }
        logic.setUnits(logic.retUs());
        logic.setUnits(comp.getBotUnits());
        System.out.println(battle);
        while(!logic.retUs().isEmpty() && !comp.getBotUnits().isEmpty())
        {
            comp.showUnits();
            logic.usrMove(comp);
            logic.setUnits(logic.retUs());
            if(!comp.getBotUnits().isEmpty())
            {
                comp.botMove(logic);
                logic.setUnits(comp.getBotUnits());
            }
            System.out.println(battle);
        }
        if(logic.retUs().isEmpty())
        {
            System.out.println("You LOSE!!!");
        }
        else if(comp.getBotUnits().isEmpty())
        {
            System.out.println("You WIN!!!");
        }
    }
}