package Logic;

import Buildings.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Town implements Serializable {
    private static final long serialVersionUID = 1L;
    private final ArrayList<Building> buildings;
    private final List<Building> buildList;
    private boolean canBuilt;
    private int craftCntr;
    private int wood;
    private int rock;
    private boolean[] isBuilt;
    Town(Logic lg) {
        wood = 40;
        rock = 20;
        craftCntr = 0;
        isBuilt = new boolean[7];
        buildings = new ArrayList<>();
        buildList = List.of(new Lab(lg.player), new Tavern(lg.player), new Forge(lg.player), new Arsenal(lg.player), new Academy(lg.shop, lg.player),
                new Market(lg.shop, Town.this), new Bank(lg.shop, lg.player, Town.this), new Craft(lg.shop));
    }
    @Override
    public String toString(){
        int count = 1;
        String listOfBuildings = "";
        for(Building build : buildings)
        {
            listOfBuildings += count + ". " + build.getName() + "\n";
            count++;
        }
        return  listOfBuildings;
    }
    public void listOfBuilds() {
        for(int i = 0; i < buildList.size(); i++)
        {
            if(i < 7 && isBuilt[i] && buildList.get(i).getLevel() != 0) {
                System.out.println(i + 1 + ". " + buildList.get(i).toString() +  " can be improved");
            } else if(i == 7 && craftCntr < 4){
                System.out.println(i + 1 + ". " + buildList.get(i).toString());
            }
            else{
                System.out.println(i + 1 + ". " + buildList.get(i).toString());
            }
        }
    }
    public void start(Town tn, Logic lg)
    {
        canBuilt = true;
        System.out.println("Choose a building to build or improve");
        listOfBuilds();
        while(wood > 1 && rock > 1 && canBuilt) {
            System.out.println("You have " + wood + " woods and " + rock + " rocks");
            addBuilding(GameProcess.scan.nextInt(), tn, lg);
        }
    }
    void addBuilding(int input, Town tn, Logic lg) {
        Building bld = null;
        switch(input){
            case 0: {
                canBuilt = false;
                break;
            }
            case 1: {
                if(calcBuild(Lab.wood, Lab.rock)) {
                    if (!isBuilt[input - 1]) {
                        buildings.add(new Lab(lg.player));
                        buildings.getLast().action();
                        isBuilt[input - 1] = true;
                    } else {
                        bld = buildings.get(search(buildList.get(input - 1)));
                    }
                }
                break;
            }
            case 2: {
                if(calcBuild(Tavern.wood, Tavern.rock)) {
                    if (!isBuilt[input - 1]) {
                        buildings.add(new Tavern(lg.player));
                        buildings.getLast().action();
                        isBuilt[input - 1] = true;
                    } else {
                        bld = buildings.get(search(buildList.get(input - 1)));
                    }
                }
                break;
            }
            case 3: {
                if(calcBuild(Forge.wood, Forge.rock)) {
                    if (!isBuilt[input - 1]) {
                        buildings.add(new Forge(lg.player));
                        buildings.getLast().action();
                        isBuilt[input - 1] = true;
                    } else {
                        bld = buildings.get(search(buildList.get(input - 1)));
                    }
                }
                break;
            }
            case 4: {
                if(calcBuild(Arsenal.wood, Arsenal.rock)) {
                    if (!isBuilt[input - 1]) {
                        buildings.add(new Arsenal(lg.player));
                        buildings.getLast().action();
                        isBuilt[input - 1] = true;
                    } else {
                        bld = buildings.get(search(buildList.get(input - 1)));
                    }
                }
                break;
            }
            case 5: {
                if (!isBuilt[input - 1] && calcBuild(Academy.wood, Academy.rock)) {
                    buildings.add(new Academy(lg.shop, lg.player));
                    buildings.getLast().action();
                    isBuilt[input - 1] = true;
                } else {
                    bld = buildings.get(search(buildList.get(input - 1)));
                }
                break;
            }
            case 6: {
                if (!isBuilt[input - 1] && calcBuild(Market.wood, Market.rock)) {
                    buildings.add(new Market(lg.shop, tn));
                    buildings.getLast().action();
                    isBuilt[input - 1] = true;
                } else {
                    bld = buildings.get(search(buildList.get(input - 1)));
                }
                break;
            }
            case 7: {
                if (!isBuilt[input - 1] && calcBuild(Bank.wood, Bank.rock)) {
                    buildings.add(new Bank(lg.shop, lg.player, tn));
                    buildings.getLast().action();
                    isBuilt[input - 1] = true;
                } else {
                    bld = buildings.get(search(buildList.get(input - 1)));
                }
                break;
            }
            case 8: {
                if(craftCntr < 4 && calcBuild(Craft.wood, Craft.rock)) {
                    buildings.add(new Craft(lg.shop));
                    buildings.getLast().action();
                    craftCntr++;
                }
                break;
            }
            default: {
                System.out.println("Wrong number");
                break;
            }
        }
        if(bld != null) {
            bld.improvement();
        }
    }
    private boolean calcBuild(int w, int r) {
        if(wood >= w && rock >= r) {
            wood -= w;
            rock -= r;
            return true;
        }
        return false;
    }
    private int search(Building bldng)
    {
        for(Building build : buildings){
            if(build.getClass().equals(bldng.getClass()))
            {
                return buildings.indexOf(build);
            }
        }
        return -1;
    }
    public int getWood() {
        return wood;
    }
    public int getRock() {
        return rock;
    }
    public void setWood(int wood) {
        this.wood = wood;
    }
    public void setRock(int rock) {
        this.rock = rock;
    }
    public void NotBuilt(int index) {
        isBuilt[index] = false;
    }
    public void setCraft()
    {
        craftCntr--;
    }
    public int indexOfBuild(Building bld) {
        return switch (bld.getName()) {
            case "Lab" -> 0;
            case "Tavern" -> 1;
            case "Forge" -> 2;
            case "Arsenal" -> 3;
            case "Academy" -> 4;
            case "Market" -> 5;
            case "Bank" -> 6;
            default -> -1;
        };
    }
    public Bank getBank() {
        if(search(buildList.get(6)) != -1) {
            return (Bank) buildings.get(search(buildList.get(6)));
        }
        else {
            return null;
        }
    }
    public ArrayList<Building> getBuildings() {
        return buildings;
    }
}
