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
    //product page
    DIGEST("(?i)digest"),
    ATTRIBUTES("(?i)attributes"),
    COMPARE("(?i)compare\\s+(\\d+)"),
    COMMENTS("(?i)comments"),
    ADD_TO_CART("(?i)add\\s+to\\s+cart"),
    ADD_COMMENT("(i?)add\\s+comment");

    private Pattern pattern;

    Commands(String regexPattern){
        this.pattern = Pattern.compile(regexPattern);
    }

    public Matcher getMatcher (String input) {
        return pattern.matcher(input);
    }
}
