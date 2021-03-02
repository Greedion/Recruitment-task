package com.task.dataaccessobject;

import com.task.exception.ExceptionArchive;
import com.task.exception.ServiceOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Component
class DataAccess {

    private final Logger logger = LoggerFactory.getLogger(DataAccess.class);

    BufferedReader getStreamFromFile(String path){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(
                        getClass().getClassLoader().getResourceAsStream(path))));
                if (reader.ready()){
                    return reader;
                }
            }catch (IOException | NullPointerException e){
                logger.error(ExceptionArchive.DA_WRONG_FILE_PATH_EXCEPTION_LOGGER, e.getMessage());
                throw new ServiceOperationException(ExceptionArchive.DA_WRONG_FILE_PATH_EXCEPTION_WITH_ERROR_MESSAGE + e.getMessage());
            }
        throw new ServiceOperationException(ExceptionArchive.DA_WRONG_FILE_PATH_EXCEPTION);
    }
}
