package Buildings;

public class Arsenal extends Building implements Actions {
    public static final int wood = 3;
    public static final int rock = 1;
    public Arsenal()
    {
        super(1, wood, rock, "Arsenal");
    }
    @Override
    public void action() {

    }
}
