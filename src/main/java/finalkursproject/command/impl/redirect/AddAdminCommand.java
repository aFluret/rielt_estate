package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.service.AdministratorService;
import finalkursproject.service.ClientService;
import finalkursproject.util.Hasher;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddAdminCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(AddAdminCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start add admin ");
        ClientService clientService = serviceFactory.getClientService();
        AdministratorService administratorService = serviceFactory.getAdministratorService();
        String login = request.getParameter("login-admin");
        String password = request.getParameter("password-admin");
        try {
            if (clientService.findClientByLogin(login) != null || administratorService.findAdministratorByLogin(login) != null) {
                diagnoseCommonLogin(request);
            } else {
                password = Hasher.sha1Hash(password);
                administratorService.addAdministrator(login,password);
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish add admin");
        return pageName.getPath();
    }

    private void diagnoseCommonLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким логином уже сужествует.");
        } else {
            request.getSession().setAttribute("error_data", "User with such login is already exists.");
        }
    }

}