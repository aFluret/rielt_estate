package finalkursproject.service.impl;

import finalkursproject.connectionpool.ConnectionPool;
import finalkursproject.connectionpool.ICloseConnectionPool;
import finalkursproject.exception.ConnectionPoolException;
import finalkursproject.exception.ServiceException;
import finalkursproject.service.ICloseDB;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class CloseDB implements ICloseDB {
    private static Logger LOGGER = Logger.getLogger(CloseDB.class);

    public void closeConnections() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Service: Close Connection");
        try {
            ICloseConnectionPool pool =  ConnectionPool.getInstance();
            pool.releasePool();
        } catch (ConnectionPoolException e) {
            throw new ServiceException(e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Service: Close Connection - success");
    }
}
