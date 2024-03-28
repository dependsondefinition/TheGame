package Units;

import java.util.Scanner;

public class SuperUnit extends Unit {
    public SuperUnit()
    {
        super();
    }
    public SuperUnit(Scanner scanner)
    {
        super();
        this.name = "SuperUnit";
        this.sign = "\u001B[32m" + "S" + "\u001B[0m";
        System.out.println("Set HP");
        this.hp = scanner.nextInt();
        System.out.println("Set damage");
        this.damage = scanner.nextInt();
        System.out.println("Set distance");
        this.distance = scanner.nextInt();
        System.out.println("Set defence");
        this.defence = scanner.nextInt();
        System.out.println("Set movement");
        this.movement = scanner.nextFloat();
        this.defaultMovement = this.movement;
        this.price = 0;
    }
}
