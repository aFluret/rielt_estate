package finalkursproject.dao.ext;

import finalkursproject.connectionpool.ConnectionPool;
import finalkursproject.dao.IRegistrationDao;
import finalkursproject.entity.Registration;
import finalkursproject.exception.ConnectionPoolException;
import finalkursproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDao implements IRegistrationDao {
    private static final Logger LOGGER = Logger.getLogger(RegistrationDao.class);
    private static final String FIND_ACTIVE_REGISTRATION_BY_CLIENT_ID = "SELECT * FROM rielt_estate.registration WHERE rielt_estate.registration.client_user_iduser =? AND rielt_estate.registration.status=1";
    private static final String EDIT_REGISTRATION_COST = "UPDATE rielt_estate.registration SET rielt_estate.registration.total_cost = (rielt_estate.registration.total_cost + ?) WHERE rielt_estate.registration.idregistration = ?";
    private static final String ADD_REGISTRATION = "INSERT INTO rielt_estate.registration (date,client_user_iduser,status) VALUES (?,?,1)";
    private static final String FIND_REGISTRATIONS_BY_CLIENT_ID = "SELECT * FROM rielt_estate.registration WHERE rielt_estate.registration.client_user_iduser =?";
    private static final String FIND_REGISTRATION_BY_REGISTRATION_ID = "SELECT * FROM rielt_estate.registration WHERE rielt_estate.registration.idregistration =?";
    private static final String PAY_REGISTRATION = "UPDATE rielt_estate.registration SET rielt_estate.registration.status = 0 WHERE rielt_estate.registration.idregistration = ?";
    private static final String FIND_ALL_REGISTRATIONS = "SELECT * FROM rielt_estate.registration";
    private static final String DELETE_EMPTY_REGISTRATION = "DELETE FROM rielt_estate.registration WHERE rielt_estate.registration.client_user_iduser=? AND rielt_estate.registration.status=1";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public Registration findActiveRegistrationByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Registration DAO: start findActiveRegistrationByClientId");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ACTIVE_REGISTRATION_BY_CLIENT_ID);
                statement.setInt(1, idClient);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    return createRegistrationByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Registration DAO: finish findActiveRegistrationByClientId");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return null;
    }

    @Override
    public boolean editRegistrationCost(int idRegistration, double deltaTotalCost) throws DaoException {
        LOGGER.log(Level.DEBUG, "Registration DAO: start editRegistrationCost");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(EDIT_REGISTRATION_COST);
                statement.setDouble(1, deltaTotalCost);
                statement.setInt(2, idRegistration);
                if (statement.executeUpdate() != 0) {
                    return true;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Registration DAO: finish editRegistrationCost");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query registration editing", e);
        }
        return false;
    }

    @Override
    public boolean addRegistration(int idClient, String date) throws DaoException {
        LOGGER.log(Level.DEBUG, "Registration DAO: start addRegistration");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(ADD_REGISTRATION);
                statement.setString(1, date);
                statement.setInt(2, idClient);


                if (statement.executeUpdate() != 0) {
                    return true;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Registration DAO: finish addRegistration");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query registration adding", e);
        }
        return false;
    }

    @Override
    public List<Registration> findAllRegistrationsByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationDao: Start findAllRegistrationsByClientId.");
        List<Registration> registrations;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_REGISTRATIONS_BY_CLIENT_ID);
                statement.setInt(1, idClient);
                resultSet = statement.executeQuery();
                registrations = new ArrayList<>();
                while (resultSet.next()) {
                    registrations.add(createRegistrationByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationDao: Finish findAllRegistrationsByClientId.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return registrations;

    }

    @Override
    public List<Registration> findAllRegistrations() throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationDao: Start findAllRegistrations.");
        List<Registration> registrations;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_REGISTRATIONS);
                resultSet = statement.executeQuery();
                registrations = new ArrayList<>();
                while (resultSet.next()) {
                    registrations.add(createRegistrationByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationDao: Finish findAllRegistrations.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return registrations;

    }


    @Override
    public Registration findRegistrationByRegistrationId(int idRegistration) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationDao: Start findRegistrationByRegistrationId.");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_REGISTRATION_BY_REGISTRATION_ID);
                statement.setInt(1, idRegistration);
                resultSet = statement.executeQuery();
                while (resultSet.first()) {
                    return createRegistrationByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationDao: Finish findRegistrationByRegistrationId.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return null;

    }

    @Override
    public void payRegistration(int idRegistration) throws DaoException {
        LOGGER.log(Level.DEBUG, "Registration DAO: start payRegistration");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(PAY_REGISTRATION);
                statement.setInt(1, idRegistration);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Registration DAO: finish payRegistration");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query registration adding", e);
        }
    }

    private Registration createRegistrationByResultSet(ResultSet resultSet) throws DaoException {
        Registration registration;
        try {
            registration = new Registration();
            registration.setIdRegistration(resultSet.getInt("idregistration"));
            registration.setDate(resultSet.getString("date"));
            registration.setTotalCost(resultSet.getDouble("total_cost"));
            registration.setIdClient(resultSet.getInt("client_user_iduser"));
            registration.setStatus(resultSet.getBoolean("status"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return registration;
    }

    @Override
    public void deleteEmptyRegistration(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start deleteEmptyRegistration");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(DELETE_EMPTY_REGISTRATION);
                statement.setInt(1, idClient);
                statement.executeUpdate();
            } finally {
                LOGGER.log(Level.DEBUG, "Client DAO: finish deleteEmptyRegistration");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

}
