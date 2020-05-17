package Controller;

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
}
