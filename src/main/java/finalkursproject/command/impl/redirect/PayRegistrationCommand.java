package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.entity.Account;
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


public class PayRegistrationCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(PayRegistrationCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.REGISTRATIONS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start pay registration");
        int idClient, idRegistration;
        double totalCost;
        double point;
        Account account;
        Client client;
        try {
            idRegistration = Integer.parseInt(request.getParameter("idRegistration"));
            totalCost = (serviceFactory.getRegistrationService().findRegistrationByRegistrationId(idRegistration)).getTotalCost();
            idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
            point = ((Client) request.getSession().getAttribute("client")).getPoint();
            if (totalCost != 0) {
                if (serviceFactory.getAccountService().findAccountByClientId(idClient) != null) {
                    account = serviceFactory.getAccountService().findAccountByClientId(idClient);
                    if (account.getAccountStatus() > totalCost) {
                        serviceFactory.getAccountService().payRegistration(idClient, totalCost,point);
                        serviceFactory.getRegistrationService().payRegistration(idRegistration);
                        client=serviceFactory.getClientService().addPoints(idClient,totalCost-point);
                        request.getSession().setAttribute("client",client);
                        diagnoseSuccessfulPayment(request);
                    } else {
                        diagnoseLackOfMoney(request);
                    }

                } else {
                    diagnoseNoAccount(request);
                }
            } else {
                diagnoseEmptyRegistration(request);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e)

        {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish pay registration");
        return pageName.getPath();
    }

    private static void diagnoseLackOfMoney(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "На вашем счету недостаточно средств для оплаты заказа. Пополните счет либо оплатите заказ наличными.");
        } else {
            request.getSession().setAttribute("error_data", "There is no enough money on your account for paying for this registration. Replenish your account or pay in cash.");
        }
    }

    private static void diagnoseEmptyRegistration(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Запись пуста.");
        } else {
            request.getSession().setAttribute("error_data", "The registration is empty.");
        }
    }

    private static void diagnoseNoAccount(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "У вас нет счета. Зарегистрируйте его в личном кабинете либо оплатите запись наличными.");
        } else {
            request.getSession().setAttribute("error_data", "You havn't got the account. Register the account in your profile or pay in cash.");
        }
    }

    private static void diagnoseSuccessfulPayment(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Запись успешно оплачена!");
        } else {
            request.getSession().setAttribute("error_data", "Your registration was successfully confirmed. ");
        }
    }


}