package com.task.service;

import com.task.dataaccessobject.DataAccessInterface;
import com.task.exception.ServiceOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.StringTokenizer;

@Service
public class Algorithm {

    private final DataAccessInterface dataAccessFactory;
    private final Logger logger = LoggerFactory.getLogger(Algorithm.class);
    private final static String STREAM_EXCEPTION = "Stream close exception: ";
    private final static String STREAM_EXCEPTION_LOGGER = "Stream close exception: {}";
    private final static String MATCH_NULL_EXCEPTION = "Match cannot be null.";

    Algorithm(final DataAccessInterface dataAccessFactory) {
        this.dataAccessFactory = dataAccessFactory;
    }

    private GenderAnswer checkGenderForSingleMatch(String match){
        checkMatch(match);
        BufferedReader reader = dataAccessFactory.streamFactory(Gender.FEMALE);
        try {
        boolean isFemale = reader.lines()
                .map(String::toUpperCase)
                .anyMatch(x -> x.equals(match.toUpperCase()));
            reader.close();
            if (isFemale) {
                return GenderAnswer.FEMALE;
            } else {
                reader = dataAccessFactory.streamFactory(Gender.MALE);
                boolean isMale = reader.lines()
                        .map(String::toUpperCase)
                        .anyMatch(x -> x.equals(match.toUpperCase()));
                reader.close();
                if (isMale) {
                    return GenderAnswer.MALE;
                } else {
                    return GenderAnswer.INCONCLUSIVE;
                }
            }
        }catch (UncheckedIOException | IOException e){
            logger.error(STREAM_EXCEPTION_LOGGER, e.getMessage());
            throw new ServiceOperationException(STREAM_EXCEPTION+ e.getMessage());
        }
    }

    public GenderAnswer variantOne(String match){
        checkMatch(match);
        StringTokenizer stringTokenizer = new StringTokenizer(match);
        String firstToken = stringTokenizer.nextToken();
        return checkGenderForSingleMatch(firstToken);
    }

    public GenderAnswer variantTwo(String match){
        checkMatch(match);
        int male = 0;
        int female = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(match);
        GenderAnswer answerForToken;
        while (stringTokenizer.hasMoreTokens()) {
            answerForToken = checkGenderForSingleMatch(stringTokenizer.nextToken());
            if (answerForToken.equals(GenderAnswer.FEMALE)) {
                female++;
            } else if (answerForToken.equals(GenderAnswer.MALE)) {
                male++;
            }
        }
        if (male > female) {
            return GenderAnswer.MALE;
        } else if (male < female) {
            return GenderAnswer.FEMALE;
        } else {
            return GenderAnswer.INCONCLUSIVE;
        }
    }

    private void checkMatch(final String match) {
        if(match == null){
            logger.error(MATCH_NULL_EXCEPTION);
            throw new ServiceOperationException(MATCH_NULL_EXCEPTION);
        }
    }
}
