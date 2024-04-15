package Logic;

import java.util.Scanner;

public class GameProcess {
    private final Town town;
    private final Logic logic;
    private final Bot comp;
    private final Field battle;
    private final Scanner scan = new Scanner(System.in);
    private FileManager manager = new FileManager();
    public GameProcess()
    {
        town = new Town(scan);
        System.out.println(town);
        comp = new Bot();
        System.out.println("Maps to load:");
        manager.showFiles();
        battle = manager.LoadMap(scan.next());
        logic = new Logic(scan);
        logic.setField(battle);
    }
    public void Process()
    {
        System.out.println(logic);
        System.out.println(battle);
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
            Town.wood += 5 * logic.retUs().size();
            Town.rock += 5 * logic.retUs().size();
        }
    }
}
