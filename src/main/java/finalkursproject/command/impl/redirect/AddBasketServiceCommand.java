package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.entity.Client;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddBasketServiceCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddBasketServiceCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add service to basket");
        int idClient, idRegistration, idService, quantity=1;
        double deltaTotalCost;
        String date;
        try {
            idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
            date = (request.getParameter("date"));
           // date = request.getSession().getAttribute("date")).getDate();
            if (serviceFactory.getRegistrationService().findActiveRegistrationByClientId(idClient) != null) {
                idRegistration = serviceFactory.getRegistrationService().findActiveRegistrationByClientId(idClient).getIdRegistration();
                idService = Integer.parseInt(request.getParameter("idService"));

                quantity = 1;
                if (quantity > 0) {
                    if (serviceFactory.getRegistrationServiceService().checkServiceInActiveRegistration(idRegistration, idService) == null) {
                        serviceFactory.getRegistrationServiceService().addRegistrationService(idRegistration, idService, quantity);
                        deltaTotalCost = (serviceFactory.getServiceService().findServiceById(idService)).getCost() * 1;
                        serviceFactory.getRegistrationService().editRegistrationCost(idRegistration, deltaTotalCost);
                        diagnoseAddService(request);
                    } /*else {
                       // serviceFactory.getRegistrationServiceService().addRegistrationServiceQuantity(idRegistration, idService, 1);
                        deltaTotalCost = (serviceFactory.getServiceService().findServiceById(idService)).getCost() * 1;
                        serviceFactory.getRegistrationService().editRegistrationCost(idRegistration, deltaTotalCost);
                        diagnoseAddService(request);
                    }*/
                } else {
                    diagnoseEmptyChoise(request);
                }
            } else {
                serviceFactory.getRegistrationService().addRegistration(idClient, date);
                idRegistration = serviceFactory.getRegistrationService().findActiveRegistrationByClientId(idClient).getIdRegistration();
                idService = Integer.parseInt(request.getParameter("idService"));
                quantity = 1;
                if (quantity > 0) {

                    serviceFactory.getRegistrationServiceService().addRegistrationService(idRegistration, idService,1);
                    deltaTotalCost = (serviceFactory.getServiceService().findServiceById(idService)).getCost();
                    serviceFactory.getRegistrationService().editRegistrationCost(idRegistration, deltaTotalCost);
                    diagnoseAddService(request);
                } //else {
                    //diagnoseEmptyChoise(request);
              //  }
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish add service to basket");
        return pageName.getPath();
    }

    private static void diagnoseAddService(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Запись добавлена");
        } else {
            request.getSession().setAttribute("error_data", "Service was added to your registration.");
        }
    }

    private static void diagnoseEmptyChoise(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "");
        } else {
            request.getSession().setAttribute("error_data", "Choose the quantity of this service.");
        }
    }


}