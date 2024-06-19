package Logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FieldTest {
    private Field fld;
    @BeforeEach
    public void init() {
        FileManager file = new FileManager();
        fld = file.LoadMap("map");
    }
    @Test //штрафы за перемещение
    public void fineTest() {
        float[] exp =  {1.0F, 1.0F, 1.0F};
        Assertions.assertEquals(Arrays.toString(exp), Arrays.toString(fld.getFines().get(fld.getTer().getFirst())));
    }
    @Test
    public void fieldDrawTest() {
        Assertions.assertEquals("**********\n" + "**@*@**@**\n" + "*!****!*!*\n" + "**@*@**@**\n" + "**********\n", fld.toString());
    }
}
