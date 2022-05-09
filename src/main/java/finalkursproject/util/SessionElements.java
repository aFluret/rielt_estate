package finalkursproject.util;


import finalkursproject.webenum.PageNameRedirect;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class SessionElements {

    public static String getLocale(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale == null || locale.isEmpty()) {
            locale = Locale.getDefault().getLanguage();
        }
        return locale;
    }


    public static String getPageCommand(HttpServletRequest request) {
        String pageCommand = (String) request.getSession().getAttribute("pageCommand");
        if (pageCommand == null || pageCommand.isEmpty()) {
            pageCommand = PageNameRedirect.INDEX.getPath();
        }
        return pageCommand;
    }
}
