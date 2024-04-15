package Buildings;

public class Craft extends Building{
    public static final int wood = 2;
    public static final int rock = 2;
    public static int counter = 0;
    public Craft()
    {
        super(0, wood, rock, "Craft");
        counter++;
    }
}
