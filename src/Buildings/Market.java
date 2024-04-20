package Buildings;

import Logic.GameProcess;
import Logic.Player;
import Logic.Shop;
import Logic.Town;

public class Market extends Building implements Actions {
    public static final int wood = 4;
    public static final int rock = 0;
    private boolean canOperate = true;
    private Shop shop;
    private Town town;
    private int abs;
    private int PriceWoodS, PriceRockS;
    private int PriceWoodB, PriceRockB;
    public Market(Shop sp, Town tn) {
        super(0, wood, rock, "Market");
        shop = sp;
        town = tn;
    }
    @Override
    public void action() {
       while(canOperate) {
           market();
           String scan = GameProcess.scan.next();
           if (scan.equals("S")) {
               System.out.println("Wood or Rock (W | R)");
               sell(GameProcess.scan.next());
           } else if (scan.equals("B")) {
               System.out.println("Wood or Rock (W | R)");
               buy(GameProcess.scan.next());
           } else if(scan.equals("P")) {
               canOperate = false;
               System.out.println("Market is closed!");
           }
       }
    }
    @Override
    public void improvement()
    {
        canOperate = true;
        action();
    }
    private void market()
    {
        recount();
        System.out.print("Current price of wood (to buy | to sell): ");
        System.out.println(PriceWoodB + " | " + PriceWoodS);
        System.out.print("Current price of rock (to buy | to sell): ");
        System.out.println(PriceRockB + " | " + PriceRockS);
        System.out.println("To buy type B, to sell - S, to escape - P");
        System.out.println("Your budget is " + shop.getBudget());
    }
    private void buy(String ans) {
        int amount;
        if(ans.equals("W")) {
            amount = GameProcess.scan.nextInt();
            if(shop.getBudget() < amount * PriceWoodB) {
                System.out.println("Too much!");
            }
            else{
                town.setWood(town.getWood() + amount);
                shop.setBudget(shop.getBudget() - amount * PriceWoodB);
            }
        }
        else if(ans.equals("R")) {
            amount = GameProcess.scan.nextInt();
            if(shop.getBudget() < amount * PriceRockB) {
                System.out.println("Too much!");
            }
            else{
                town.setRock(town.getRock() + amount);
                shop.setBudget(shop.getBudget() - amount * PriceRockB);
            }
        }
        if(shop.getBudget() < PriceWoodB && shop.getBudget() < PriceRockB)
        {
            canOperate = false;
        }
    }
    private void sell(String ans) {
        int amount;
        if(ans.equals("W")) {
            System.out.println("You have " + town.getWood() + " wood");
            amount = GameProcess.scan.nextInt();
            if(amount > town.getWood()) {
                System.out.println("Too much!");
            }
            else{
                town.setWood(town.getWood() - amount);
                shop.setBudget(shop.getBudget() + amount * PriceWoodS);
            }
        }
        else if(ans.equals("R")) {
            System.out.println("You have " + town.getRock() + " rock");
            amount = GameProcess.scan.nextInt();
            if(amount > town.getRock()) {
                System.out.println("Too much!");
            }
            else{
                town.setRock(town.getRock() - amount);
                shop.setBudget(shop.getBudget() + amount * PriceRockS);
            }
        }
        if(town.getWood() == 0 && town.getRock() == 0)
        {
            canOperate = false;
        }
    }
    private void recount()
    {
        abs = town.getWood() + town.getRock();
        PriceWoodS = (abs / (town.getRock() == 0 ? 1 : town.getRock())) * 5 + 1;
        PriceRockS = (abs / (town.getWood() == 0 ? 1 : town.getWood())) * 5 + 1;
        PriceWoodB = (int) Math.round(PriceWoodS * 1.25);
        PriceRockB = (int) Math.round(PriceRockS * 1.25);
    }
}
