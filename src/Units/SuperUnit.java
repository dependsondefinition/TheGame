package Units;

import java.util.Scanner;

public class SuperUnit extends Unit {
    public SuperUnit(Scanner scanner)
    {
        super("SuperUnit", "\u001B[32m" + "S" + "\u001B[0m", scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextFloat(), 0);
    }
}
