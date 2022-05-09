package finalkursproject.command.impl;


import finalkursproject.command.ICloseDBCommand;
import finalkursproject.exception.ServiceException;
import finalkursproject.service.ICloseDB;
import finalkursproject.service.impl.CloseDB;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class CloseDBCommand implements ICloseDBCommand {
    private static final Logger LOGGER = Logger.getLogger(CloseDBCommand.class);

    @Override
    public void closeDB() {
        try {
            ICloseDB closeDB = new CloseDB();
            closeDB.closeConnections();
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, "Problem while closing database:" + e.getMessage());
        }
    }
}
