package View;

import java.util.Scanner;

public abstract class Page {
     protected static Scanner scanner = new Scanner(System.in);

    public abstract AllPages run();
}
