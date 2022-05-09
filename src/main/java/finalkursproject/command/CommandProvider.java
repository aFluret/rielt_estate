package finalkursproject.command;


import finalkursproject.command.impl.forward.*;
import finalkursproject.command.impl.redirect.*;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.Map;


public class CommandProvider {
    private final static CommandProvider INSTANCE = new CommandProvider();
    public Map<CommandName, ICommand> commands = new EnumMap<CommandName,ICommand>(CommandName.class);

    public CommandProvider() {

        commands.put(CommandName.SIGN_IN,new SignInCommand());
        commands.put(CommandName.SIGN_UP,new SignUpCommand());
        commands.put(CommandName.INDEX,new IndexCommand());
        commands.put(CommandName.CHANGE_LOCALE,new ChangeLocaleCommand());
        commands.put(CommandName.FIND_BY_TYPE,new FindByTypeCommand());
        commands.put(CommandName.SIGN_OUT,new SignOutCommand());
        commands.put(CommandName.ADD_SERVICE_BASKET,new AddBasketServiceCommand());
        commands.put(CommandName.SHOW_REGISTRATION_CLIENT,new ShowRegistrationClientCommand());
        commands.put(CommandName.REMOVE_SERVICE_BASKET,new RemoveBasketServiceCommand());
        commands.put(CommandName.PAY_FOR_REGISTRATION,new PayRegistrationCommand());
        commands.put(CommandName.CLIENT_PROFILE,new ClientProfileCommand());
        commands.put(CommandName.DELETE_SERVICE,new DeleteServiceCommand());
        commands.put(CommandName.ADD_SERVICE_ACTION,new AddServiceCommand());
        commands.put(CommandName.SHOW_CLIENT,new ShowClientCommand());
        commands.put(CommandName.BAN_CLIENT,new BanClientCommand());
        commands.put(CommandName.SHOW_ADMIN,new ShowAdminCommand());
        commands.put(CommandName.DELETE_ADMIN,new DeleteAdminCommand());
        commands.put(CommandName.ADD_ADMIN,new AddAdminCommand());
        commands.put(CommandName.SHOW_REGISTRATION_ADMIN,new ShowRegistrationAdminCommand());
        commands.put(CommandName.EDIT_PROFILE,new EditProfileCommand());
        commands.put(CommandName.STATISTIC,new EditProfileCommand());
        commands.put(CommandName.CHANGE_PASSWORD,new ChangePasswordCommand());
        commands.put(CommandName.DELETE_ACCOUNT,new DeleteAccountCommand());
        commands.put(CommandName.ADD_ACCOUNT,new AddAccountCommand());
        commands.put(CommandName.EDIT_SERVICE,new EditServiceCommand());
        commands.put(CommandName.CLOSE_REGISTRATION,new CloseRegistrationCommand());
        commands.put(CommandName.ADD_REVIEW,new AddReviewCommand());
        commands.put(CommandName.SHOW_REVIEWS,new ShowReviewsCommand());
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }


    public ICommand getCommand(HttpServletRequest request) {
        ICommand iCommand = commands.get(CommandName.WRONG_REQUEST);
        String command = request.getRequestURI();
        command=command.replace("/rielt-torg.by/","");
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            iCommand = commands.get(commandName);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return iCommand;
    }
}
