package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.entity.Client;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import finalkursproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignOutCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(SignOutCommand.class);
    private PageName jspPageName = PageName.ERROR;
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Sign out start");
        try {
            String locale = SessionElements.getLocale(request);
            if ((int) request.getSession().getAttribute("role") == 1) {
                request.getSession().removeAttribute("admin");
                request.getSession().setAttribute("role", 0);
            } else {
                int idClient =((Client) request.getSession().getAttribute("client")).getIdUser();
                if (serviceFactory.getRegistrationService().findActiveRegistrationByClientId(idClient)!=null){
                    serviceFactory.getRegistrationServiceService().deleteRegistrationService(serviceFactory.getRegistrationService().findActiveRegistrationByClientId(idClient).getIdRegistration());
                    serviceFactory.getRegistrationService().deleteEmptyRegistration(idClient);
                    request.getSession().removeAttribute("client");
                    request.getSession().setAttribute("role", 0);
                }
            }
            request.getSession().invalidate();
            request.getSession().setAttribute("locale", locale);
            response.sendRedirect(PageNameRedirect.INDEX.getPath());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Sign out finish");
        return jspPageName.getPath();
    }
}
