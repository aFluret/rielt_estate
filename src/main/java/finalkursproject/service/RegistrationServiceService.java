package finalkursproject.service;


import finalkursproject.entity.RegistrationService;
import finalkursproject.exception.ServiceException;

import java.util.List;

public interface RegistrationServiceService {
    void addRegistrationService(int idRegistration, int idService, int quantity) throws ServiceException;

    void removeRegistrationService(int idRegistration, int idService) throws ServiceException;

    void deleteRegistrationService(int idRegistration) throws ServiceException;

    List<RegistrationService> findRegistrationServicesByClientId(int idClient) throws ServiceException;

    List<RegistrationService> findAllRegistrationServices() throws ServiceException;

    boolean checkActiveRegistrationService(int idService) throws ServiceException;

    RegistrationService checkServiceInActiveRegistration(int idRegistration,int  idService) throws ServiceException;

//    void addRegistrationServiceQuantity(int idRegistration,int idService,int quantity) throws ServiceException;

}
