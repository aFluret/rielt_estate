package finalkursproject.dao.ext;

import finalkursproject.connectionpool.ConnectionPool;
import finalkursproject.dao.IAccountDao;
import finalkursproject.entity.Account;
import finalkursproject.exception.ConnectionPoolException;
import finalkursproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;

public class AccountDao implements IAccountDao {
    private static final Logger LOGGER = Logger.getLogger(AccountDao.class);
    private static final String FIND_ACCOUNT_BY_CLIENT_ID = "SELECT * FROM rielt_estate.account WHERE rielt_estate.account.client_user_iduser =?";
    private static final String FIND_ACCOUNT_BY_NUMBER = "SELECT * FROM rielt_estate.account WHERE rielt_estate.account.account_number =?";
    private static final String PAY_REGISTRATION = "UPDATE rielt_estate.account SET rielt_estate.account.account_status = (rielt_estate.account.account_status - ?) WHERE rielt_estate.account.client_user_iduser = ?";
    private static final String PAY_PART_REGISTRATION = "UPDATE rielt_estate.account SET rielt_estate.account.account_status = 0 WHERE rielt_estate.account.client_user_iduser = ?";
    private static final String DELETE_ACCOUNT = "DELETE FROM account WHERE account.idaccount=?";
    private static final String ADD_ACCOUNT = "INSERT INTO account (client_user_iduser,account_status,account_number) VALUES (?,?,?)";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    @Override
    public Account findAccountByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: start findAccountByClientId");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ACCOUNT_BY_CLIENT_ID);
                statement.setInt(1, idClient);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    return createAccountByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Account DAO: finish findAccountByClientId");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return null;

    }

    @Override
    public void payRegistration(int idClient, double totalCost) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: start payRegistration");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(PAY_REGISTRATION);
                statement.setDouble(1, totalCost);
                statement.setInt(2, idClient);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Account DAO: finish payRegistration");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    @Override
    public void payPartRegistration(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: start payPartRegistration");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(PAY_PART_REGISTRATION);
                statement.setInt(1, idClient);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Account DAO: finish payPartRegistration");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }

    }

    @Override
    public void deleteAccount(int idAccount) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: start deleteAccount");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(DELETE_ACCOUNT);
                statement.setInt(1, idAccount);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Account DAO: finish deleteAccount");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    @Override
    public Account findAccountByNumber(String accountNumber) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: start findAccountByNumber");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ACCOUNT_BY_NUMBER);
                statement.setString(1, accountNumber);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    return createAccountByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Account DAO: finish findAccountByNumber");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return null;
    }

    @Override
    public void addAccount(int idClient, String accountNumber, double accountStatus) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: start addAccount");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(ADD_ACCOUNT);
                statement.setInt(1, idClient);
                statement.setDouble(2, accountStatus);
                statement.setString(3, accountNumber);

                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Account DAO: finish addAccount");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    private Account createAccountByResultSet(ResultSet resultSet) throws DaoException {
        Account account;
        try {
            account = new Account();
            account.setIdAccount(resultSet.getInt("idaccount"));
            account.setAccountStatus(resultSet.getDouble("account_status"));
            account.setAccountNumber(resultSet.getString("account_number"));
            account.setIdClient(resultSet.getInt("client_user_iduser"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return account;
    }
}
