package Buildings;

import Logic.Player;

public class Arsenal extends Building implements Actions {
    public static final int wood = 3;
    public static final int rock = 1;
    private Player player;
    public Arsenal(Player pl) {
        super(1, wood, rock, "Arsenal");
        player = pl;
    }
    @Override
    public void action() {
        player.setArmorUp(player.getArmorUp() + 1);
    }
}
