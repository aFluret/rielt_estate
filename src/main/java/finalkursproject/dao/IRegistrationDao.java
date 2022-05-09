package finalkursproject.dao;


import finalkursproject.entity.Registration;
import finalkursproject.exception.DaoException;

import java.util.List;

public interface IRegistrationDao extends AbstractDao {
    Registration findActiveRegistrationByClientId(int idClient) throws DaoException;

    boolean editRegistrationCost(int idRegistration, double deltaTotalCost) throws DaoException;

    boolean addRegistration(int idClient, String date) throws DaoException;

    List<Registration> findAllRegistrationsByClientId(int idClient) throws DaoException;

    List<Registration> findAllRegistrations() throws DaoException;

    Registration findRegistrationByRegistrationId(int idRegistration) throws DaoException;

    void payRegistration(int idRegistration) throws DaoException;

    void deleteEmptyRegistration(int idClient) throws DaoException;

}
