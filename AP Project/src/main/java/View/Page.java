package View;

import Model.Accounts.Account;

import java.util.Scanner;

public abstract class Page {
    protected static Scanner scanner = new Scanner(System.in);
    protected Account user;


    public abstract AllPages run();
}
