package com.task.dataaccessobject;

import com.task.exception.ServiceOperationException;
import com.task.service.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class DataAccessFactoryTest {

    final static String EXCEPTION_MESSAGE = "Wrong file path exception";

    @Test
    @DisplayName("Should throw exception when path for file is wrong")
    void wrong_Path_Exception(){
        //given
        String wrongPath = "XXXXX";
        var dataAccessFactory = getDataAccessFactory(wrongPath, wrongPath);

        //when
            var exception = catchThrowable(() -> dataAccessFactory.streamFactory(Gender.FEMALE));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining(EXCEPTION_MESSAGE);
    }
    @Test
    @DisplayName("Should throw exception when path for file is null")
    void path_Is_Null_Exception(){
        //given
        var dataAccessFactory = getDataAccessFactory(null, null);

        //when
        var exception = catchThrowable(() -> dataAccessFactory.streamFactory(Gender.FEMALE));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining(EXCEPTION_MESSAGE);
    }

    DataAccessFactory getDataAccessFactory(String femalePath, String malePath){
        return new DataAccessFactory(null){
            final private DataAccess dataAccess = new DataAccess();
            final private  String FEMALE = femalePath;
            final private  String MALE = malePath;
            private final Logger logger = LoggerFactory.getLogger(DataAccessFactoryTest.class);

            @Override
            public BufferedReader streamFactory(Gender gender) {
                try {
                    return switch (gender) {
                        case FEMALE -> dataAccess.getStreamFromFile(FEMALE);
                        case MALE -> dataAccess.getStreamFromFile(MALE);
                    };
                }catch (NullPointerException e) {
                    logger.error("Wrong file path exception: {}", e.getMessage());
                    throw new ServiceOperationException("Wrong file Path exception: " + e.getMessage());
                }
            }
        };
    }
}