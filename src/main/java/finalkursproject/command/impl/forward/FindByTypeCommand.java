package finalkursproject.command.impl.forward;

import finalkursproject.command.ICommand;
import finalkursproject.entity.Service;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import finalkursproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class FindByTypeCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(FindByTypeCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.SERVICES;
    private String serviceType;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: FindByTypeCommand started.");
        try {
            String type = request.getParameter("service_type");
            if (type != null) {
                serviceType = type;
            }
            List<Service> services = serviceFactory.getServiceService().findServiceByType(serviceType);
            if(services==null){
                diagnoseNoServices(request);
            }
            request.setAttribute("services", services);
            request.getSession().setAttribute("pageCommand", PageNameRedirect.FIND_BY_TYPE.getPath());
            request.getSession().setAttribute("locale", SessionElements.getLocale(request));
            rewrite(request);
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            ///pageName = pageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Command: FindByTypeCommand finished.");
        return pageName.getPath();
    }

    private void rewrite(HttpServletRequest request) {
        request.setAttribute("error_data", request.getSession().getAttribute("error_data"));
        request.getSession().removeAttribute("error_data");
    }

    private static void diagnoseNoServices(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Список услуг данного типа пуст.");
        } else {
            request.getSession().setAttribute("error_data", "Service list of this service type is empty.");
        }
    }




}
