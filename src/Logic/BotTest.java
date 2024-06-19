package Logic;

import Units.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BotTest {
    private Bot bot;
    @BeforeEach
    public void init() {
        bot = new Bot();
    }
    @Test
    public void botActionsTest() {
        bot.addUnit(new Unit("n", "n", 10, 5, 1, 0, 2, 1));
        ByteArrayOutputStream Out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(Out));
        bot.showUnits();
        Assertions.assertEquals("Your enemies:\r\nName:          n, Sign: n, HP: 10, Damage: 5, Distance: 1, Defence: 0, Movement: 2,0, Price: 1", Out.toString().trim());
        System.setOut(System.out);
    }
}
