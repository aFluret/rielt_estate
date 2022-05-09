package finalkursproject.dao;


import finalkursproject.entity.RegistrationService;
import finalkursproject.exception.DaoException;

import java.util.List;

public interface IRegistrationServiceDao extends AbstractDao {

    void addRegistrationService(int idRegistration, int idService, int quantity) throws DaoException;

    void removeRegistrationService(int idRegistration, int idService) throws DaoException;


    void deleteRegistrationService(int idRegistration) throws DaoException;

    List<RegistrationService> findRegistrationServicesByClientId(int idClient) throws DaoException;

    List<RegistrationService> findAllRegistrationServices() throws DaoException;

    boolean checkActiveRegistrationService(int idService) throws DaoException;

    RegistrationService checkServiceInActiveRegistration(int idRegistration, int idService) throws DaoException;

    //void addRegistrationServiceQuantity(int idRegistration, int idService, int quantity) throws DaoException;

}
