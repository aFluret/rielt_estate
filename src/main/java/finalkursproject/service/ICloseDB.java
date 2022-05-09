package finalkursproject.service;

import finalkursproject.exception.ServiceException;

public interface ICloseDB {

    void closeConnections() throws ServiceException;
}
