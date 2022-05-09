package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.entity.Account;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CloseRegistrationCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(CloseRegistrationCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.REGISTRATIONS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start CloseRegistrationCommand");
        int idRegistration,idClient;
        double totalCost,point;
        Account account;
        try {
            idRegistration = Integer.parseInt(request.getParameter("idRegistration"));
            idClient = Integer.parseInt(request.getParameter("idClient"));
            totalCost = (serviceFactory.getRegistrationService().findRegistrationByRegistrationId(idRegistration)).getTotalCost();
            point = (serviceFactory.getClientService().findClientById(idClient)).getPoint();
            if (totalCost != 0) {
                if (serviceFactory.getAccountService().findAccountByClientId(idClient) != null) {
                    account = serviceFactory.getAccountService().findAccountByClientId(idClient);
                    if (account.getAccountStatus() > totalCost) {
                        serviceFactory.getAccountService().payRegistration(idClient, totalCost,point);
                        serviceFactory.getRegistrationService().payRegistration(idRegistration);
                        diagnoseSuccessfulPayment(request);
                    } else {
                        serviceFactory.getAccountService().payPartRegistration(idClient);
                        serviceFactory.getRegistrationService().payRegistration(idRegistration);
                        diagnosePartPayment(request);
                    }
                } else {
                    serviceFactory.getRegistrationService().payRegistration(idRegistration);
                    diagnosePaymentByCash(request);
                }
            } else {
                diagnoseEmptyRegistration(request);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish CloseRegistrationCommand");
        return pageName.getPath();
    }

    private static void diagnosePartPayment(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Запись оплачена наличным и безналичным расчетом.");
        } else {
            request.getSession().setAttribute("error_data", "The registration was paid by cash and by card.");
        }
    }

    private static void diagnoseEmptyRegistration(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Запись пуста.");
        } else {
            request.getSession().setAttribute("error_data", "The registration is empty.");
        }
    }

    private static void diagnosePaymentByCash(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Запись оплачена наличными и закрыта.");
        } else {
            request.getSession().setAttribute("error_data", "The registration was paid by cash and closed.");
        }
    }

    private static void diagnoseSuccessfulPayment(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Запись успешно оплачена и закрыта.");
        } else {
            request.getSession().setAttribute("error_data", "Registration was successfully paid and closed.");
        }
    }



}