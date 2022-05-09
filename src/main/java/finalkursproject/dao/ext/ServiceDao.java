package finalkursproject.dao.ext;

import finalkursproject.connectionpool.ConnectionPool;
import finalkursproject.dao.IServiceDao;
import finalkursproject.entity.Service;
import finalkursproject.exception.ConnectionPoolException;
import finalkursproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements IServiceDao {
    private static final Logger LOGGER = Logger.getLogger(ServiceDao.class);
    private static final String FIND_ALL_SERVICES = "SELECT * FROM service";
    private static final String FIND_EXIST_SERVICES = "SELECT * FROM service WHERE exist=1 ";
    private static final String FIND_SERVICE_BY_TYPE = "SELECT * FROM service WHERE type=? AND exist=1";
    private static final String FIND_SERVICE_BY_ID = "SELECT * FROM service WHERE idservice=?";
    private static final String DELETE_SERVICE = "UPDATE rielt_estate.service SET rielt_estate.service.exist = 0 WHERE rielt_estate.service.idservice = ?";
    private static final String FIND_SERVICE_BY_NAME = "SELECT * FROM service WHERE service.rus_name=?";
    private static final String FIND_SERVICE_BY_NAME_AND_ID = "SELECT * FROM service WHERE service.rus_name=? AND service.idservice!=?";
    private static final String ADD_SERVICE = "INSERT INTO service (rus_name,type,cost,image_path,exist) VALUES (?,?,?,?,1)";
    private static final String EDIT_SERVICE = "UPDATE rielt_estate.service SET rielt_estate.service.rus_name=?,rielt_estate.service.type=?,rielt_estate.service.cost=?,rielt_estate.service.image_path=? WHERE rielt_estate.service.idservice = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public List<Service> findAllServices() throws DaoException {
        LOGGER.log(Level.DEBUG, "ServiceDAO: Start find all services");
        List<Service> services;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_SERVICES);
                resultSet = statement.executeQuery();
                services = new ArrayList<>();
                while (resultSet.next()) {
                    services.add(createServiceByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ServiceDAO: Finish get all services");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return services;
    }

    @Override
    public List<Service> findExistServices() throws DaoException {
        LOGGER.log(Level.DEBUG, "ServiceDAO: Start find exist services");
        List<Service> services;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_EXIST_SERVICES);
                resultSet = statement.executeQuery();
                services = new ArrayList<>();
                while (resultSet.next()) {
                    services.add(createServiceByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ServiceDAO: Finish find exist services");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return services;
    }

    @Override
    public void deleteService(int idService) throws DaoException {
        LOGGER.log(Level.DEBUG, "Service DAO: start deleteService");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(DELETE_SERVICE);
                statement.setInt(1, idService);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "RegistrationService DAO: finish deleteService");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }

    }

    @Override
    public Service findServiceByName(String nameEn, String nameRu) throws DaoException {
        LOGGER.log(Level.DEBUG, "ServiceDAO: Start find service by name.");
        Service service = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_SERVICE_BY_NAME);
                statement.setString(1, nameRu);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    service = createServiceByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ServiceDAO: Finish find service by name.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return service;

    }

    @Override
    public void addService(String type, String nameRu, double cost,  String imagePath) throws
            DaoException {
        LOGGER.log(Level.DEBUG, "Service Dao: start addService");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(ADD_SERVICE);
                statement.setString(1, nameRu);
                statement.setString(2, type);
                statement.setDouble(3, cost);
                statement.setString(4, imagePath);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Service Dao: finish addService");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    @Override
    public Service findServiceByNameAndId( String nameRu, int idService) throws DaoException {
        LOGGER.log(Level.DEBUG, "ServiceDAO: Start findServiceByNameAndId.");
        Service service = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_SERVICE_BY_NAME_AND_ID);
                statement.setString(1, nameRu);
                statement.setInt(2, idService);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    service = createServiceByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ServiceDAO: Finish findServiceByNameAndId.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return service;
    }

    @Override
    public void editService(int idService, String type, String nameRu, double cost,
                             String imagePath) throws DaoException {
        LOGGER.log(Level.DEBUG, "Service Dao: start editService");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(EDIT_SERVICE);
                statement.setString(1, nameRu);
                statement.setString(2, type);
                statement.setDouble(3, cost);
                statement.setString(4, imagePath);
                statement.setInt(5, idService);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Service Dao: finish editService");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }


    @Override
    public List<Service> findServiceByType(String serviceType) throws DaoException {
        LOGGER.log(Level.DEBUG, "ServiceDAO: Start find service by type.");
        List<Service> services;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_SERVICE_BY_TYPE);
                statement.setString(1, serviceType);
                resultSet = statement.executeQuery();
                services = new ArrayList<>();
                while (resultSet.next()) {
                    services.add(createServiceByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ServiceDAO: Finish find service by type.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return services;

    }

    @Override
    public Service findServiceById(int idService) throws DaoException {
        LOGGER.log(Level.DEBUG, "ServiceDAO: Start find service by id.");
        Service service;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_SERVICE_BY_ID);
                statement.setInt(1, idService);
                resultSet = statement.executeQuery();
                service = new Service();
                while (resultSet.next()) {
                    service = createServiceByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ServiceDAO: Finish find service by id.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return service;

    }

    private Service createServiceByResultSet(ResultSet resultSet) throws DaoException {
        Service service = new Service();
        try {
            service.setIdService(resultSet.getInt("idservice"));
            service.setNameRu(resultSet.getString("rus_name"));
            service.setCost(resultSet.getDouble("cost"));
            service.setType(resultSet.getString("type"));
            service.setImagePath(resultSet.getString("image_path"));
            service.setExist(resultSet.getBoolean("exist"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return service;
    }


}
