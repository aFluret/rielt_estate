package finalkursproject.service.impl;


import finalkursproject.dao.ext.ClientDao;
import finalkursproject.entity.User;
import finalkursproject.entity.Client;
import finalkursproject.exception.DaoException;
import finalkursproject.exception.ServiceException;
import finalkursproject.factory.DaoFactory;
import finalkursproject.service.ClientService;
import finalkursproject.util.Hasher;
import finalkursproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class ClientServiceImpl implements ClientService {
    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Client signIn(String login, String password) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: start SignInCommand");
        Client client = null;
        try {
            if (Validator.isNull(login, password) && Validator.isEmptyString(login, password) && Validator.matchLogin(login) && Validator.matchPassword(password)) {
                client = daoFactory.getClientDao().signIn(login, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Client Service: end SignInCommand");
        return client;
    }

    @Override
    public Client signUp(String login, String password, String name, String surname, String email) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Client Service: Sign up started");
        Client client = null;
        User user = null;
        try {
            if (Validator.isNull(login, password, name, surname, email) && Validator.isEmptyString(login, password, name, surname, email) && Validator.matchProperName(name, surname) && Validator.matchLogin(login) && Validator.matchPassword(password) && Validator.matchEmail(email)) {
                password = Hasher.sha1Hash(password);
                if (daoFactory.getAdministratorDao().findAdministratorByLogin(login) == null) {
                    user = daoFactory.getClientDao().addUser(login, password);
                    if (user != null) {
                        client = daoFactory.getClientDao().addClient(user.getIdUser(), login, name, surname, email);
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Client Service: finish SignUpCommand");
        return client;
    }

    @Override
    public Client findClientByLogin(String login) throws ServiceException {
        ClientDao clientDao = daoFactory.getClientDao();
        Client client = null;
        try {
            if (Validator.isNull(login) && Validator.isEmptyString(login) && Validator.matchLogin(login)) {
                client = clientDao.findClientByLogin(login);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return client;
    }


    @Override
    public Client findClientByEmail(String email) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start findClientByEmail");
        try {
            if (Validator.isNull(email) && Validator.isEmptyString(email) && Validator.matchEmail(email)) {
                LOGGER.log(Level.DEBUG, "ClientService: Finish findClientByEmail");
                return daoFactory.getClientDao().findClientByEmail(email);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> findAllClients() throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start findAllClients");
        try {
            LOGGER.log(Level.DEBUG, "ClientService: Finish findAllClients");
            return daoFactory.getClientDao().findAllClients();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Client findClientById(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start findClientById");
        try {
            LOGGER.log(Level.DEBUG, "ClientService: Finish findClientById");
            return daoFactory.getClientDao().findClientById(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void unbanClient(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start unbanClient");
        try {
            LOGGER.log(Level.DEBUG, "ClientService: Finish unbanClient");
            daoFactory.getClientDao().unbanClient(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void banClient(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start banClient");
        try {
            LOGGER.log(Level.DEBUG, "ClientService: Finish banClient");
            daoFactory.getClientDao().banClient(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public boolean checkBan(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start checkBan");
        try {
            LOGGER.log(Level.DEBUG, "ClientService: Finish checkBan");
            return daoFactory.getClientDao().checkBan(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Client editClient(int idClient, String surname, String name, String email) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start editClient");
        try {
            if (Validator.isNull(surname, name, email) && Validator.isEmptyString(surname, name, email) && Validator.matchProperName(surname, name) && Validator.matchEmail(email)) {
                LOGGER.log(Level.DEBUG, "ClientService: Finish editClient");
                return daoFactory.getClientDao().editClient(idClient, surname, name, email);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public Client addPoints(int idClient, double totalCost) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start addPoints");
        try {
            LOGGER.log(Level.DEBUG, "ClientService: Finish addPoints");
            String points = String.valueOf(totalCost);
            points = points.substring(0, 2);
            double point = Double.parseDouble(points) / 10;
            return daoFactory.getClientDao().addPoints(idClient, point);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public Client changePassword(int idClient, String password) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start changePassword");
        try {
            if (Validator.isEmptyString(password) && Validator.isNull(password)) {
                LOGGER.log(Level.DEBUG, "ClientService: Finish changePassword");
                return daoFactory.getClientDao().changePassword(idClient, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public Client findClientByIdAndPassword(int idClient, String oldPassword) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ClientService: Start findClientByIdAndPassword");
        try {
            if (Validator.isEmptyString(oldPassword) && Validator.isNull(oldPassword) && Validator.matchPassword(oldPassword)) {
                LOGGER.log(Level.DEBUG, "ClientService: Finish findClientByIdAndPassword");
                return daoFactory.getClientDao().findClientByIdAndPassword(idClient, oldPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }


}
