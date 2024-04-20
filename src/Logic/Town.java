package Logic;

import Buildings.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Town implements Serializable {
    private final ArrayList<Building> buildings;
    private final List<Building> buildList;
    private boolean canBuilt = true;
    private int craftCntr;
    private Shop shop;
    private Player player;
    private int wood;
    private int rock;
    private boolean[] isBuilt;
    Town(Shop sp, Player pl) {
        wood = 4;
        rock = 2;
        craftCntr = 0;
        shop = sp;
        player = pl;
        isBuilt = new boolean[6];
        buildings = new ArrayList<>();
        buildList = List.of(new Lab(player), new Tavern(player), new Forge(player), new Arsenal(player), new Academy(shop, player), new Market(shop, Town.this), new Craft(shop));
    }
    Town(Town sTown, Shop sp, Player pl){
        wood = sTown.wood;
        rock = sTown.rock;
        craftCntr = sTown.craftCntr;
        isBuilt = sTown.isBuilt;
        buildings = sTown.getBuildings();
        shop = sp;
        player = pl;
        buildList = List.of(new Lab(player), new Tavern(player), new Forge(player), new Arsenal(player), new Academy(shop, player), new Market(shop, sTown), new Craft(shop));
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
            if(i < 6 && isBuilt[i] && buildList.get(i).getLevel() != 0) {
                System.out.println(i + 1 + ". " + buildList.get(i).toString() +  " can be improved");
            } else if(i == 6 && craftCntr < 4){
                System.out.println(i + 1 + ". " + buildList.get(i).toString());
            }
            else{
                System.out.println(i + 1 + ". " + buildList.get(i).toString());
            }
        }
    }
    public void start(Town tn)
    {
        System.out.println("Choose a building to build or improve");
        listOfBuilds();
        while(wood > 1 && rock > 1 && canBuilt) {
            System.out.println("You have " + wood + " woods and " + rock + " rocks");
            addBuilding(GameProcess.scan.nextInt(), tn);
        }
    }
    private void addBuilding(int input, Town tn) {
        Building bld = null;
        switch(input){
            case 0: {
                canBuilt = false;
                break;
            }
            case 1: {
                if(calcBuild(Lab.wood, Lab.rock)) {
                    if (!isBuilt[input - 1]) {
                        buildings.add(new Lab(player));
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
                        buildings.add(new Tavern(player));
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
                        buildings.add(new Forge(player));
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
                        buildings.add(new Arsenal(player));
                        buildings.getLast().action();
                        isBuilt[input - 1] = true;
                    } else {
                        bld = buildings.get(search(buildList.get(input - 1)));
                    }
                }
                break;
            }
            case 5: {
                if(calcBuild(Academy.wood, Academy.rock)) {
                    if (!isBuilt[input - 1]) {
                        buildings.add(new Academy(shop, player));
                        buildings.getLast().action();
                        isBuilt[input - 1] = true;
                    } else {
                        bld = buildings.get(search(buildList.get(input - 1)));
                    }
                }
                break;
            }
            case 6: {
                if(calcBuild(Market.wood, Market.rock)) {
                    if (!isBuilt[input - 1] && calcBuild(Market.wood, Market.rock)) {
                        buildings.add(new Market(shop, tn));
                        buildings.getLast().action();
                        isBuilt[input - 1] = true;
                    } else {
                        bld = buildings.get(search(buildList.get(input - 1)));
                    }
                }
                break;
            }
            case 7: {
                if(craftCntr < 4 && calcBuild(Craft.wood, Craft.rock)) {
                    buildings.add(new Craft(shop));
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
            if(build.getClass() == bldng.getClass())
            {
                return buildings.indexOf(build);
            }
        }
        return -1;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
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
}
