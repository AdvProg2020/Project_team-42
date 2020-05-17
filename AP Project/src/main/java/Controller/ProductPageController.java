package Controller;

import Controller.AccountPagesController.AccountPageController;
import Model.Accounts.Account;
import Model.Accounts.SellerAccount;
import Model.Product;
import Model.Requests.CommentRequest;
import Model.Requests.Request;
import Model.Shop;

import java.util.HashMap;

public class ProductPageController {
    private static ProductPageController productPageController = new ProductPageController();
    private Shop shop;
    private HashMap<Product, HashMap<SellerAccount, Integer>> cart;
    private Product selectedProduct;

    private ProductPageController () {
        this.shop = Shop.getInstance();
        this.cart = new HashMap<>();
    }

    public void setSelectedProduct(Product SelectedProduct) {
        this.selectedProduct = SelectedProduct;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public static ProductPageController getInstance() {
        return productPageController;
    }

    public Product getProductById (long productId) throws Exceptions.NoProductByThisIdException {
        return shop.getProductById(productId);
    }

    public void addComment (Account user, String title, String content) {
        new CommentRequest(user, selectedProduct, title, content, user.hasBoughtProduct(selectedProduct));
    }

    public void addToCart (String sellerUserName) throws Exceptions.NotCustomerException, Exceptions.NoSellerByThisUserNameForProductException {
        SellerAccount seller = selectedProduct.getSellerByUsername(sellerUserName);
        try {
            AccountPageController.getUser().addProductToCart(selectedProduct, seller);
        } catch (Exceptions.NotLogedInException e) {
            this.cart.put(selectedProduct, new HashMap<SellerAccount, Integer>());
            this.cart.get(selectedProduct).put(seller, 1);
        }
    }
}
