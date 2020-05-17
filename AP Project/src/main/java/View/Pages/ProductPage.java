package View.Pages;

import Controller.AccountPagesController.AccountPageController;
import Controller.Exceptions;
import Controller.ProductPageController;
import Model.Accounts.Account;
import Model.Accounts.SellerAccount;
import Model.Comment;
import Model.Product;
import View.AllPages;
import View.Commands;
import View.Page;

import java.util.regex.Matcher;

public class ProductPage extends Page {
    private static ProductPage productPage = new ProductPage();
    private ProductPageController controller;


    private ProductPage () {
        this.controller = ProductPageController.getInstance();
    }

    public static ProductPage getInstance() {
        return productPage;
    }

    public AllPages run() {
        String input;
        Matcher matcher;

        while (!Commands.EXIT.getMatcher(input = scanner.nextLine()).matches()) {
            System.out.println("product id   : " + controller.getSelectedProduct().getProductId() +
                             "\nproduct name : " + controller.getSelectedProduct().getName());

            if (Commands.DIGEST.getMatcher(input).matches())
                digest();
            else if (Commands.ATTRIBUTES.getMatcher(input).matches())
                showAttributes();
            else if ((matcher = Commands.COMPARE.getMatcher(input)).matches())
                compare(Long.parseLong(matcher.group(1)));
            else if (Commands.COMMENTS.getMatcher(input).matches()) {
                try {
                    showComments();
                } catch (Exceptions.NotLogedInException e) {
                    return AllPages.LOGIN_REGISTER_PAGE;
                }
            }
            else
                System.out.println("Invalid command format.");
        }

        return null;
    }

    private void digest () {
        System.out.println("Category     : " + controller.getSelectedProduct().getCategory().getName() +
                         "\nDescription  : " + controller.getSelectedProduct().getDescription() +
                         "\nAverage rate : " + controller.getSelectedProduct().getAverageRate() +
                         "\nPrice        : " + controller.getSelectedProduct().getPrice() +
                         "\nSellers      :");

        for (SellerAccount seller : controller.getSelectedProduct().getSellers()) {
            try {
                System.out.println("\t" + seller.getUserName() + " by " + controller.getSelectedProduct().getOff(seller) + "% off");
            } catch (Exceptions.NoOffForThisProductException e) {
                System.out.println("\t" + seller.getUserName());
            }
        }

        String input;

        while (!Commands.BACK.getMatcher(input = scanner.nextLine()).matches()) {
            if (Commands.ADD_TO_CART.getMatcher(input).matches())
                addToCart();
        }
    }

    private void addToCart () {
        System.out.print("Please select a seller :");
        String sellerUserName = scanner.nextLine();
        try {
            controller.addToCart(sellerUserName);
        } catch (Exceptions.NotCustomerException | Exceptions.NoSellerByThisUserNameForProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAttributes () {
        System.out.println(controller.getSelectedProduct().toString());
    }

    private void compare (long productId) {
        try {
            Product productToCompare = controller.getProductById(productId);
            System.out.printf("Product id   : %-72d | %d\n" +
                              "Name         : %-72s | %s\n" +
                              "Category     : %-72s | %s\n" +
                              "Brand        : %-72s | %s\n" +
                              "Description  : %-72s | %s\n" +
                              "Attribute    : %-72s | %s\n" +
                              "Average rate : %-72f | %f\n",
                              controller.getSelectedProduct().getProductId(), productToCompare.getProductId(), controller.getSelectedProduct().getName(), productToCompare.getName(),
                              controller.getSelectedProduct().getCategory().getName(), productToCompare.getCategory().getName(), controller.getSelectedProduct().getBrand(),
                              productToCompare.getBrand(), controller.getSelectedProduct().getDescription(), productToCompare.getDescription(),
                              controller.getSelectedProduct().getAttribute(), productToCompare.getAttribute(),
                              controller.getSelectedProduct().getAverageRate(), productToCompare.getAverageRate());
            System.out.print("Seller(s)    : ");
            for (SellerAccount seller : controller.getSelectedProduct().getSellers()) {
                System.out.printf("%-10s, ", seller.getUserName());
            }
            for (int i = 0; i < 6 - controller.getSelectedProduct().getSellers().size(); i++) {
                System.out.print("            ");
            }
            System.out.print(" | ");
            for (SellerAccount seller : productToCompare.getSellers()) {
                System.out.printf("%-10s, ", seller.getUserName());
            }
            System.out.println();
            System.out.print("Off(s)       : ");
            for (SellerAccount seller : controller.getSelectedProduct().getSellers()) {
                try {
                    System.out.printf("%-10f, ", controller.getSelectedProduct().getOff(seller));
                } catch (Exceptions.NoOffForThisProductException e) {
                    System.out.println("no off    , ");
                }
            }
            for (int i = 0; i < 6 - controller.getSelectedProduct().getSellers().size(); i++) {
                System.out.print("          ");
            }
            System.out.print(" | ");
            for (SellerAccount seller : productToCompare.getSellers()) {
                try {
                    System.out.printf("%-10f, ", productToCompare.getOff(seller));
                } catch (Exceptions.NoOffForThisProductException e) {
                    System.out.println("no off    , ");
                }
            }
            System.out.println();
        } catch (Exceptions.NoProductByThisIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showComments () throws Exceptions.NotLogedInException {
        try {
            for (Comment comment : controller.getSelectedProduct().getComments()) {
                System.out.println("Title : " + comment.getTitle() + "\n\tContent : " + comment.getContent());
            }
        } catch (Exceptions.NoCommentsException e) {
            System.out.println(e.getMessage());
        }

        String input;

        while (!Commands.EXIT.getMatcher(input = scanner.nextLine()).matches()) {
            if (Commands.BACK.getMatcher(input).matches())
                return;
            else if (Commands.ADD_COMMENT.getMatcher(input).matches())
                addComment();
        }
    }

    private void addComment () throws Exceptions.NotLogedInException {
        try {
            Account user = AccountPageController.getUser();
            System.out.println("Title :");
            String title = scanner.nextLine();
            System.out.println("Content :");
            String content = scanner.nextLine();
            controller.addComment(user, title, content);
        } catch (Exceptions.NotLogedInException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
