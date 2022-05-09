package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.service.ServiceService;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddServiceCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddServiceCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add service ");
        ServiceService serviceService = serviceFactory.getServiceService();
        String type = request.getParameter("service_type");
        String nameRu = request.getParameter("nameRu");
        Double cost = Double.parseDouble(request.getParameter("cost"));
        String imagePath = request.getParameter("imageName");
        try {
            serviceService.addService(type, nameRu, cost,  imagePath);
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish add service");
        return pageName.getPath();
    }

    private static void diagnoseCommonName(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Помещение с данным названием уже присутствует в списке.");
        } else {
            request.getSession().setAttribute("error_data", "The room with the data named is already on the list.");
        }
    }

}