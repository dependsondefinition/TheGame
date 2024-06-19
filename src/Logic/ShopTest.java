package Logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopTest {
    private Shop shop;
    @BeforeEach
    public void init() {
        shop = new Shop();
    }
    @Test
    public void buyUnitTest() {
        Player player = new Player();
        shop.buy(1, player);
        Assertions.assertEquals(65, shop.getBudget());
    }
}
