package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BanClientCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(BanClientCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start BanClientCommand");
        int idClient;
        try {
            idClient = Integer.parseInt(request.getParameter("idClient"));
            if ((serviceFactory.getClientService().findClientById(idClient)).isBan()) {
                serviceFactory.getClientService().unbanClient(idClient);
                diagnoseClientUnban(request);
            } else {
                serviceFactory.getClientService().banClient(idClient);
                diagnoseClientBan(request);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish BanClientCommand");
        return pageName.getPath();
    }

    private static void diagnoseClientBan(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Клиент заблокирован.");
        } else {
            request.getSession().setAttribute("error_data", "Client blocked.");
        }
    }

    private static void diagnoseClientUnban(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Клиент разблокирован.");
        } else {
            request.getSession().setAttribute("error_data", "Client unblocked.");
        }
    }


}