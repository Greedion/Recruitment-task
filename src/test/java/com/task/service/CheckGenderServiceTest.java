package com.task.service;

import com.task.exception.ServiceOperationException;
import com.task.model.MatchWithVariantModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckGenderServiceTest {

    Algorithm algorithm;
    CheckGenderService checkGenderService;

    @BeforeAll
    void setUp() {
        algorithm = getMockAlgorithmVersion();
        checkGenderService = new CheckGenderService(algorithm);
    }

    @Test
    @DisplayName("Should select the correct variant based on the variable")
    void should_Choose_Correct_Variant() {
        //give
        MatchWithVariantModel variantOne = new MatchWithVariantModel("Jan", "1");
        MatchWithVariantModel variantTwo = new MatchWithVariantModel("Jan", "2");

        //when
        var resultOne = checkGenderService.selectVariant(variantOne);
        var resultTwo = checkGenderService.selectVariant(variantTwo);

        //then
        assertEquals(resultOne, ResponseEntity.ok("FEMALE"));
        assertEquals(resultTwo, ResponseEntity.ok("MALE"));

    }

    @Test
    @DisplayName("Should throw exception when match is empty")
    void should_Throw_Exception_When_Match_Is_Empty() {
        //give
        MatchWithVariantModel match = new MatchWithVariantModel("", "1");
        String INVALID_MATCH_EXCEPTION = "Invalid 'match' exception.";

        //when
        var exception = catchThrowable(() -> checkGenderService.selectVariant(match));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining(INVALID_MATCH_EXCEPTION);
    }

    @Test
    @DisplayName("Should throw exception when match is null")
    void should_Throw_Exception_When_Match_Is_Null() {
        //give
        MatchWithVariantModel match = new MatchWithVariantModel(null, "1");
        String INVALID_MATCH_EXCEPTION = "Invalid 'match' exception.";

        //when
        var exception = catchThrowable(() -> checkGenderService.selectVariant(match));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining(INVALID_MATCH_EXCEPTION);
    }

    @Test
    @DisplayName("Should throw exception when variant is empty")
    void should_Throw_Exception_When_Variant_Is_Empty() {
        //give
        MatchWithVariantModel match = new MatchWithVariantModel("Foo", "");
        String INVALID_VARIANT_EXCEPTION = "Invalid 'variant' exception.";

        //when
        var exception = catchThrowable(() -> checkGenderService.selectVariant(match));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining(INVALID_VARIANT_EXCEPTION);
    }

    @Test
    @DisplayName("Should throw exception when variant is null")
    void should_Throw_Exception_When_Variant_Is_Null() {
        //give
        MatchWithVariantModel match = new MatchWithVariantModel("Foo", null);
        String INVALID_VARIANT_EXCEPTION = "Invalid 'variant' exception.";

        //when
        var exception = catchThrowable(() -> checkGenderService.selectVariant(match));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining(INVALID_VARIANT_EXCEPTION);
    }

    @Test
    @DisplayName("Should throw exception when variant contain wrong char")
    void should_Throw_Exception_When_Variant_Contain_Wrong_Char() {
        //give
        MatchWithVariantModel match = new MatchWithVariantModel("Foo", "1GG");
        String NUMBER_FORMAT_EXCEPTION = "Variant contains illegal characters: {}";

        //when
        var exception = catchThrowable(() -> checkGenderService.selectVariant(match));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining(NUMBER_FORMAT_EXCEPTION);
    }

    @Test
    @DisplayName("Should throw exception when variant is out of scope")
    void should_Throw_Exception_When_Variant_Is_Out_Of_Scope() {
        //give
        MatchWithVariantModel match = new MatchWithVariantModel("Foo", "3");
        String ILLEGAL_STATE_EXCEPTION = "Unexpected value: ";

        //when
        var exception = catchThrowable(() -> checkGenderService.selectVariant(match));

        //then
        assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining(ILLEGAL_STATE_EXCEPTION);
    }

    Algorithm getMockAlgorithmVersion() {
        var algorithm = mock(Algorithm.class);
        when(algorithm.variantOne(anyString())).thenReturn(GenderAnswer.FEMALE);
        when(algorithm.variantTwo(anyString())).thenReturn(GenderAnswer.MALE);
        return algorithm;
    }
}