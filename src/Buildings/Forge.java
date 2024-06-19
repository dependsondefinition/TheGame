package Buildings;

import Logic.Player;

public class Forge extends Building implements Actions{
    public static final int wood = 3;
    public static final int rock = 1;
    private Player player;
    public Forge(Player pl)
    {
        super(1, wood, rock, "Forge");
        player = pl;
    }

    @Override
    public void action()
    {
        player.setAttackUp(player.getAttackUp() + 1);
    }
}
