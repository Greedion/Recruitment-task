package com.task.dataaccessobject;

import com.task.exception.ServiceOperationException;
import com.task.service.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.BufferedReader;

@Repository
public class DataAccessFactory implements DataAccessInterface {

    final private DataAccess dataAccess;
    private final Logger logger = LoggerFactory.getLogger(DataAccessFactory.class);
    private ConfigurationPropertiesValue configValue;
    public DataAccessFactory(final DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Autowired
    private void setConfigValue(ConfigurationPropertiesValue configValue){
        this.configValue = configValue;
    }

    @Override
    public BufferedReader streamFactory(Gender gender) {
        try {
            return switch (gender) {
                case FEMALE -> dataAccess.getStreamFromFile(configValue.getFemale());
                case MALE -> dataAccess.getStreamFromFile(configValue.getMale());
            };
        }catch (NullPointerException e) {
            logger.error("Wrong file path exception: {}", e.getMessage());
            throw new ServiceOperationException("Wrong file patch exception: " + e.getMessage());
        }
    }
}