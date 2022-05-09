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


public class DeleteAccountCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(DeleteAccountCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PageName pageName = PageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Command:Start DeleteAccountCommand");
        int idAccount;
        try {
            idAccount = Integer.parseInt(request.getParameter("idAccount"));
            serviceFactory.getAccountService().deleteAccount(idAccount);
            response.sendRedirect(SessionElements.getPageCommand(request));
        } catch (IOException |
                ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
            pageName = PageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Command:Finish DeleteAccountCommand");
        return pageName.getPath();
    }


}