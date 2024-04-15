package Logic;

import Buildings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Town {
    private ArrayList<Building> buildings = new ArrayList<>();
    private final List<Building> buildList = List.of(new Lab(), new Tavern(), new Forge(), new Arsenal(), new Academy(), new Market(), new Craft());
    public static int wood;
    public static int rock;
    Town(Scanner scan)
    {
        wood = 4;
        rock = 2;
        System.out.println("Town is empty");
        System.out.println("Choose a building to build");
        listOfBuilds();
        while(wood > 1 && rock > 1) {
            System.out.println("You have " + wood + " woods and " + rock + " rocks");
            addBuilding(scan.nextInt());
        }
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
    public void listOfBuilds()
    {
        for(int i = 0; i < buildList.size(); i++)
        {
            System.out.println(i + 1 + ". " + buildList.get(i).toString());
        }
    }
    public void addBuilding(int input)
    {
        switch(input){
            case 1:{
                if(calcBuild(Lab.class, Lab.wood, Lab.rock)) {
                    buildings.add(new Lab());
                }
                break;
            }
            case 2:{
                if(calcBuild(Tavern.class, Tavern.wood, Tavern.rock)) {
                    buildings.add(new Tavern());
                }
                break;
            }
            case 3:{
                if(calcBuild(Forge.class, Forge.wood, Forge.rock)) {
                    buildings.add(new Forge());
                }
                break;
            }
            case 4:{
                if(calcBuild(Arsenal.class, Arsenal.wood, Arsenal.rock)) {
                    buildings.add(new Arsenal());
                }
                break;
            }
            case 5:{
                if(calcBuild(Academy.class, Academy.wood, Academy.rock)) {
                    buildings.add(new Academy());
                }
                break;
            }
            case 6:{
                if(calcBuild(Market.class, Market.wood, Market.rock)) {
                    buildings.add(new Market());
                }
                break;
            }
            case 7:{
                if(Craft.counter < 5 && wood >= Craft.wood && rock >= Craft.rock) {
                    buildings.add(new Craft());
                    wood -= Craft.wood;
                    rock -= Craft.rock;
                }
                break;
            }
            default:{
                System.out.println("Wrong number");
                break;
            }
        }
    }
    private boolean calcBuild(Class bld, int w, int r)
    {
        if((!buildings.contains(bld) && (wood >= w && rock >= r))) {
            wood -= w;
            rock -= r;
            return true;
        }
        return false;
    }
}
