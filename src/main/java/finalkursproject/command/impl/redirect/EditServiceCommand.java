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

public class EditServiceCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditServiceCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start EditServiceCommand");
        String type = request.getParameter("service_type");
        String nameRu = request.getParameter("nameRu");
        int idService = Integer.parseInt(request.getParameter("id_service"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        String imagePath = request.getParameter("image");
        try {
            if (serviceFactory.getServiceService().findServiceByNameAndId(nameRu,idService) != null) {
              /*  diagnoseCommonName(request);*/
            } else {
                serviceFactory.getServiceService().editService(idService,type, nameRu, cost, imagePath);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish EditServiceCommand");
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
