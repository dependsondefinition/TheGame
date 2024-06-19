package Logic;

import Buildings.Academy;
import Buildings.Arsenal;
import Buildings.Market;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TownTest {
    private Town town;
    private Logic logic;
    private FileManager manager;
    @BeforeEach
    public void init() {
        logic = new Logic(GameProcess.scan);
        manager = new FileManager();
    }
    @Test
    public void townBuildingsUpgrade() {
        town = new Town(logic);
        town.getBuildings().add(new Arsenal(logic.player));
        town.getBuildings().getFirst().improvement();
        Assertions.assertEquals(2, town.getBuildings().getFirst().getLevel());
    }
    @Test
    public void townSpecialBuildingsWork() {
        Town town = new Town(logic);
        town.addBuilding(8, town, logic);
        Assertions.assertEquals(85, logic.shop.getBudget());
        town.getBuildings().add(new Market(logic.shop, town));
        Assertions.assertEquals("Market", town.getBuildings().getLast().getName());
        town.getBuildings().add(new Academy(logic.shop, logic.player));
        Assertions.assertEquals("Academy", town.getBuildings().getLast().getName());
    }
    @Test
    public void townBuildingProgressTest() {
        Town town = manager.LoadGame("gam2").getTown();
        Assertions.assertEquals(2, town.getBuildings().getFirst().getLevel());
        town.getBuildings().getFirst().improvement();
        Assertions.assertEquals(3, town.getBuildings().getFirst().getLevel());
    }
}
