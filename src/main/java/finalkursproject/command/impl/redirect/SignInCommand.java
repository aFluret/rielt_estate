package finalkursproject.command.impl.redirect;

import finalkursproject.command.ICommand;
import finalkursproject.entity.Administrator;
import finalkursproject.entity.Client;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.ServiceFactory;
import finalkursproject.util.Hasher;
import finalkursproject.util.SessionElements;
import finalkursproject.webenum.PageName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command: Sign in started.");
        String login = request.getParameter("login_in");
        String password = request.getParameter("password_in");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            diagnoseIncorrectSignIn(request);
            return pageName.getPath();
        }
        password = Hasher.sha1Hash(password);
        try {
            Administrator administrator = serviceFactory.getAdministratorService().signIn(login, password);
            if (administrator != null) {
                HttpSession session = request.getSession();
                session.setAttribute("role", 1);
                session.setAttribute("admin", administrator);
                LOGGER.log(Level.INFO, "Successful sign in account as administrator " + administrator.getLogin());
                response.sendRedirect(SessionElements.getPageCommand(request));
            } else {
                Client client = serviceFactory.getClientService().signIn(login, password);
                if (client != null) {
                    if (!serviceFactory.getClientService().checkBan(client.getIdUser())) {
                        HttpSession session = request.getSession();
                        session.setAttribute("role", 2);
                        session.setAttribute("client", client);
                        LOGGER.log(Level.INFO, "Successful sign in account as client " + client.getLogin());
                        response.sendRedirect(SessionElements.getPageCommand(request));
                    } else {
                        diagnoseClientBan(request);
                        response.sendRedirect(SessionElements.getPageCommand(request));
                    }
                } else {
                    diagnoseIncorrectSignIn(request);
                    response.sendRedirect(SessionElements.getPageCommand(request));
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            pageName = pageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Sign in finished.");
        return pageName.getPath();
    }

    private static void diagnoseIncorrectSignIn(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Неверно введен логин или пароль. Повторите вход.");
        } else {
            request.getSession().setAttribute("error_data", "Incorrect login or password. Try again.");
        }
    }

    private static void diagnoseClientBan(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Вы заблокированы.");
        } else {
            request.getSession().setAttribute("error_data", "You're blocked.");
        }
    }

}
