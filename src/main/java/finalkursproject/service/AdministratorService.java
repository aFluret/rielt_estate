package finalkursproject.service;

import finalkursproject.entity.Administrator;
import finalkursproject.exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    Administrator signIn(String login, String password) throws ServiceException;

    List<Administrator> findAllAdministrators() throws ServiceException;

    void deleteAdministrator(int idAdmin) throws ServiceException;

    Administrator findAdministratorByLogin(String login) throws ServiceException;

    Administrator addAdministrator(String login,String password) throws ServiceException;

    Administrator changePassword(int idAdmin, String password) throws ServiceException;

    Administrator findAdministratorByIdAndPassword(int idAdmin,String oldPassword) throws ServiceException;

}
