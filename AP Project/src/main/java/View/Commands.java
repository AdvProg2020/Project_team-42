package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    EXIT("(?i)exit"),
    BACK("(?i)back"),
    VIEW_PERSONAL_INFO("(?i)view\\s+personal\\s+info"),
    EDIT_PERSONAL_INFO("(?i)edit\\s+(.+)"),
    NAMES("\\w+"),
    EMAIL(".+@.+\\..+"),
    PHONE_NUMBER("(\\+989|09)\\d\\d\\d\\d\\d\\d\\d\\d\\d"),
    PASSWORD("\\S\\S\\S\\S\\S\\S\\S\\S+"),
    VIEW_CART("(?i)view\\s+cart"),
    VIEW_ORDERS("(?i)view\\s+orders"),
    VIEW_BALANCE("(?i)view\\s+balance"),
    VIEW_DISCOUNT_CODES("(?i)view\\s+discount\\s+codes"),
    OFF("(i?)off"),
    HELP("(i?)\\s*help\\s*"),
    //product page
    DIGEST("(?i)digest"),
    ATTRIBUTES("(?i)attributes"),
    COMPARE("(?i)compare\\s+(\\d+)"),
    COMMENTS("(?i)comments"),
    ADD_TO_CART("(?i)add\\s+to\\s+cart"),
    ADD_COMMENT("(i?)add\\s+comment"),
    //SellerPage
    VEIW_COMPANY_INFORMATION("(i?)view\\s+compony\\s+information"),
    VIEW_SALES_HISTORY("(i?)view\\s+sales\\s+history"),
    MANAGE_PRODUCTS("(i?)manage\\s+products"),
    ADD_PRODUCT("(i?)add\\s+product"),
    REMOVE_PRODUCT("(i?)remove\\s+product\\s+(\\d+)"),
    SHOW_CATEGORIES("(i?)show\\s+categories"),
    VIEW_OFFS("(i?)view\\s+offs"),
    VIEW_BALANCEE("(i?)view\\s+balance");
    private Pattern pattern;

    Commands(String regexPattern){
        this.pattern = Pattern.compile(regexPattern);
    }

    public Matcher getMatcher (String input) {
        return pattern.matcher(input);
    }
}
