package finalkursproject.factory;

import finalkursproject.service.*;
import finalkursproject.service.impl.*;
import finalkursproject.service.*;
import finalkursproject.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private ClientService clientService = new ClientServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();
    private ServiceService serviceService = new ServiceServiceImpl();
    private RegistrationService registrationService = new RegistrationServiceImpl();
    private RegistrationServiceService registrationServiceService = new RegistrationServiceServiceImpl();
    private AccountService accountService = new AccountServiceImpl();
    private ReviewService reviewService = new ReviewServiceImpl();


    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }

    public RegistrationServiceService getRegistrationServiceService() {
        return registrationServiceService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }
}
