package Controller;

import Model.Accounts.Account;
import Model.Accounts.SellerAccount;
import Model.Product;
import Model.Requests.CommentRequest;
import Model.Shop;

public class ProductPageController {
    private static ProductPageController productPageController = new ProductPageController();
    private Shop shop;
    private Product selectedProduct;

    private ProductPageController() {
        this.shop = Shop.getInstance();
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

    public void addToCart (String sellerUserName) throws Exceptions.NoSellerByThisUserNameForProductException, Exceptions.NotCustomerException {
        SellerAccount seller = selectedProduct.getSellerByUsername(sellerUserName);
        CartPageController.getInstance().addProductToCart(seller, selectedProduct);
    }
}
