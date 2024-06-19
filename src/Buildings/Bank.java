package Buildings;

import Logic.*;
import Units.Unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank extends Building implements Actions{
    public static final int wood = 0;
    public static final int rock = 0;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private ArrayList<Credit> credits = new ArrayList<>();
    private final Logger log = Logger.getLogger("MyLogger");
    private List<Credit> offers = List.of(new Credit(10, 10, 5),
            new Credit(50, 5, 10), new Credit(1000, 2, 15));
    private Shop shop;
    private Town town;
    private Player player;
    public Bank(Shop sp, Player pl, Town tn) {
        super(0, wood, rock, "Bank");
        shop = sp;
        player = pl;
        town = tn;
    }
    @Override
    public void action() {
        System.out.println("Welcome to the BAUMAN'S GATE BANK");
        System.out.println("Here you can take out a loan for all your needs (even if you don't have money)");
        System.out.println("There are our offers (amount of money | percent | loan period): ");
        listOffers();
        int num = GameProcess.scan.nextInt();
        addCredit(num);
    }
    @Override
    public void improvement() {
        if(!credits.isEmpty()) {
            System.out.println("Do you want to repay our loans? (Y | N)");
            String ans = GameProcess.scan.next();
            if (ans.equals("Y")) {
                System.out.println("Which one?");
                showCredits();
                editCredit(GameProcess.scan.nextInt() - 1);
                System.out.println("Do you want to take new personal credit for your needs? (Y | N)");
                ans = GameProcess.scan.next();
                if (ans.equals("Y")) {
                    action();
                }
            } else if (ans.equals("N")) {
                action();
            }
        } else {
            action();
        }
    }
    private void listOffers(){
        int count = 1;
        for(Credit crdt : offers) {
            System.out.println(count + ". " + crdt);
            count++;
        }
    }
    private void showCredits() {
        for(int i = 0; i < credits.size(); i++) {
            System.out.println(i + 1 + ". " + credits.get(i));
        }
    }
    private void addCredit(int input) {
        switch(input)
        {
            case 1: {
                credits.add(new Credit(10, 10, 5));
                shop.setBudget((int) (shop.getBudget() + credits.getLast().getMoney()));
                log.log(Level.INFO, "You added credit " + credits.getLast());
                break;
            }
            case 2: {
                credits.add(new Credit(50, 5, 10));
                shop.setBudget((int) (shop.getBudget() + credits.getLast().getMoney()));
                log.log(Level.INFO, "You added credit " + credits.getLast());
                break;
            }
            case 3: {
                credits.add(new Credit(1000, 2, 15));
                shop.setBudget((int) (shop.getBudget() + credits.getLast().getMoney()));
                log.log(Level.INFO, "You added credit " + credits.getLast());
                break;
            }
            default: {
                System.out.println("If you continue to type wrong numbers we will arrange a mortgage for you");
                break;
            }
        }
    }
    private void editCredit(int input) {
        Credit cr = credits.get(input);
        System.out.println(shop.getBudget());
        int pay = GameProcess.scan.nextInt();
        if(pay > shop.getBudget()) {
            System.out.println("You don't have such money");
        }
        else {
            shop.setBudget(shop.getBudget() - pay);
            if(cr.getMoney() <= pay) {
                System.out.println(cr + " is paid!!! Maybe you would like to take another one???");
                credits.remove(cr);
            } else {
                cr.setMoney(cr.getMoney() - pay);
            }
        }
    }
    public void recount(Field fld) {
        for (int i = 0; i < credits.size(); i++) {
            Credit curCred = credits.get(i);
            curCred.setMoney(curCred.getMoney() * (1 + curCred.getPercent()));
            if(curCred.getPeriod() > 0) {
                curCred.setPeriod(curCred.getPeriod() - 1);
                if(curCred.getPeriod() == 2) {
                    System.out.println(ANSI_RED_BACKGROUND + "IF YOU DON'T PAY YOUR CREDIT IN 2 DAYS WE WILL CONFISCATE YOUR PROPERTY!!!"
                            + ANSI_RESET + "\n YOUR LOVELY BAUMAN'S GATE BANK");
                    log.log(Level.WARNING, "You have 2 days until credit period will end");
                    System.out.println(curCred.getMoney());
                }
            } else {
                if(shop.getBudget() > 0) {
                    if (curCred.getMoney() > shop.getBudget()) {
                        curCred.setMoney(curCred.getMoney() - shop.getBudget());
                        shop.setBudget(0);
                    } else {
                        shop.setBudget((int) (shop.getBudget() - curCred.getMoney()));
                        System.out.println(curCred + " is payed!!! DO YOU WANT TO TAKE OUT NEW CREDIT?");
                        log.log(Level.INFO, "You payed credit " + curCred);
                        credits.remove(curCred);
                    }
                } else {
                    if(town.getBank() != null && town.getBuildings().size() > 1) {
                        if(!town.getBank().equals(town.getBuildings().getLast())) {
                            Building bld = town.getBuildings().getLast();
                            if(bld instanceof Craft){
                                town.setCraft();
                            } else {
                                player.remUp(town.indexOfBuild(bld));
                                town.NotBuilt(town.indexOfBuild(bld));
                            }
                            System.out.println("We took your " + bld.getName() + "\n YOUR LOVELY BAUMAN'S GATE BANK");
                            log.log(Level.SEVERE, "You lost " + bld.getName());
                            town.getBuildings().remove(bld);
                        } else if(!town.getBank().equals(town.getBuildings().getFirst())) {
                            Building bld = town.getBuildings().getFirst();
                            if(bld instanceof Craft){
                                town.setCraft();
                            } else {
                                player.remUp(town.indexOfBuild(bld));
                                town.NotBuilt(town.indexOfBuild(bld));
                            }
                            System.out.println("We took your " + bld.getName() + "\n YOUR LOVELY BAUMAN'S GATE BANK");
                            log.log(Level.SEVERE, "You lost " + bld.getName());
                            town.getBuildings().remove(bld);
                        }
                        if(curCred.getMoney() > 30) {
                            curCred.setMoney(curCred.getMoney() - 30);
                        }
                        else {
                            System.out.println(curCred + " is payed!!! DO YOU WANT TO TAKE OUT NEW CREDIT?");
                            log.log(Level.INFO, "You payed credit " + curCred);
                            credits.remove(curCred);
                        }
                    } else if(!player.getUnits().isEmpty()) {
                        Unit del = player.getUnits().getLast();
                        System.out.println("We took your " + del.getName() + "\n YOUR LOVELY BAUMAN'S GATE BANK");
                        log.log(Level.SEVERE, "You lost " + del.getName());
                        if(curCred.getMoney() > del.getPrice()) {
                            curCred.setMoney(curCred.getMoney() - del.getPrice());
                        }
                        else {
                            System.out.println(curCred + " is payed!!! DO YOU WANT TO TAKE OUT NEW CREDIT?");
                            log.log(Level.INFO, "You payed credit " + curCred);
                            credits.remove(curCred);
                        }
                        player.getUnits().remove(player.getUnits().getLast());
                        fld.getMap().get(del.getX()).get(del.getY()).releaseUn();
                    }
                }
            }
        }
    }
    public void spam() {
        float spamProb = (float) Math.random();
        if(spamProb > 0.8) {
            if(shop.getBudget() > 100) {
                System.out.println(ANSI_RED_BACKGROUND + "SPECIAL OFFER FOR YOU!!!!!!!!!!"
                        + ANSI_RESET + " CREDIT OF 50000 AT 1000 PERCENT PER MOVE FOR A 10 MOVES");
                log.log(Level.WARNING, "Spam message for budget more than 100");
            }
            else if(shop.getBudget() > 50) {
                System.out.println(ANSI_BLUE_BACKGROUND + "IF YOU CALL TO US RIGHT NOW YOU WILL GET MORTGAGE FOR ALL YOUR LIFE!!!!"
                        + ANSI_RESET + "\n YOUR LOVELY BAUMAN'S GATE BANK");
                log.log(Level.WARNING, "Spam message for budget more than 50");
            }
            else if(shop.getBudget() > 10) {
                System.out.println(ANSI_GREEN_BACKGROUND + "WE ARE SURE THAT YOU NEED CREDIT FROM BAUMAN'S GATE BANK!!!"
                        + ANSI_RESET + " WE CAN OFFER YOU SOOOO MANY CREDITS!!! JUST CONTACT WITH US!!!");
                log.log(Level.WARNING, "Spam message for budget more than 10");
            }
        }
    }
    public ArrayList<Credit> getCredits() {
        return credits;
    }
}
class Credit implements Serializable {
    private float money;
    private final float percent;
    private int period;
    Credit(int money, int percent, int period) {
        this.money = money;
        this.percent = percent;
        this.period = period;
    }
    @Override
    public String toString()
    {
        return String.format("%6s | %4s%% | %2d", money, percent, period);
    }
    public float getMoney() {
        return money;
    }
    public float getPercent() {
        return percent / 100;
    }
    public int getPeriod() {
        return period;
    }
    public void setMoney(float money) {
        this.money = money;
    }
    public void setPeriod(int period) {
        this.period = period;
    }
}