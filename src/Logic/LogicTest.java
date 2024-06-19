package Logic;

import Chest.Chest;
import Units.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogicTest {
    private Player player;
    private Bot bot;
    @BeforeEach
    public void init() {
        player = new Player();
        bot = new Bot();
        player.addUnit(new Unit("n", "n", 10, 2, 1, 0, 3, 1));
        bot.addUnit(new Unit("n", "n", 10, 100, 1, 1, 1, 1));
    }
    @Test
    public void unitDeathTest() {
        bot.getUnits().getFirst().attack(player.getUnits().getFirst());
        Assertions.assertTrue(player.getUnits().getLast().getHp() <=  0);
    }
    @Test
    public void unitAttackTest() {
        player.getUnits().getFirst().attack(bot.getUnits().getFirst());
        Assertions.assertTrue(bot.getUnits().getFirst().getHp() < bot.getUnits().getFirst().getDefaultHP());
    }
    @Test
    public void unitDefenceTest() {
        player.getUnits().getFirst().attack(bot.getUnits().getFirst());
        Assertions.assertEquals(0, bot.getUnits().getFirst().getDefence());
        player.getUnits().getFirst().attack(bot.getUnits().getFirst());
        Assertions.assertEquals(7, bot.getUnits().getFirst().getHp());
    }
    @Test
    public void unitDistanceTest() {
        player.getUnits().getFirst().setCoord(0, 0);
        bot.getUnits().getFirst().setCoord(0, 1);
        bot.getUnits().getFirst().seeEnemy(player.getUnits().getFirst());
        Assertions.assertTrue(bot.getUnits().getFirst().isSeeEn());
        bot.getUnits().getFirst().setCoord(0, 2);
        bot.getUnits().getFirst().seeEnemy(player.getUnits().getFirst());
        Assertions.assertFalse(bot.getUnits().getFirst().isSeeEn());
    }
    @Test
    public void unitMoveTest() {
        Logic logic = new Logic(GameProcess.scan);
        FileManager file = new FileManager();
        Field fld = file.LoadMap("map");
        logic.setField(fld);
        player.getUnits().getFirst().setCoord(0, 0);
        ByteArrayOutputStream Out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(Out));
        logic.chDir(player.getUnits().getFirst());
        Assertions.assertEquals("2. Right\r\n3. Down\r\n7. Down + Right", Out.toString().trim());
        System.setOut(System.out);
    }
    @Test
    public void unitOpenChestTest() {
        Chest chest = new Chest();
        chest.openChest(player.getUnits().getFirst());
        Assertions.assertTrue(chest.isOpen());
    }
}
