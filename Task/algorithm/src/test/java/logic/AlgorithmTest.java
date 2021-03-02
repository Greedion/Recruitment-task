package logic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmTest {

    @Test
    @DisplayName("Should throw exception when 'match' is null. Test for variantOne")
    void match_Is_Null_Exception_For_VariantOne() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = null;

        //when
        var exception = Assertions.catchThrowable(() -> algorithm.variantOne(match));
        //then

        Assertions.assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining("Match cannot be null");
    }

    @Test
    @DisplayName("Should throw exception when 'match' is null. Test for variantTwo")
    void match_Is_Null_Exception_For_VariantTwo() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);

        //when
        var exception = Assertions.catchThrowable(() -> algorithm.variantTwo(null));

        //then
        Assertions.assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining("Match cannot be null");
    }

    @Test
    @DisplayName("Should return female. Test for variantOne")
    void should_return_FEMALE_For_VariantOne() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = "Maria";

        //when
        var result = algorithm.variantOne(match);

        //then
        assertEquals(result, GenderAnswer.FEMALE);
    }

    @Test
    @DisplayName("Should return male. Test for variantOne")
    void should_return_MALE_For_VariantOne() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = "Jan";

        //when
        var result = algorithm.variantOne(match);

        //then
        assertEquals(result, GenderAnswer.MALE);
    }

    @Test
    @DisplayName("Should return inconclusive. Test for variantOne")
    void should_return_INCONCLUSIVE_For_VariantOne() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = "FOO";

        //when
        var result = algorithm.variantOne(match);

        //then
        assertEquals(result, GenderAnswer.INCONCLUSIVE);
    }

    @Test
    @DisplayName("Should return female. Test for variantTwo")
    void should_return_Female_For_VariantTwo() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = "Maria";

        //when
        var result = algorithm.variantTwo(match);

        //then
        assertEquals(result, GenderAnswer.FEMALE);
    }

    @Test
    @DisplayName("Should return male. Test for variantTwo")
    void should_return_Male_For_VariantTwo() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = "Jan";

        //when
        var result = algorithm.variantTwo(match);

        //then
        assertEquals(result, GenderAnswer.MALE);
    }

    @Test
    @DisplayName("Should return inconclusive. Test for variantTwo")
    void should_return_INCONCLUSIVE_For_VariantTwo() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = "FOO";

        //when
        var result = algorithm.variantTwo(match);

        //then
        assertEquals(result, GenderAnswer.INCONCLUSIVE);
    }

    @Test
    @DisplayName("Should return female with three tokens. Test for variantTwo")
    void should_Return_FEMALE_With_Three_Tokens_For_VariantTwo() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = "Maria Jan Daria";

        //when
        var result = algorithm.variantTwo(match);

        //then
        assertEquals(result, GenderAnswer.FEMALE);
    }

    @Test
    @DisplayName("Should return male with three tokens. Test for variantTwo")
    void should_Return_MALE_With_Three_Tokens_For_VariantTwo() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String match = "Adrian Jan Daria";

        //when
        var result = algorithm.variantTwo(match);

        //then
        assertEquals(result, GenderAnswer.MALE);
    }

    @Test
    @DisplayName("Should return the correct result for all data from 'male' file. Test for VariantOne")
    void should_Include_All_Data_For_Male_And_For_VariantOne() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String matchOne = "Jan";
        String matchTwo = "Adrian";
        String matchThree = "Damian";
        String matchFour = "Patryk";

        //when
        var resultOne = algorithm.variantOne(matchOne);
        var resultTwo = algorithm.variantOne(matchTwo);
        var resultThree = algorithm.variantOne(matchThree);
        var resultFour = algorithm.variantOne(matchFour);

        //then
        assertEquals(resultOne, GenderAnswer.MALE);
        assertEquals(resultTwo, GenderAnswer.MALE);
        assertEquals(resultThree, GenderAnswer.MALE);
        assertEquals(resultFour, GenderAnswer.MALE);
    }

    @Test
    @DisplayName("Should return the correct result for all data from 'female' file. Test for VariantOne")
    void should_Include_All_Data_For_Female_And_For_VariantOne() {
        //given
        DataAccessFactory dataAccessMock = getDataAccessFactoryMock();
        Algorithm algorithm = new Algorithm(dataAccessMock);
        String matchOne = "Maria";
        String matchTwo = "Daria";
        String matchThree = "Krystyna";
        String matchFour = "Aleksandra";

        //when
        var resultOne = algorithm.variantOne(matchOne);
        var resultTwo = algorithm.variantOne(matchTwo);
        var resultThree = algorithm.variantOne(matchThree);
        var resultFour = algorithm.variantOne(matchFour);

        //then
        assertEquals(resultOne, GenderAnswer.FEMALE);
        assertEquals(resultTwo, GenderAnswer.FEMALE);
        assertEquals(resultThree, GenderAnswer.FEMALE);
        assertEquals(resultFour, GenderAnswer.FEMALE);
    }

    @Test
    @DisplayName("Should throw exception when receive closed stream. Test for VariantOne")
    void should_Return_Exception_When_Receive_Close_Stream_For_VariantOne() {
        //give
        DataAccessFactory dataAccessMock = getDataAccessFactoryMockWithClosedStream();
        Algorithm algorithm = new Algorithm(dataAccessMock);

        //when
        var exception = Assertions.catchThrowable(() -> algorithm.variantOne("foo"));

        //then
        Assertions.assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining("Stream close exception");
    }

    @Test
    @DisplayName("Should throw exception when receive closed stream. Test for VariantTwo")
    void should_Return_Exception_When_Receive_Close_Stream_For_VariantTwo() {
        //give
        DataAccessFactory dataAccessMock = getDataAccessFactoryMockWithClosedStream();
        Algorithm algorithm = new Algorithm(dataAccessMock);

        //when
        var exception = Assertions.catchThrowable(() -> algorithm.variantTwo("foo"));

        //then
        Assertions.assertThat(exception)
                .isInstanceOf(ServiceOperationException.class)
                .hasMessageContaining("Stream close exception");
    }


    DataAccessFactory getDataAccessFactoryMockWithClosedStream() {
        return new DataAccessFactory() {
            final private static String FEMALE = "src/test/java/resources/female.csv";
            // Try with resources should close stream
            @Override
            public BufferedReader streamFactory(Gender gender) {
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(FEMALE)))){
                    return reader;
                }catch (IOException e){
                    throw new ServiceOperationException("Wrong file path exception.");
                }
            }
        };
    }

    DataAccessFactory getDataAccessFactoryMock() {
        return new DataAccessFactory() {
            final private static String FEMALE = "src/test/java/resources/female.csv";
            final private static String MALE = "src/test/java/resources/male.csv";

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