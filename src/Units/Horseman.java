package Units;

public class Horseman extends Unit{
    public static final float fineTree = 1.5f;
    public static final float fineSwamp = 2.2f;
    public static final float fineHill = 1.2f;
    public static final float fineMountain = 3f;
    public Horseman(String nam, String n, int health, int dam, int dist, int def, float move, int pr)
    {
        super(nam, n, health, dam, dist, def, move, pr);
        setFines(fineTree, fineSwamp, fineHill, fineMountain);
    }
}
