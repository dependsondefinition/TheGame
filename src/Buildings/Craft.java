package Buildings;

import Logic.Shop;

public class Craft extends Building implements Actions{
    public static final int wood = 2;
    public static final int rock = 2;
    private final Shop shop;
    public Craft(Shop sp)
    {
        super(0, wood, rock, "Craft");
        shop = sp;
    }
    @Override
    public void action()
    {
        shop.setBudget(shop.getBudget() + 10);
    }
}