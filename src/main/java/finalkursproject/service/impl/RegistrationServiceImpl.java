package finalkursproject.service.impl;


import finalkursproject.entity.Registration;
import finalkursproject.exception.DaoException;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.DaoFactory;
import finalkursproject.service.RegistrationService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class RegistrationServiceImpl implements RegistrationService {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public Registration findActiveRegistrationByClientId(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationService: Start findActiveRegistrationByClientId");
        try {
            LOGGER.log(Level.DEBUG, "RegistrationService: Finish findActiveRegistrationByClientId");
            return daoFactory.getRegistrationDao().findActiveRegistrationByClientId(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void editRegistrationCost(int idRegistration, double deltaTotalCost) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationService: Start editRegistration");
        try {
            daoFactory.getRegistrationDao().editRegistrationCost(idRegistration, deltaTotalCost);
            LOGGER.log(Level.DEBUG, "RegistrationService: Finish editRegistration");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void addRegistration(int idClient, String date) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationService: Start addRegistration");
        try {
            daoFactory.getRegistrationDao().addRegistration(idClient, date);
            LOGGER.log(Level.DEBUG, "RegistrationService: Finish addRegistration");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public List<Registration> findAllRegistrationsByClientId(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationService: Start findAllRegistrationsByClientId");
        try {
            LOGGER.log(Level.DEBUG, "Registration Service: Finish findAllRegistrationsByClientId");
            return daoFactory.getRegistrationDao().findAllRegistrationsByClientId(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<Registration> findAllRegistrations() throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationService: Start findAllRegistrations");
        try {
            LOGGER.log(Level.DEBUG, "Registration Service: Finish findAllRegistrations");
            return daoFactory.getRegistrationDao().findAllRegistrations();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Registration findRegistrationByRegistrationId(int idRegistration) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationService: Start findRegistrationByRegistrationId");
        try {
            LOGGER.log(Level.DEBUG, "Registration Service: Finish findRegistrationByRegistrationId");
            return daoFactory.getRegistrationDao().findRegistrationByRegistrationId(idRegistration);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void payRegistration(int idRegistration) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationService: Start payRegistration");
        try {
            daoFactory.getRegistrationDao().payRegistration(idRegistration);
            LOGGER.log(Level.DEBUG, "Registration Service: Finish payRegistration");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void deleteEmptyRegistration(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationService: Start deleteEmptyRegistration");
        try {
            LOGGER.log(Level.DEBUG, "RegistrationService: Finish deleteEmptyRegistration");
            daoFactory.getRegistrationDao().deleteEmptyRegistration(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


}
