package Buildings;

import Logic.GameProcess;
import Logic.Player;

public class Tavern extends Building implements Actions{
    public static final int wood = 3;
    public static final int rock = 5;
    private Player player;
    public Tavern(Player pl)
    {
        super(1, wood, rock, "Tavern");
        player = pl;
    }
    @Override
    public void action() {
        System.out.println("Welcome to the tavern!");
        System.out.println("Here you can improve your movement (M) or decrease fines (F)");
        String inp = GameProcess.scan.next();
        if(inp.equals("M")) {
            player.setMoveUp(player.getMoveUp() + 0.5f);
        }
        else if(inp.equals("F")){
            player.setFineUp(player.getFineUp() + 0.2f);
        }
    }
}
