package finalkursproject.dao;

import finalkursproject.entity.Service;
import finalkursproject.exception.DaoException;

import java.util.List;

public interface IServiceDao extends AbstractDao {
    List<Service> findAllServices() throws DaoException;

    List<Service> findServiceByType(String serviceType) throws DaoException;

    Service findServiceById(int idService) throws DaoException;

    List<Service> findExistServices() throws DaoException;

    void deleteService(int idService) throws DaoException;

    Service findServiceByName(String nameEn, String nameRu) throws DaoException;

    void addService(String type,  String nameRu, double cost,  String imagePath) throws DaoException;

    Service findServiceByNameAndId( String nameRu, int idService) throws DaoException;

    void editService(int idService, String type,  String nameRu, double cost,  String imagePath) throws DaoException;

}
