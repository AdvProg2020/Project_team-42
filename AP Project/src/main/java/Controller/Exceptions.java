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
}
