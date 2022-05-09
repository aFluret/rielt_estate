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


public class RemoveBasketServiceCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(RemoveBasketServiceCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start remove service from basket");
        int idRegistration,idService,quantity;
        double deltaTotalCost;
        try {
            idRegistration = Integer.parseInt(request.getParameter("idRegistration"));
            idService = Integer.parseInt(request.getParameter("idService"));
            quantity = 1;
            serviceFactory.getRegistrationServiceService().removeRegistrationService(idRegistration,idService);
            deltaTotalCost = (serviceFactory.getServiceService().findServiceById(idService)).getCost() * 1;
            serviceFactory.getRegistrationService().editRegistrationCost(idRegistration, (-deltaTotalCost));

            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish remove service from basket");
        return pageName.getPath();
    }
}