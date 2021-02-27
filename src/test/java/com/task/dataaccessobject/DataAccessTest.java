package com.task.dataaccessobject;

import com.task.exception.ServiceOperationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
class DataAccessTest {

    @Test
    @DisplayName("Should throw exception when path for file is wrong")
    void wrong_path_Exception(){
        //given
        DataAccess dataAccess = new DataAccess();
        String wrongPath = "WRONG PATH";

        //when
         var exception = catchThrowable(() -> dataAccess.getStreamFromFile(wrongPath));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining("Wrong file path exception");
    }

    @Test
    @DisplayName("Should throw exception when path for file is null")
    void path_Is_Null_Exception(){
        //given
        DataAccess dataAccess = new DataAccess();

        //when
        var exception = catchThrowable(() -> dataAccess.getStreamFromFile(null));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining("Wrong file path exception");
    }
}