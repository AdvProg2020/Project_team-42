package View;

import Model.Accounts.Account;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Page {
    protected static Scanner scanner = new Scanner(System.in);
    protected static ArrayList<AllPages> pagesHistory = new ArrayList<>();

    public abstract AllPages run();

    protected void printInvalidCommandMessage () {
        System.out.println("Invalid command format.");
    }
}
