package finalkursproject.service;


import finalkursproject.entity.Service;
import finalkursproject.exception.ServiceException;

import java.util.List;

public interface ServiceService {
    List<Service> findAllServices() throws ServiceException;

    List<Service> findServiceByType(String serviceType) throws ServiceException;

    Service findServiceById(int idService) throws ServiceException;

    List<Service> findExistServices() throws ServiceException;

    void deleteService(int idService) throws ServiceException;

    Service findServiceByName(String nameEn, String nameRu) throws ServiceException;

    void addService(String type, String nameRu, double cost,  String imagePath) throws ServiceException;

    Service findServiceByNameAndId( String nameRu, int idService) throws ServiceException;

    void editService(int idService, String type,  String nameRu, double cost,  String imagePath) throws ServiceException;
}
