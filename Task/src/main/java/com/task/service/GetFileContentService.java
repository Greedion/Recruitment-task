package com.task.service;

import com.task.exception.ServiceOperationException;
import com.task.model.TokenModel;
import logic.DataAccessInterface;
import logic.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;

@Service
public class GetFileContentService {

    private final Logger logger = LoggerFactory.getLogger(GetFileContentService.class);
    private final DataAccessInterface dataAccessFactory;
    private final static String STREAM_EXCEPTION = "Stream close exception: ";
    private final static String STREAM_EXCEPTION_LOGGER = "Stream close exception: {}";

    GetFileContentService(final DataAccessInterface dataAccessFactory) {
        this.dataAccessFactory = dataAccessFactory;
    }

    public ResponseEntity<TokenModel> getContentFromFile() {
        TokenModel tokenModel = new TokenModel();
        try {
            BufferedReader reader = dataAccessFactory.streamFactory(Gender.FEMALE);
            reader.lines().forEach(x -> tokenModel.put(x, "FEMALE"));
            reader.close();
            reader = dataAccessFactory.streamFactory(Gender.MALE);
            reader.lines().forEach(x -> tokenModel.put(x, "MALE"));
            reader.close();
            return ResponseEntity.ok(tokenModel);
        } catch (UncheckedIOException | IOException e) {
            logger.error(STREAM_EXCEPTION_LOGGER, e.getMessage());
            throw new ServiceOperationException(STREAM_EXCEPTION + e.getMessage());
        }
    }
}
