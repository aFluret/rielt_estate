package finalkursproject.factory;

import finalkursproject.dao.ext.*;
import finalkursproject.dao.ext.*;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();
    private ClientDao clientDao = new ClientDao();
    private AdministratorDao administratorDao = new AdministratorDao();
    private ServiceDao serviceDao = new ServiceDao();
    private RegistrationDao registrationDao = new RegistrationDao();
    private RegistrationServiceDao registrationServiceDao = new RegistrationServiceDao();
    private AccountDao accountDao = new AccountDao();
    private ReviewDao reviewDao = new ReviewDao();


    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public AdministratorDao getAdministratorDao() {
        return administratorDao;
    }

    public ServiceDao getServiceDao() {
        return serviceDao;
    }

    public RegistrationDao getRegistrationDao() {
        return registrationDao;
    }

    public RegistrationServiceDao getRegistrationServiceDao() {
        return registrationServiceDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ReviewDao getReviewDao() {
        return reviewDao;
    }
}
