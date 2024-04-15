package Buildings;

public class Academy extends Building implements Actions {
    public static final int wood = 3;
    public static final int rock = 6;
    public Academy()
    {
        super(0, wood, rock, "Academy");
    }
    @Override
    public void action() {

    }
}
