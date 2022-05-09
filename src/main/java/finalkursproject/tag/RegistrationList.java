package finalkursproject.tag;


import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class RegistrationList extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        Integer role = (Integer) pageContext.getSession().getAttribute("role");
        String serviceList = null;
        if (role != null) {
            switch (role) {
                case 1:
                    serviceList = "/front/jsp/admin/registrationlist.jsp";
                    break;
                case 2:
                    serviceList = "/front/jsp/client/registrationlist.jsp";
                    break;
            }
        }
        try {
            pageContext.include(serviceList);
        } catch (IOException | ServletException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
