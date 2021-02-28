package com.task.service;

import com.task.dataaccessobject.DataAccessFactory;
import com.task.dataaccessobject.DataAccessInterface;
import com.task.exception.ServiceOperationException;
import com.task.model.TokenModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class GetFileContentServiceTest {

    @Test
    @DisplayName("Should throw exception when receive closed stream")
    void should_Return_Exception_When_Receive_Close_Stream_For_VariantOne() {
        //give
        DataAccessInterface dataAccessMock = getDataAccessFactoryMockWithClosedStream();
        GetFileContentService getFileContentService = new GetFileContentService(dataAccessMock);

        //when
        var exception = catchThrowable(getFileContentService::getContentFromFile);

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining("Stream close exception");
    }

    @Test
    @DisplayName("Should return the same as in the file")
    void should_Return_All_Data() {
        //give
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        GetFileContentService getFileContentService = new GetFileContentService(dataAccessMock);

        //and
        Map<String, String> elements = getAllElementsFromStream(dataAccessMock);
        TokenModel tokenModel = new TokenModel();
        tokenModel.setTokens(elements);
        ResponseEntity<TokenModel> mockElements = ResponseEntity.ok(tokenModel);

        //when
        ResponseEntity<TokenModel> answerFromContentService = getFileContentService.getContentFromFile();

        //then
        assertEquals(mockElements, answerFromContentService);
    }

    Map<String, String> getAllElementsFromStream(DataAccessInterface dataAccessFactory) {
        Map<String, String> elements = new HashMap<>();
        try {
            BufferedReader reader = dataAccessFactory.streamFactory(Gender.FEMALE);
            reader.lines().
                    forEach(x -> elements.put(x, "FEMALE"));
            reader.close();
            reader = dataAccessFactory.streamFactory(Gender.MALE);
            reader.lines()
                    .forEach(x -> elements.put(x, "MALE"));
            reader.close();
            return elements;
        } catch (UncheckedIOException | IOException e) {
            throw new ServiceOperationException("Stream close exception: " + e.getMessage());
        }
    }


    DataAccessFactory getDataAccessFactoryMock() {
        return new DataAccessFactory(null) {
            final private static String FEMALE = "src/test/java/com/task/resources/female.csv";
            final private static String MALE = "src/test/java/com/task/resources/male.csv";

            @Override
            public BufferedReader streamFactory(Gender gender) {
                try {
                    return switch (gender) {
                        case FEMALE -> getStreamFromFile(FEMALE);
                        case MALE -> getStreamFromFile(MALE);
                    };
                } catch (NullPointerException e) {
                    throw new ServiceOperationException("Wrong file path exception: " + e.getMessage());
                }
            }
        };
    }

    DataAccessFactory getDataAccessFactoryMockWithClosedStream() {
        return new DataAccessFactory(null) {
            final private static String FEMALE = "src/test/java/com/task/resources/female.csv";

            // Try with resources should close stream
            @Override
            public BufferedReader streamFactory(Gender gender) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(FEMALE)))) {
                    return reader;
                } catch (IOException e) {
                    throw new ServiceOperationException("Wrong file path exception.");
                }
            }
        };
    }

    BufferedReader getStreamFromFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path)));
            if (reader.ready()) {
                return reader;
            }
        } catch (IOException | NullPointerException e) {
            throw new ServiceOperationException("Wrong file path exception: " + e.getMessage());
        }
        throw new ServiceOperationException("Wrong file path exception.");
    }
}