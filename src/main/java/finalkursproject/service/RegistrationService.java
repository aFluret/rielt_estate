package finalkursproject.service;


import finalkursproject.entity.Registration;
import finalkursproject.exception.ServiceException;

import java.util.List;

public interface RegistrationService {
    Registration findActiveRegistrationByClientId(int idClient) throws ServiceException;

    void editRegistrationCost(int idRegistration, double deltaTotalCost) throws ServiceException;

    void addRegistration(int idClient, String date) throws ServiceException;

    List<Registration> findAllRegistrationsByClientId(int idClient) throws ServiceException;

    List<Registration> findAllRegistrations() throws ServiceException;

    Registration findRegistrationByRegistrationId(int idRegistration) throws ServiceException;

    void payRegistration(int idRegistration) throws ServiceException;

    void deleteEmptyRegistration(int idClient) throws ServiceException;

}
