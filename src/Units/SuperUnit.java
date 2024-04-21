package Units;

import java.io.Serializable;
import java.util.Scanner;

public class SuperUnit extends Unit implements Serializable {
    public SuperUnit(Scanner scanner)
    {
        super(scanner.next(), "\u001B[32m" + scanner.next() + "\u001B[0m", scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextFloat(), 0);
        this.price = (int) (((float)(this.hp + (this.damage)) / (float)this.distance * this.defence / this.movement));
    }
}
