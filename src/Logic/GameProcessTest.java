package Logic;

import Units.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GameProcessTest {
    private Player player = new Player();
    private Bot bot = new Bot();
    @Test //победа игрока
    public void Win() {
        player.addUnit(new Unit("n", "n", 1, 1, 1, 1, 1, 1));
        Assertions.assertFalse(player.getUnits().isEmpty());
        Assertions.assertTrue(bot.getUnits().isEmpty());
    }
    @Test //победа бота
    public void Lose() {
        bot.addUnit(new Unit("n", "n", 1, 1, 1, 1, 1, 1));
        Assertions.assertFalse(bot.getUnits().isEmpty());
        Assertions.assertTrue(player.getUnits().isEmpty());
    }
}
