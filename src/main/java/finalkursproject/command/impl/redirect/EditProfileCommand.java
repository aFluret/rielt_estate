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

public class EditProfileCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditProfileCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start EditProfileCommand");
        Client client = null;
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
     //   String address = request.getParameter("address");
        String clientEmail = ((Client) request.getSession().getAttribute("client")).getEmail();
        int idClient = ((Client) request.getSession().getAttribute("client")).getIdUser();
        try {
            if (!clientEmail.equals(email)) {
                if (serviceFactory.getClientService().findClientByEmail(email) == null) {
                    client = serviceFactory.getClientService().editClient(idClient, surname, name, email);
                    if(client!=null) {
                        request.getSession().setAttribute("client", client);
                    }
                } else {
                    diagnoseCommonEmail(request);
                }
            } else {
                client = serviceFactory.getClientService().editClient(idClient, surname, name, email);
                if(client!=null) {
                    request.getSession().setAttribute("client", client);
                }
            }
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish EditProfileCommand");
        return pageName.getPath();
    }

    private void diagnoseCommonEmail(HttpServletRequest request) {
        if (request.getSession().getAttribute("locale").equals("ru")) {
            request.getSession().setAttribute("error_data", "Пользователь с таким адресом электронной почты уже существует.");
        } else {
            request.getSession().setAttribute("error_data", "User with such email is already exists.");
        }
    }


}
