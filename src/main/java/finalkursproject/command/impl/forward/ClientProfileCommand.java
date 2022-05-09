package finalkursproject.command.impl.forward;

import finalkursproject.command.ICommand;
import finalkursproject.entity.Account;
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

public class ClientProfileCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ClientProfileCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.PROFILE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: ClientProfileCommand started.");
        try {
            int idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
            Account account = serviceFactory.getAccountService().findAccountByClientId(idClient);
            request.setAttribute("account", account);
            request.getSession().setAttribute("pageCommand", PageNameRedirect.PROFILE.getPath());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: ClientProfileCommand finished.");
        return pageName.getPath();

    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute("error_data", request.getSession().getAttribute("error_data"));
        request.getSession().removeAttribute("error_data");
    }

}
