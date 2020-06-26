package View;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Page {
    protected static Scanner scanner = new Scanner(System.in);
    protected static ArrayList<Page> pagesHistory = new ArrayList<>();

    public abstract Page run();

    protected void printInvalidCommandMessage () {
        System.out.println("Invalid command format.");
    }
}
