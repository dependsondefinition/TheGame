package Buildings;

import Logic.GameProcess;
import Logic.Player;
import Logic.Shop;
import Units.SuperUnit;
import Units.Unit;

import java.util.ArrayList;

public class Academy extends Building implements Actions {
    public static final int wood = 3;
    public static final int rock = 6;
    ArrayList<Unit> expUnit;
    private Shop shop;
    private Player player;
    public Academy(Shop sp, Player pl)
    {
        super(0, wood, rock, "Academy");
        expUnit = new ArrayList<>();
        shop = sp;
        player = pl;
    }
    @Override
    public void action() {
        System.out.println("Enter name, sign, hp, damage, distance, defence, move");
        expUnit.add(new SuperUnit(GameProcess.scan));
        System.out.println(expUnit.getLast().getPrice());
    }
    @Override
    public void improvement() {
        for(int i = 0; i < expUnit.size(); i++) {
            System.out.println(i + 1 + ". " + expUnit.get(i));
        }
        int index = GameProcess.scan.nextInt() - 1;
        System.out.println("Hire (H) | Delete (D)");
        String ans = GameProcess.scan.next();
        if(ans.equals("H")) {
            if(shop.getBudget() > expUnit.get(index).getPrice()) {
                shop.setBudget(shop.getBudget() - expUnit.get(index).getPrice());
                player.addUnit(expUnit.get(index));
            } else {
                System.out.println("You cant afford this unit!");
            }
        } else if(ans.equals("D")) {
            expUnit.remove(expUnit.get(index));
        }
    }
}
