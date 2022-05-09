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


public class DeleteServiceCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(DeleteServiceCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start delete service");
        int idService;
        try {
            idService = Integer.parseInt(request.getParameter("idService"));
            if (serviceFactory.getRegistrationServiceService().checkActiveRegistrationService(idService)) {
                diagnoseDeleteError(request);
            } else {
                serviceFactory.getServiceService().deleteService(idService);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish delete service");
        return pageName.getPath();
    }

    private static void diagnoseDeleteError(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Данное помещение присутствует в открытой записи одного из клиентов. Удаление невозможно.");
        } else {
            request.getSession().setAttribute("error_data", "This room is present in the open recording of one of the clients. Removal is not possible.");
        }
    }

}