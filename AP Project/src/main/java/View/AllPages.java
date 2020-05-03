package View;

import View.Pages.*;
import View.Pages.AccountsPage.*;

public enum AllPages {
    LOGIN_REGISTER_PAGE(LoginRegisterPage.getInstance()),
    CUSTOMER_PAGE(CustomerPage.getInstance()),
    MANAGER_PAGE(ManagerPage.getInstance()),
    SELLER_PAGE(SellerPage.getInstance()),
    ALL_PRODUCTS_PAGE(AllProductsPage.getInstance()),
    CART_PAGE(CartPage.getInstance()),
    OFFS_PAGE(OffsPage.getInstance()),
    PRODUCT_PAGE(ProductPage.getInstance());

    public Page page;

    AllPages(Page page) {
        this.page = page;
    }
}
