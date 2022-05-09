package finalkursproject.service.impl;


import finalkursproject.entity.RegistrationService;
import finalkursproject.exception.DaoException;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.DaoFactory;
import finalkursproject.service.RegistrationServiceService;
import finalkursproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class RegistrationServiceServiceImpl implements RegistrationServiceService {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationServiceServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void addRegistrationService(int idRegistration, int idService, int quantity) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceService: Start addRegistrationService");
        try {
          //  if (Validator.isNull(quantity) && Validator.isEmptyString(String.valueOf(quantity)) && Validator.matchServiceQuantity(quantity)) {
                daoFactory.getRegistrationServiceDao().addRegistrationService(idRegistration, idService, quantity);
                LOGGER.log(Level.DEBUG, "RegistrationServiceService: Finish addRegistrationService");
       //     }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void removeRegistrationService(int idRegistration, int idService) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceService: Start removeRegistrationService");
        try {
            daoFactory.getRegistrationServiceDao().removeRegistrationService(idRegistration, idService);
            LOGGER.log(Level.DEBUG, "RegistrationServiceService: Finish removeRegistrationService");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void deleteRegistrationService(int idRegistration) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceService: Start deleteRegistrationService");
        try {
            daoFactory.getRegistrationServiceDao().deleteRegistrationService(idRegistration);
            LOGGER.log(Level.DEBUG, "RegistrationServiceService: Finish deleteRegistrationService");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public List<RegistrationService> findRegistrationServicesByClientId(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceService: Start findRegistrationServicesByClientId");
        try {
            LOGGER.log(Level.DEBUG, "RegistrationServiceService: Finish findRegistrationServicesByClientId");
            return daoFactory.getRegistrationServiceDao().findRegistrationServicesByClientId(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public List<RegistrationService> findAllRegistrationServices() throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceService: Start findAllRegistrationServices");
        try {
            LOGGER.log(Level.DEBUG, "RegistrationServiceService: Finish findAllRegistrationServices");
            return daoFactory.getRegistrationServiceDao().findAllRegistrationServices();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }


    @Override
    public boolean checkActiveRegistrationService(int idService) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceService: Start checkActiveRegistrationService");
        try {
            LOGGER.log(Level.DEBUG, "RegistrationServiceService: Finish checkActiveRegistrationService");
            return daoFactory.getRegistrationServiceDao().checkActiveRegistrationService(idService);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public RegistrationService checkServiceInActiveRegistration(int idRegistration, int idService) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceService: Start checkServiceInActiveRegistration");
        try {
            LOGGER.log(Level.DEBUG, "RegistrationServiceService: Finish checkServiceInActiveRegistration");
            return daoFactory.getRegistrationServiceDao().checkServiceInActiveRegistration(idRegistration, idService);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }


    /*@Override
    public void addRegistrationServiceQuantity(int idRegistration, int idService, int quantity) throws ServiceException {
        LOGGER.log(Level.DEBUG, "RegistrationServiceService: Start addRegistrationServiceQuantity");
        try {
           // if (Validator.isNull(quantity) && Validator.isEmptyString(String.valueOf(quantity)) && Validator.matchServiceQuantity(quantity)) {
                LOGGER.log(Level.DEBUG, "RegistrationServiceService: Finish addRegistrationServiceQuantity");
                daoFactory.getRegistrationServiceDao().addRegistrationServiceQuantity(idRegistration, idService, 1);
          //  }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }*/
}
