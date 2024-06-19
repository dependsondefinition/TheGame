package Buildings;

import Logic.Player;

public class Lab extends Building implements Actions{
    public static final int wood = 3;
    public static final int rock = 1;
    private Player player;
    public Lab(Player pl)
    {
        super(1, wood, rock, "Lab");
        player = pl;
    }
    @Override
    public void action()
    {
        player.setHealthUp(player.getHealthUp() + 1);
    }
}
