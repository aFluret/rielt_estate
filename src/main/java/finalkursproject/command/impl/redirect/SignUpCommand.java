package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.entity.Client;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.service.ClientService;
import finalkursproject.webenum.PageName;
import finalkursproject.webenum.PageNameRedirect;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignUpCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Sign up started.");
        Client client =null;
        ClientService clientService = serviceFactory.getClientService();
        String login = request.getParameter("login_up");
        String password = request.getParameter("password_up");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
       // String address = request.getParameter("address");
        String surname = request.getParameter("surname");
        try {
            if (clientService.findClientByEmail(email) != null) {
                diagnoseCommonEmail(request);
            } else {
                if (clientService.findClientByLogin(login) != null) {
                    diagnoseCommonLogin(request);
                } else {
                    client = clientService.signUp(login, password, name, surname, email);
                    if(client==null){
                        diagnoseCommonLogin(request);
                    }
                }
            }
            response.sendRedirect(PageNameRedirect.INDEX.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command: Sign Up finished.");
        return pageName.getPath();
    }


    private void diagnoseCommonLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким логином уже существует.");
        } else {
            request.getSession().setAttribute("error_data", "User with such login is already exists.");
        }
    }


    private void diagnoseCommonEmail(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким адресом электронной почты уже существует.");
        } else {
            request.getSession().setAttribute("error_data", "User with such email is already exists.");
        }
    }


}
