package finalkursproject.command.impl.forward;

import finalkursproject.command.ICommand;
import finalkursproject.entity.*;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import finalkursproject.webenum.PageNameRedirect;
import finalkursproject.entity.Client;
import finalkursproject.entity.Service;
import finalkursproject.entity.Registration;
import finalkursproject.entity.RegistrationService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowRegistrationAdminCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ShowRegistrationAdminCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.REGISTRATIONS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: ShowRegistrationClientCommand started.");
        try {
            List<Registration> registrations = serviceFactory.getRegistrationService().findAllRegistrations();
            List<RegistrationService> registrationServices = serviceFactory.getRegistrationServiceService().findAllRegistrationServices();
            List<Service> services = serviceFactory.getServiceService().findAllServices();
            List<Client> clients = serviceFactory.getClientService().findAllClients();
            request.setAttribute("clients", clients);
            request.setAttribute("registrations", registrations);
            request.setAttribute("registration_services",registrationServices);
            request.setAttribute("services", services);
            request.getSession().setAttribute("pageCommand", PageNameRedirect.REGISTRATIONS_ADMIN.getPath());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Command: ShowRegistrationClientCommand finished.");
        return pageName.getPath();

    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute("error_data", request.getSession().getAttribute("error_data"));
        request.getSession().removeAttribute("error_data");
    }

}
