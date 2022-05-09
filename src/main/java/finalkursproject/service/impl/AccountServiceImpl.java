package finalkursproject.service.impl;


import finalkursproject.entity.Account;
import finalkursproject.exception.DaoException;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.DaoFactory;
import finalkursproject.service.AccountService;
import finalkursproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public Account findAccountByClientId(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start findAccountByClientId");
        try {
            LOGGER.log(Level.DEBUG, "AccountService: Finish findAccountByClientId");
            return daoFactory.getAccountDao().findAccountByClientId(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void payRegistration(int idClient, double totalCost, double point) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start payRegistration");
        try {
            daoFactory.getAccountDao().payRegistration(idClient, totalCost - point);
            LOGGER.log(Level.DEBUG, "AccountService: Finish payRegistration");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void payPartRegistration(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start payPartRegistration");
        try {
            daoFactory.getAccountDao().payPartRegistration(idClient);
            LOGGER.log(Level.DEBUG, "AccountService: Finish payPartRegistration");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void deleteAccount(int idAccount) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start deleteAccount");
        try {
            daoFactory.getAccountDao().deleteAccount(idAccount);
            LOGGER.log(Level.DEBUG, "AccountService: Finish deleteAccount");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public Account findAccountByNumber(String accountNumber) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start findAccountByNumber");
        try {
            if (Validator.isNull(accountNumber) && Validator.isEmptyString(accountNumber) && Validator.matchAccountNumber(accountNumber)) {
                LOGGER.log(Level.DEBUG, "AccountService: Finish findAccountByNumber");
                return daoFactory.getAccountDao().findAccountByNumber(accountNumber);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public void addAccount(int idClient, String accountNumber) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start addAccount");
        try {
            if (Validator.isNull(accountNumber) && Validator.isEmptyString(accountNumber) && Validator.matchAccountNumber(accountNumber)) {
                double accountStatus = Math.random() * 1500;
                daoFactory.getAccountDao().addAccount(idClient, accountNumber, accountStatus);
                LOGGER.log(Level.DEBUG, "AccountService: Finish addAccount");
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }
}
