package finalkursproject.dao;


import finalkursproject.entity.Account;
import finalkursproject.exception.DaoException;

public interface IAccountDao extends AbstractDao {
    Account findAccountByClientId(int idClient) throws DaoException;

    void payRegistration(int idClient, double totalCost) throws DaoException;

    void payPartRegistration(int idClient) throws DaoException;

    void deleteAccount(int idAccount) throws DaoException;

    Account findAccountByNumber(String accountNumber) throws DaoException;

    void addAccount(int idClient, String accountNumber, double account_status) throws DaoException;


}
