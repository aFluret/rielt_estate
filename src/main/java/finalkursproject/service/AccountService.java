package finalkursproject.service;


import finalkursproject.entity.Account;
import finalkursproject.exception.ServiceException;

public interface AccountService {
    Account findAccountByClientId(int idClient) throws ServiceException;

    void payRegistration(int idClient, double totalCost,double point) throws ServiceException;

    void payPartRegistration(int idClient) throws ServiceException;

    void deleteAccount(int idAccount) throws ServiceException;

    Account findAccountByNumber(String accountNumber) throws ServiceException;

    void addAccount(int idClient, String accountNumber) throws ServiceException;

}

