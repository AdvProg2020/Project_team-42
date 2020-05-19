package Controller;

import Model.Accounts.SellerAccount;
import Model.Product;

public class Exceptions {
    public static class InvalidFieldException extends Exception {
        public InvalidFieldException() {
            super("The entered field is invalid.");
        }
    }

    public static class InvalidFormatException extends Exception {
        public InvalidFormatException() {
            super("The entered format is invalid.");
        }
    }

    public static class NoOrderException extends Exception {
        public NoOrderException() {
            super("You have no order.");
        }
    }

    public static class NoDiscountCodeException extends Exception {
        public NoDiscountCodeException() {
            super("You have no discount code.");
        }
    }

    public static class NoOffForThisProductException extends Exception {}

    public static class NoProductByThisIdException extends Exception {
        public NoProductByThisIdException (long productId) {
            super("There is no product with id " + productId + ".");
        }
    }

    public static class NoCommentsException extends  Exception {
        public NoCommentsException () {
            super("There is no comments for this product.");
        }
    }

    public static class NotLogedInException extends Exception {
        public NotLogedInException () {
            super("In order to do this action you must log in.");
        }
    }

    public static class NotCustomerException extends Exception {
        public NotCustomerException() {
            super("In order to do this action you must log in by a customer account.");
        }
    }

    public static class NoSellerByThisUserNameForProductException extends Exception {
        public NoSellerByThisUserNameForProductException () {
            super("There is no seller by this username for this product.");
        }
    }

    public static class NoProductWithThisIdInCartException extends Exception {
        public NoProductWithThisIdInCartException() {
            super("There is no product with this id in cart.");
        }
    }

    public static class NoSellerWithThisUserNameForThisProductInCartException extends Exception {
        public NoSellerWithThisUserNameForThisProductInCartException() {
            super("There is no seller with this username for this product in cart.");
        }
    }

    public static class EmptyCartException extends Exception {
        public EmptyCartException() {
            super("Your cart is empty.");
        }
    }

    public static class NotEnoughProductToPurchaseException extends Exception {
        public NotEnoughProductToPurchaseException(Product product, SellerAccount seller, int existingCount) {
            super(seller.getUserName() + " has only " + existingCount + " units of " + product.getName());
        }
    }

    public static class StopPurchaseException extends Exception {}

    public static class NoDiscountByCodeException extends Exception {
        public NoDiscountByCodeException() {
            super("This discount code is not valid for you.");
        }
    }

    public static class NotUsableDiscountCodeException extends Exception {
        public NotUsableDiscountCodeException () {
            super("This discount code is not usable.");
        }
    }

    public static class NotEnoughCreditException extends Exception {
        public NotEnoughCreditException() {
            super("You don`t have enough credit to buy the cart.");
        }
    }

    public static class NoBuyLogByIdException extends Exception {
        public NoBuyLogByIdException() {
            super("You don`t have a buy log with this id.");
        }
    }

    public static class RateOutOfRangeException extends Exception {
        public RateOutOfRangeException() {
            super("Rate must be from 1 to 5.");
        }
    }

    public static class NotBoughtProductByIdException extends Exception {
        public NotBoughtProductByIdException() {
            super("You have not bought a product with this id.");
        }
    }
}
