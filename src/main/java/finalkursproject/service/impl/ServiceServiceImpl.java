package finalkursproject.service.impl;


import finalkursproject.entity.Service;
import finalkursproject.exception.DaoException;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.DaoFactory;
import finalkursproject.service.ServiceService;
import finalkursproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class ServiceServiceImpl implements ServiceService {
    private static final Logger LOGGER = LogManager.getLogger(ServiceServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<Service> findAllServices() throws ServiceException {
        LOGGER.log(Level.DEBUG, "ServiceService: Start get all services");
        try {
            LOGGER.log(Level.DEBUG, "Service Service: Finish get all services");
            return daoFactory.getServiceDao().findAllServices();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<Service> findExistServices() throws ServiceException {
        LOGGER.log(Level.DEBUG, "ServiceService: Start get exist services");
        try {
            LOGGER.log(Level.DEBUG, "Service Service: Finish get exist services");
            return daoFactory.getServiceDao().findExistServices();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void deleteService(int idService) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ServiceService: Start deleteService");
        try {
            LOGGER.log(Level.DEBUG, "Service Service: Finish deleteService");
            daoFactory.getServiceDao().deleteService(idService);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Service findServiceByName(String nameEn, String nameRu) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ServiceService: Start find service by name");
        try {
            if (Validator.isEmptyString(nameEn,nameRu) && Validator.isNull(nameEn,nameRu) && Validator.matchServiceName(nameEn,nameRu)) {

                LOGGER.log(Level.DEBUG, "Service Service: Finish find service by name");
                return daoFactory.getServiceDao().findServiceByName(nameEn, nameRu);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public void addService(String type,  String nameRu, double cost,  String imagePath) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Service Service: start addService");
        try {
            if (Validator.isNull(type, nameRu, cost,  imagePath) && Validator.isEmptyString(type,  nameRu, imagePath) &&Validator.matchServiceWeightCost(cost)) {
                daoFactory.getServiceDao().addService(type, nameRu, cost,  imagePath);
                LOGGER.log(Level.DEBUG, "Service Service: end addService");
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Service findServiceByNameAndId(String nameRu, int idService) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ServiceService: Start findServiceByNameAndId");
        try {
            if (Validator.isEmptyString( nameRu) && Validator.matchServiceName(nameRu)) {

                LOGGER.log(Level.DEBUG, "Service Service: Finish findServiceByNameAndId");
                return daoFactory.getServiceDao().findServiceByNameAndId(nameRu, idService);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public void editService(int idService, String type,  String nameRu, double cost,  String imagePath) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Service Service: start editService");
        try {
            if (Validator.isNull(type,  nameRu, cost, imagePath) && Validator.isEmptyString(type,  nameRu, imagePath) ) {
                daoFactory.getServiceDao().editService(idService,type,  nameRu, cost,  imagePath);
                LOGGER.log(Level.DEBUG, "Service Service: end editService");
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }


    @Override
    public List<Service> findServiceByType(String serviceType) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ServiceService: Start find service by type");
        try {
            if (Validator.isNull(serviceType) && Validator.isEmptyString(serviceType)) {
                LOGGER.log(Level.DEBUG, "Service Service: Finish find service by type");
                return daoFactory.getServiceDao().findServiceByType(serviceType);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public Service findServiceById(int idService) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ServiceService: Start find service by id");
        try {
            LOGGER.log(Level.DEBUG, "Service Service: Finish find service by id");
            return daoFactory.getServiceDao().findServiceById(idService);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
