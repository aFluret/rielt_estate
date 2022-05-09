package finalkursproject.dao;

import finalkursproject.entity.User;
import finalkursproject.entity.Client;
import finalkursproject.exception.DaoException;

import java.util.List;

public interface IClientDao extends AbstractDao {
    Client signIn(String login, String password) throws DaoException;

    Client findClientByLogin(String login) throws DaoException;

    Client findClientByEmail(String email) throws DaoException;

    User addUser(String login, String password) throws DaoException;

    Client addClient(int idUser, String login, String name, String surname, String email) throws DaoException;

    User findUserByLogin(String login) throws DaoException;

    List<Client> findAllClients() throws DaoException;

    Client findClientById(int idClient) throws DaoException;

    void unbanClient(int idClient) throws DaoException;

    Client addPoints(int idClient, double point) throws DaoException;

    void banClient(int idClient) throws DaoException;

    boolean checkBan(int idClient) throws DaoException;

    Client editClient(int idClient, String surname, String name, String email) throws DaoException;

    Client changePassword(int idClient, String password) throws DaoException;

    Client findClientByIdAndPassword(int idClient, String oldPassword) throws DaoException;


}
