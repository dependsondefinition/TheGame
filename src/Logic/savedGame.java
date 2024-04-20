package Logic;

import java.io.Serializable;

public class savedGame implements Serializable{
    private static final long serialVersionUID = 1L;
    private Player player;
    private Shop shop;
    private Town town;
    public savedGame(Player pl, Shop sp, Town tn)
    {
        player = pl;
        shop = sp;
        town = tn;
    }

    public Player getPlayer() {
        return player;
    }
    public Shop getShop() {
        return shop;
    }
    public Town getTown() {
        return town;
    }
}
