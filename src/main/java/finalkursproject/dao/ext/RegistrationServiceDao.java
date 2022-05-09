package finalkursproject.dao.ext;

import finalkursproject.connectionpool.ConnectionPool;
import finalkursproject.dao.IRegistrationServiceDao;
import finalkursproject.entity.RegistrationService;
import finalkursproject.exception.ConnectionPoolException;
import finalkursproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationServiceDao implements IRegistrationServiceDao {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServiceDao.class);
    private static final String ADD_REGISTRATION_SERVICE = "INSERT INTO rielt_estate.registration_service (registration_service.registration_idregistration,registration_service.service_idservice,registration_service.quantity) VALUES (?,?,1)";
    private static final String FIND_REGISTRATION_SERVICE_BY_CLIENT_ID = "SELECT * FROM rielt_estate.registration_service JOIN rielt_estate.registration ON rielt_estate.registration.idregistration = rielt_estate.registration_service.registration_idregistration WHERE rielt_estate.registration.client_user_iduser =?";
    private static final String REMOVE_REGISTRATION_SERVICE = "DELETE FROM rielt_estate.registration_service WHERE rielt_estate.registration_service.registration_idregistration=? AND rielt_estate.registration_service.service_idservice=?";
    private static final String DELETE_REGISTRATION_SERVICE = "DELETE FROM rielt_estate.registration_service WHERE rielt_estate.registration_service.registration_idregistration=?";
    private static final String FIND_REGISTRATION_SERVICE_BY_SERVICE_ID = "SELECT * FROM rielt_estate.registration_service WHERE rielt_estate.registration_service.service_idservice =?";
    private static final String FIND_ALL_REGISTRATION_SERVICE = "SELECT * FROM rielt_estate.registration_service";
    private static final String FIND_SERVICE_IN_ACTIVE_REGISTRATION = "SELECT * FROM rielt_estate.registration_service WHERE rielt_estate.registration_service.service_idservice =? AND rielt_estate.registration_service.registration_idregistration=?";
   // private static final String ADD_REGISTRATION_SERVICE_QUANTITY = "UPDATE arenda.registration_service SET arenda.registration_service.quantity = (arenda.registration_service.quantity+?) WHERE arenda.registration_service.service_idservice = ? AND arenda.registration_service.registration_idregistration=?";

    private static final String ADD_REGISTRATION_SERVICE_QUANTITY = "UPDATE arenda.registration_service SET arenda.registration_service.quantity = (arenda.registration_service.quantity+?) WHERE arenda.registration_service.service_idservice = 1";

    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    @Override
    public void addRegistrationService(int idRegistration, int idService, int quantity) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationService DAO: start addRegistrationService");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                quantity=1;
                statement = connection.prepareStatement(ADD_REGISTRATION_SERVICE);
                statement.setInt(1, idRegistration);
                statement.setInt(2, idService);

                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationService DAO: finish addRegistrationService");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    @Override
    public void removeRegistrationService(int idRegistration, int idService) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationService DAO: start removeRegistrationService");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(REMOVE_REGISTRATION_SERVICE);
                statement.setInt(1, idRegistration);
                statement.setInt(2, idService);

                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationService DAO: finish removeRegistrationService");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    @Override
    public void deleteRegistrationService(int idRegistration) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationService DAO: start deleteRegistrationService");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(DELETE_REGISTRATION_SERVICE);
                statement.setInt(1, idRegistration);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationService DAO: finish deleteRegistrationService");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }

    }

    @Override
    public List<RegistrationService> findRegistrationServicesByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceDAO: start findActiveRegistrationByClientId");
        List<RegistrationService> registrationServices = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_REGISTRATION_SERVICE_BY_CLIENT_ID);
                statement.setInt(1, idClient);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    registrationServices.add(createRegistrationServiceByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationServiceDAO: finish findActiveRegistrationByClientId");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return registrationServices;

    }

    @Override
    public List<RegistrationService> findAllRegistrationServices() throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceDAO: start findAllRegistrationServices");
        List<RegistrationService> registrationServices = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_REGISTRATION_SERVICE);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    registrationServices.add(createRegistrationServiceByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationServiceDAO: finish findAllRegistrationServices");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return registrationServices;

    }


    @Override
    public boolean checkActiveRegistrationService(int idService) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationService DAO: start checkActiveRegistrationService");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_REGISTRATION_SERVICE_BY_SERVICE_ID);
                statement.setInt(1, idService);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    return true;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationService DAO: finish checkActiveRegistrationService");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return false;
    }

    @Override
    public RegistrationService checkServiceInActiveRegistration(int idRegistration, int idService) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationService DAO: start checkServiceInActiveRegistration");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_SERVICE_IN_ACTIVE_REGISTRATION);
                statement.setInt(1, idService);
                statement.setInt(2, idRegistration);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    return createRegistrationServiceByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationService DAO: finish checkServiceInActiveRegistration");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return null;
    }

   /* @Override
    public void addRegistrationServiceQuantity(int idRegistration, int idService, int quantity) throws DaoException {
        LOGGER.log(Level.DEBUG, "RegistrationService DAO: start addRegistrationServiceQuantity");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                quantity=1;
                statement = connection.prepareStatement(ADD_REGISTRATION_SERVICE_QUANTITY);
                statement.setInt(1, quantity);
                statement.setInt(2, idService);
                statement.setInt(3, idRegistration);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationService DAO: finish addRegistrationServiceQuantity");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }*/

    private RegistrationService createRegistrationServiceByResultSet(ResultSet resultSet) throws DaoException {
        RegistrationService registrationService = new RegistrationService();
        try {
            registrationService.setIdRegistration(resultSet.getInt("registration_idregistration"));
            registrationService.setIdService(resultSet.getInt("service_idservice"));
            registrationService.setQuantity(resultSet.getInt("quantity"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return registrationService;
    }
}
