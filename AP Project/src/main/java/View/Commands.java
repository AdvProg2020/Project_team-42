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
    //customer page
    VIEW_CART("(?i)view\\s+cart"),
    VIEW_ORDERS("(?i)view\\s+orders"),
    SHOW_ORDER("(?i)show\\s+order\\s+(\\d+)"),
    RATE_PRODUCT("(?i)rate\\s+(\\d+)\\s+(\\d+)"),
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
    VEIW_COMPANY_INFORMATION("(?i)view\\s+compony\\s+information"),
    VIEW_SALES_HISTORY("(?i)view\\s+sales\\s+history"),
    MANAGE_PRODUCTS("(?i)manage\\s+products"),
    ADD_PRODUCT("(?i)add\\s+product"),
    REMOVE_PRODUCT("(?i)remove\\s+product\\s+(\\d+)"),
    SHOW_CATEGORIES("(?i)show\\s+categories"),
    VIEW_OFFS("(?i)view\\s+offs"),
    VIEW_BALANCEE("(?i)view\\s+balance"),
    VIEW_CATEGORIES("(?i)view\\s+categories"),
    //cart page
    SHOW_PRODUCTS("(?i)show\\s+products"),
    VIEW_PRODUCT("(?i)view\\s+(\\d+)"),
    INCREASE_PRODUCT("(?i)increase\\s+(\\d+)"),
    DECREASE_PRODUCT("(?i)decrease\\s+(\\d+)"),
    SHOW_TOTAL_PRICE("(?i)show\\s+total\\s+price"),
    PURCHASE("(?i)purchase"), 
    //allProduct Page
    SHOW_PRODUCT_BY_ID("(?i)show\\s+show\\s+product\\s+(\\d)"),
    SHOW_AVALABLE_SORT("(?i)show\\s+available\\s+sort"),
    SET_SORT("(?i)sort\\s+(\\w+)"),
    FILTERING("(?i)\\s*filtering"),
    SORTING("(?i)\\s*sorting"),
    //filtering prosses
    SHOW_AVALABLE_FILTERS("(?i)\\s*show\\s*avalilable\\s*filters"),
    FILTER_ANAVALAIBLE_FILTER("(?i)filer\\s+(\\w+)"),
    CURRENST_FILTERS("(?i)current\\s+filters"),
    DISABLE_FILTERS("(?i)disable\\s+filter+(\\w+)"),
    //sorting prosses
    SORT_ANAVALAIBLE_SORT("(?i)sort\\s+(\\w+)"),
    CURRENST_SORT("(?i)current\\s+sort"),
    DISABLE_SORT("(?i)disable\\s+sort"),
    //add off
    VIEW_OFF("(?i)view\\s+(\\d+)"),
    EDIT_OFF("(?i)edit\\s+(\\d+)"),
    ADD_OFF("(?i)add\\s+off"),
    FILTERING_PRICE("(?i)price\\s+\\s*(\\d+)\\s+\\s*(\\d+)"),
    FILTERING_RATE("(?i)rate\\s+(\\d+)"),
     //login regester page
    CREATE_ACCOUNT("(?i)create\\s+account"),
    LOGIN("(?i)login"),
    //manager page
    MANAGE_USERS("(?i)manage\\s+users"),
    VIEW("(?i)view\\s+(\\S+)"),
    DELETE_USER("(?I)delete\\s+user\\s+(\\S+)"),
    CREATE_MANAGER_PROFILE("(?i)create\\s+manager\\s+profile"),
    MANAGE_ALL_PRODUCTS("(?i)manage\\s+all\\s+products"),
    CREATE_DISCOUNT_CODE("(i?)create\\s+discount\\s+code"),
    VIEW_DISCOUNT_CODE("(i?)view\\s+discount\\s+code\\s+(\\d+)"),
    EDIT_DISCOUNT_CODE("(i?)edit\\s+discount\\s+code\\s+(\\d+)"),
    REMOVE_DISCOUNT_CODE("(i?)remove\\s+discount\\s+code\\s+(\\d+)"),
    MANAGE_REQUEST("(?i)manage\\s+requests"),
    DETAILS("(?i)details\\s+(\\d+)"),
    ACCEPT("(?i)accept\\s+(\\d+)"),
    DECLINE("(?i)decline\\s+(\\d+)"),
    MANAGE_CATEGORIES("(?i)manage\\s+categories"),
    EDIT_CATEGORY("(?i)edit\\s+category\\s+(\\S+)"),
    ADD_CATEGORY("(?i)add\\s+(\\S+)"),
    REMOVE_CATEGORY("(?i)remove\\s+(\\S+)"),
    EDIT_FIELD("(?i)field\\s+(\\S+)");


    private Pattern pattern;

    Commands(String regexPattern){
        this.pattern = Pattern.compile(regexPattern);
    }

    public Matcher getMatcher (String input) {
        return pattern.matcher(input);
    }
}
